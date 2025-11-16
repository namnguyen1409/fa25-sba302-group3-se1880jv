package com.sba301.chatbackend.controller

import com.sba301.chatbackend.dto.AttachmentUploadCompleteRequest
import com.sba301.chatbackend.dto.MessageResponse
import com.sba301.chatbackend.repository.MessageRepository
import com.sba301.chatbackend.service.MessageService
import io.minio.*
import io.minio.http.Method
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import java.time.Instant
import java.util.*

@RestController
@RequestMapping("/api/attachments")
@Tag(name = "📎 Attachments (MinIO)", description = "Upload file đính kèm tin nhắn lên MinIO")
@SecurityRequirement(name = "bearerAuth")
class AttachmentController(
    private val minioClient: MinioClient,
    private val messageRepository: MessageRepository,
    private val messageService: MessageService,
    @Value("\${minio.bucket:chat-files}") private val bucketName: String
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/upload", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(
        summary = "Upload file cho attachment (MinIO)",
        description = "Upload dựa trên attachmentId đã gửi kèm khi tạo message qua WebSocket"
    )
    @ApiResponse(
        responseCode = "200", description = "Upload thành công",
        content = [Content(schema = Schema(implementation = MessageResponse::class))]
    )
    fun uploadAttachment(
        @RequestPart("file") filePart: FilePart,
        @RequestPart("attachmentId") attachmentId: String,
        @AuthenticationPrincipal auth: Authentication
    ): Mono<MessageResponse> {
        val originalName = filePart.filename().substringAfterLast("/").substringAfterLast("\\")
        val ext = originalName.substringAfterLast('.', "").lowercase().takeIf { it.isNotBlank() }?.let { ".$it" } ?: ""
        val storedFileName = "${UUID.randomUUID()}$ext"
        val objectName = "attachments/$storedFileName"

        log.debug("Upload bắt đầu attachmentId=$attachmentId user=${auth.name} original=$originalName object=$objectName")

        return messageRepository.findByAttachmentId(attachmentId)
            .switchIfEmpty(Mono.error(IllegalArgumentException("Không tìm thấy message chứa attachmentId này")))
            .flatMap { message ->
                if (message.senderId != auth.name) {
                    log.warn("User ${auth.name} cố upload attachment không thuộc message=${message.id}")
                    return@flatMap Mono.error<MessageResponse>(IllegalAccessException("Không có quyền upload"))
                }

                // === Upload lên MinIO ===
                filePart.content()
                    .reduce(ByteArray(0)) { acc, dataBuffer ->
                        val newBytes = ByteArray(acc.size + dataBuffer.readableByteCount())
                        System.arraycopy(acc, 0, newBytes, 0, acc.size)
                        dataBuffer.read(newBytes, acc.size, dataBuffer.readableByteCount())
                        newBytes
                    }
                    .flatMap { bytes ->
                        Mono.fromCallable {
                            minioClient.putObject(
                                PutObjectArgs.builder()
                                    .bucket(bucketName)
                                    .`object`(objectName)
                                    .stream(bytes.inputStream(), bytes.size.toLong(), -1)
                                    .contentType(filePart.headers().contentType?.toString() ?: "application/octet-stream")
                                    .build()
                            )
                            bytes.size.toLong()
                        }
                    }
                    .flatMap { size ->
                        val presignedUrl = minioClient.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                .bucket(bucketName)
                                .`object`(objectName)
                                .method(Method.GET)
                                .build()
                        )

                        messageService.updateAttachment(
                            AttachmentUploadCompleteRequest(
                                attachmentId = attachmentId,
                                messageId = message.id!!,
                                fileName = storedFileName,
                                url = presignedUrl,
                                fileSize = size,
                                uploadedAt = Instant.now()
                            )
                        )
                    }
            }
            .onErrorResume { e ->
                log.error("Upload thất bại attachmentId=$attachmentId object=$objectName", e)
                Mono.error(ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Upload thất bại"))
            }
    }

    @GetMapping("/{fileName}")
    @Operation(summary = "Lấy presigned URL cho file MinIO", description = "Trả về URL tạm thời cho phép tải file từ MinIO")
    fun getFile(@PathVariable fileName: String): Mono<ResponseEntity<Map<String, String>>> {
        val safeFile = fileName.substringAfterLast("/")
        val objectName = "attachments/$safeFile"

        return Mono.fromCallable {
            val url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .bucket(bucketName)
                    .`object`(objectName)
                    .method(Method.GET)
                    .expiry(60 * 60 * 24) // 24h
                    .build()
            )
            ResponseEntity.ok(mapOf("url" to url))
        }.onErrorResume { e ->
            log.error("Không tạo được presigned URL cho $objectName", e)
            Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy file"))
        }
    }
}

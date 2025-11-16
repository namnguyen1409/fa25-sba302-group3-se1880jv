package com.sba301.chatbackend.controller

import com.sba301.chatbackend.dto.CreateProfileRequest
import com.sba301.chatbackend.dto.ProfileResponse
import com.sba301.chatbackend.dto.UpdateProfileRequest
import com.sba301.chatbackend.service.ProfileService
import com.sba301.chatbackend.service.UserService
import io.minio.*
import io.minio.http.Method
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
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
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import java.time.Instant

@RestController
@RequestMapping("/api/profiles")
@Tag(name = "Profile Management", description = "APIs để quản lý profile (nhiều profile/user)")
@SecurityRequirement(name = "bearerAuth")
class ProfileController(
    private val profileService: ProfileService,
    private val userService: UserService,
    private val minioClient: MinioClient,
    @Value("\${minio.bucket:chat-files}") private val bucketName: String
) {
    private val log = LoggerFactory.getLogger(javaClass)

    // ================== BASIC PROFILE OPS ==================
    @PostMapping
    fun createProfile(
        @Valid @RequestBody request: CreateProfileRequest,
        @AuthenticationPrincipal authentication: Authentication
    ): Mono<ProfileResponse> {
        val userId = authentication.name
        return userService.getUserById(userId)
            .flatMap { user -> profileService.createProfile(user.id, request) }
    }

    @GetMapping
    fun getMyProfiles(@AuthenticationPrincipal authentication: Authentication): Flux<ProfileResponse> {
        val userId = authentication.name
        return userService.getUserById(userId)
            .flatMapMany { user -> profileService.getUserProfiles(user.id) }
    }

    @GetMapping("/{profileId}")
    fun getProfile(@PathVariable profileId: String): Mono<ProfileResponse> =
        profileService.getProfileById(profileId)

    @GetMapping("/default")
    fun getMyDefaultProfile(@AuthenticationPrincipal authentication: Authentication): Mono<ProfileResponse> {
        val userId = authentication.name
        return userService.getUserById(userId)
            .flatMap { user -> profileService.getDefaultProfile(user.id) }
    }

    @PutMapping("/{profileId}")
    fun updateProfile(
        @PathVariable profileId: String,
        @Valid @RequestBody request: UpdateProfileRequest,
        @AuthenticationPrincipal authentication: Authentication
    ): Mono<ProfileResponse> {
        val userId = authentication.name
        return userService.getUserById(userId)
            .flatMap { user -> profileService.updateProfile(profileId, user.id, request) }
    }

    @DeleteMapping("/{profileId}")
    fun deleteProfile(
        @PathVariable profileId: String,
        @AuthenticationPrincipal authentication: Authentication
    ): Mono<Void> {
        val userId = authentication.name
        return userService.getUserById(userId)
            .flatMap { user -> profileService.deleteProfile(profileId, user.id) }
    }

    // ================== AVATAR UPLOAD ==================
    @PostMapping("/{profileId}/avatar", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(summary = "Upload avatar cho profile", description = "Upload ảnh đại diện lên MinIO và cập nhật URL")
    fun uploadAvatar(
        @PathVariable profileId: String,
        @RequestPart("file") filePart: FilePart,
        @AuthenticationPrincipal authentication: Authentication
    ): Mono<ProfileResponse> {
        val userId = authentication.name
        val ext = filePart.filename().substringAfterLast('.', "").let { if (it.isBlank()) "" else ".$it" }
        val storedFileName = "avatars/${UUID.randomUUID()}$ext"

        return filePart.content()
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
                            .`object`(storedFileName)
                            .stream(bytes.inputStream(), bytes.size.toLong(), -1)
                            .contentType(filePart.headers().contentType?.toString() ?: "image/jpeg")
                            .build()
                    )
                    storedFileName
                }
            }
            .flatMap { objectName ->
                val presigned = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                        .bucket(bucketName)
                        .`object`(objectName)
                        .method(Method.GET)
                        .expiry(60 * 60 * 24 * 7)
                        .build()
                )
                profileService.updateProfile(
                    profileId,
                    userId,
                    UpdateProfileRequest(
                        avatar = presigned,
                        displayName = null,
                        bio = null,
                        status = null
                    )
                )
            }
            .onErrorResume { e ->
                log.error("Lỗi upload avatar profile=$profileId", e)
                Mono.error(ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Upload avatar thất bại"))
            }
    }

    // ================== AUDIO UPLOAD ==================
    @PostMapping("/audio", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(summary = "Upload audio voice giới thiệu / tin nhắn thoại")
    fun uploadAudio(
        @RequestPart("file") filePart: FilePart,
        @AuthenticationPrincipal authentication: Authentication
    ): Mono<Map<String, String>> {
        val userId = authentication.name
        val ext = filePart.filename().substringAfterLast('.', "").let { if (it.isBlank()) "" else ".$it" }
        val storedFileName = "audio/${userId}_${UUID.randomUUID()}$ext"

        return filePart.content()
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
                            .`object`(storedFileName)
                            .stream(bytes.inputStream(), bytes.size.toLong(), -1)
                            .contentType(filePart.headers().contentType?.toString() ?: "audio/mpeg")
                            .build()
                    )
                    val presignedUrl = minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                            .bucket(bucketName)
                            .`object`(storedFileName)
                            .method(Method.GET)
                            .expiry(60 * 60 * 24 * 7)
                            .build()
                    )
                    mapOf("url" to presignedUrl, "object" to storedFileName)
                }
            }
            .onErrorResume { e ->
                log.error("Lỗi upload audio", e)
                Mono.error(ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Upload audio thất bại"))
            }
    }

    @GetMapping("/audio/list")
    fun listUserAudios(@AuthenticationPrincipal authentication: Authentication): Mono<MutableList<String>?> {
        val userId = authentication.name
        val prefix = "audio/${userId}_"
        return Mono.fromCallable {
            val results = mutableListOf<String>()
            val listObjectsArgs = ListObjectsArgs.builder()
                .bucket(bucketName)
                .prefix(prefix)
                .build()
            val objects = minioClient.listObjects(listObjectsArgs)
            for (result in objects) {
                val item = result.get()
                results.add(item.objectName())
            }
            results
        }.onErrorResume { e ->
            log.error("Lỗi liệt kê audio cho user $userId", e)
            Mono.error(ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Không thể liệt kê audio"))
        }
    }

    @GetMapping("/audio/{fileName}")
    fun getAudioPresigned(@PathVariable fileName: String): Mono<ResponseEntity<Map<String, String>>> {
        val objectName = "audio/$fileName"
        return Mono.fromCallable {
            val url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .bucket(bucketName)
                    .`object`(objectName)
                    .method(Method.GET)
                    .expiry(60 * 60 * 24)
                    .build()
            )
            ResponseEntity.ok(mapOf("url" to url))
        }.onErrorResume { e ->
            log.error("Không tạo được URL cho audio $fileName", e)
            Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "File không tồn tại"))
        }
    }

    // ================== GET PRESIGNED AVATAR ==================
    @GetMapping("/avatar/{fileName}")
    fun getAvatarPresigned(@PathVariable fileName: String): Mono<ResponseEntity<Map<String, String>>> {
        val objectName = "avatars/$fileName"
        return Mono.fromCallable {
            val url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .bucket(bucketName)
                    .`object`(objectName)
                    .method(Method.GET)
                    .expiry(60 * 60 * 24)
                    .build()
            )
            ResponseEntity.ok(mapOf("url" to url))
        }.onErrorResume { e ->
            log.error("Không tạo được URL cho avatar $fileName", e)
            Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "File không tồn tại"))
        }
    }
}

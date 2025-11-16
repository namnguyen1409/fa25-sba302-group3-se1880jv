package com.sba301.chatbackend.controller

import com.sba301.chatbackend.dto.AttachmentUploadCompleteRequest
import com.sba301.chatbackend.dto.MessageResponse
import com.sba301.chatbackend.service.MessageService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/chat/messages")
class MessagesController(private val messages: MessageService) {
    @GetMapping("/{roomId}")
    fun historyCursor(
        @PathVariable roomId: String,
        @RequestParam(required = false) beforeMessageId: String?,
        @RequestParam(defaultValue = "30") limit: Int
    ): Flux<MessageResponse> = messages.getMessagesBefore(roomId, beforeMessageId, limit)


    @PostMapping("/attachment/callback")
    fun attachComplete(@RequestBody req: AttachmentUploadCompleteRequest) = messages.updateAttachment(req)
}
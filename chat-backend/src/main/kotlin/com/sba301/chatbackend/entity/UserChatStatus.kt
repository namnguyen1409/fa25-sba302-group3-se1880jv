package com.sba301.chatbackend.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user_chat_status")
@CompoundIndex(name="room_user_idx", def="{ 'roomId': 1, 'userId': 1 }", unique = true)
class UserChatStatus (
        @Id
        val id: String? = null,
        @Indexed
        val userId: String,
        @Indexed
        val roomId: String,

        var unreadCount: Int = 0,
        var lastReadAt: java.time.Instant? = null
){
}
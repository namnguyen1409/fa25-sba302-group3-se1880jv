package com.sba301.chatbackend.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "profiles")
class Profile (

    @Id
    val id: String? = null,

    @Indexed
    val userId: String, // Reference to User

    var displayName: String,
    var avatar: String? = null, // URL or path to avatar image
    var bio: String? = null,
    var status: ProfileStatus = ProfileStatus.ONLINE,
    var isDefault: Boolean = false, // Whether this is the user's default profile
    var customFields: Map<String, String> = emptyMap(), // Additional custom profile data

    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

enum class ProfileStatus {
    ONLINE, OFFLINE, AWAY, BUSY, INVISIBLE
}
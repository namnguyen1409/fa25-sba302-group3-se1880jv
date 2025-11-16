package com.sba301.chatbackend.entity


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "users")
class User(
    @Id
    val id: String? = null,
    @Indexed(unique = true)
    var username: String,
    val fullName: String,
    val email: String,
    var roles: Set<String> = setOf(),
) {
}
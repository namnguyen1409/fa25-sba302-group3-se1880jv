package com.sba301.chatbackend.repository.impl

import com.mongodb.client.result.UpdateResult
import com.sba301.chatbackend.entity.UserChatStatus
import com.sba301.chatbackend.repository.UserChatStatusRepositoryCustom
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class UserChatStatusRepositoryCustomImpl(
    private val mongo: ReactiveMongoTemplate
) : UserChatStatusRepositoryCustom {
    override fun incUnreadForRoomExceptUser(roomId: String, excludeUserId: String): Mono<UpdateResult> {
        val q = Query(
            Criteria.where("roomId").`is`(roomId)
                .and("userId").ne(excludeUserId)
        )
        val u = Update().inc("unreadCount", 1)
        return mongo.updateMulti(q, u, UserChatStatus::class.java)
    }

    override fun resetUnreadCountForRoomAndUser(
        roomId: String,
        userId: String
    ): Mono<UpdateResult> {
        val q = Query(
            Criteria.where("roomId").`is`(roomId)
                .and("userId").`is`(userId)
        )
        val u = Update().set("unreadCount", 0)
        return mongo.updateFirst(q, u, UserChatStatus::class.java)
    }
}
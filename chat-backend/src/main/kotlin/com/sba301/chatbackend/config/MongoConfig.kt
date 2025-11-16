package com.sba301.chatbackend.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


@Configuration
@EnableReactiveMongoRepositories(basePackages = ["com.sba301.chatbackend.repository"])
@EnableReactiveMongoAuditing
class MongoConfig

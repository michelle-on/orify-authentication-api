package com.kofastack.authapi.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    @BsonId
    val uid: ObjectId = ObjectId(),
    val email: String,
    val username: String,
    val password: String
)
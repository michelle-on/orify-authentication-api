package com.kofastack.authapi.db

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

object MongoClientFactory {
    private val connectionString: String = System.getenv("MONGODB_CONNECTION_STRING")
        ?: throw IllegalStateException("MONGODB_CONNECTION_STRING environment variable not set")

    private val databaseAuthName: String = System.getenv("MONGODB_DATABASE_AUTH")
        ?: throw IllegalStateException("MONGODB_DATABASE_AUTH environment variable not set")

    private val settings = MongoClientSettings.builder()
        .applyConnectionString(ConnectionString(connectionString))
        .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
        .build()

    val client: CoroutineClient = KMongo.createClient(settings).coroutine
    val database = client.getDatabase(databaseAuthName)
}

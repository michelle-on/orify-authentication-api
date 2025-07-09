package com.kofastack.authapi.services

import com.kofastack.authapi.db.MongoClientFactory
import com.kofastack.authapi.models.User
import com.kofastack.authapi.models.results.RegisterResult
import com.kofastack.authapi.models.results.LoginResult
import org.bson.types.ObjectId
import org.litote.kmongo.eq
import org.litote.kmongo.or
import org.litote.kmongo.coroutine.CoroutineCollection
import at.favre.lib.crypto.bcrypt.BCrypt
import io.github.cdimascio.dotenv.dotenv

class UserService {
    val dotenv = dotenv()
    private val usersCollectionName: String = dotenv["MONGODB_USERS_COLLECTION"] ?: "users"
    private val users: CoroutineCollection<User> = MongoClientFactory.database.getCollection(usersCollectionName)

    suspend fun registerUser(email: String, username: String, password: String): RegisterResult {
        val existingEmail = users.findOne(User::email eq email)
        val existingUsername = users.findOne(User::username eq username)

        if (existingEmail != null) return RegisterResult.EmailInUse
        if (existingUsername != null) return RegisterResult.UsernameInUse

        val hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray())
        val user = User(email = email, username = username, password = hashedPassword)
        users.insertOne(user)
        return RegisterResult.Success
    }

    suspend fun loginUser(identifier: String, password: String): LoginResult {
        val user = users.findOne(
            or(
                User::email eq identifier,
                User::username eq identifier
            )
        ) ?: return LoginResult.InvalidCredentials

        val result = BCrypt.verifyer().verify(password.toCharArray(), user.password)
        return if (result.verified) LoginResult.Success else LoginResult.InvalidCredentials
    }

    suspend fun deleteUserById(userId: String): Boolean {
        val result = users.deleteOneById(ObjectId(userId))
        return result.deletedCount > 0
    }

    suspend fun findByEmailOrUsername(identifier: String): User? {
        return users.findOne(
            or(User::email eq identifier, User::username eq identifier)
        )
    }
}

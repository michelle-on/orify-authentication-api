package com.kofastack.authapi.services

import com.kofastack.authapi.config.JwtConfig
import com.kofastack.authapi.models.User
import at.favre.lib.crypto.bcrypt.BCrypt

class AuthService(private val userService: UserService) {

    suspend fun login(identifier: String, password: String): String? {
        val user = userService.findByEmailOrUsername(identifier) ?: return null
        val result = BCrypt.verifyer().verify(password.toCharArray(), user.password)
        return if (result.verified) {
            JwtConfig.generateToken(user.uid.toHexString())
        } else {
            null
        }
    }
}

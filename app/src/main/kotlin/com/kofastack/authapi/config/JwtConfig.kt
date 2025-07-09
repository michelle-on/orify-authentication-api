package com.kofastack.authapi.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import java.util.Date
import io.github.cdimascio.dotenv.dotenv

object JwtConfig {
    val dotenv = dotenv()

    private val secret = dotenv["JWT_SECRET"] ?: error("JWT_SECRET not set")
    private val issuer = dotenv["JWT_ISSUER"] ?: error("JWT_ISSUER not set")
    private const val validityInMs = 36_000_00 * 24 // 24h

    private val algorithm = Algorithm.HMAC256(secret)

    fun generateToken(userId: String): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("userId", userId)
        .withExpiresAt(Date(System.currentTimeMillis() + validityInMs))
        .sign(algorithm)

    fun getVerifier() = JWT.require(algorithm).withIssuer(issuer).build()
}
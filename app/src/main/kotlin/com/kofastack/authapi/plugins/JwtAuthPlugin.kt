package com.kofastack.authapi.plugins

import com.auth0.jwt.exceptions.JWTVerificationException
import com.kofastack.authapi.config.JwtConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureAuthentication() {
    install(Authentication) {
        jwt {
            verifier(JwtConfig.getVerifier())
            validate {
                val userId = it.payload.getClaim("userId").asString()
                if (userId != null) JWTPrincipal(it.payload) else null
            }
        }
    }
}
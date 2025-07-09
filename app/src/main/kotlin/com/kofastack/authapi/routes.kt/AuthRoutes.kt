package com.kofastack.authapi.routes

import com.kofastack.authapi.services.UserService
import com.kofastack.authapi.models.results.RegisterResult
import com.kofastack.authapi.models.results.LoginResult
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.HttpStatusCode
import com.kofastack.authapi.models.validation.RegisterRequest
import com.kofastack.authapi.models.validation.LoginRequest
import kotlinx.serialization.Serializable

fun Route.authRoutes(userService: UserService) {
    route("/register") {
        post {
            try {
                val request = call.receive<RegisterRequest>()
                val (valid, message) = request.isValid()
                if (!valid) {
                    call.respond(HttpStatusCode.BadRequest, message)
                    return@post
                }
                when(userService.registerUser(request.email, request.username, request.password)) {
                    is RegisterResult.Success -> call.respondText("Usuário registrado com sucesso", status = HttpStatusCode.Created)
                    is RegisterResult.EmailInUse -> call.respondText("Email já está em uso", status = HttpStatusCode.Conflict)
                    is RegisterResult.UsernameInUse -> call.respondText("Nome de usuário indisponivel", status = HttpStatusCode.Conflict)
                }
            } catch (e: Exception) {
                call.respondText("Erro no servidor: ${e.message}", status = HttpStatusCode.InternalServerError)
            }
        }
    }

    route("/login") {
        post {
            try {
                val request = call.receive<LoginRequest>()
                when (userService.loginUser(request.identifier, request.password)) {
                    LoginResult.Success -> call.respondText("Login realizado com sucesso")
                    LoginResult.InvalidCredentials -> call.respondText("Credenciais inválidas", status = HttpStatusCode.Unauthorized)
                }
            } catch (e: Exception) {
                call.respondText("Erro no servidor: ${e.message}", status = HttpStatusCode.InternalServerError)
            }
        }
    }
}


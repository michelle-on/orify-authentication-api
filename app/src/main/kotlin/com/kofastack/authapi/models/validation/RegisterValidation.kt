package com.kofastack.authapi.models.validation

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val username: String,
    val password: String
) {
    fun isValid(): Pair<Boolean, String> {
        if (email.isBlank() || !isValidEmail(email)) {
            return false to "Email inválido, insira um email válido"
        }
        if (username.isBlank() || username.length < 3 || username.length > 20) {
            return false to "Nome de usuário inválido (mínimo 3 caracteres) e máximo 20 caracteres"
        }
        if (password.isBlank() || !isValidPassword(password)) {
            return false to "Senha deve ter pelo menos 6 caracteres, incluir um número e um caractere especial"
        }
        return true to ""
    }

    private fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return email.matches(emailRegex)
    }
    
    private fun isValidPassword(password: String): Boolean {
    val passwordRegex = "^(?=.*[0-9])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$".toRegex()
        return password.matches(passwordRegex)
    }
}

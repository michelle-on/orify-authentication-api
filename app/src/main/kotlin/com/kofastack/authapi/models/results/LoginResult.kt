package com.kofastack.authapi.models.results

sealed class LoginResult {
    object Success : LoginResult()
    object InvalidCredentials : LoginResult()
}

package com.kofastack.authapi.models.results

sealed class RegisterResult {
    object Success : RegisterResult()
    object EmailInUse : RegisterResult()
    object UsernameInUse : RegisterResult()
}

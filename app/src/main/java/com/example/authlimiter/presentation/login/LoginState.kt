package com.example.authlimiter.presentation.login

import com.example.authlimiter.data.model.LoginResponse

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val response: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}
package com.example.authlimiter.presentation.login

import com.example.authlimiter.data.model.AuthRequest

sealed class AuthIntent {
    data class Signup(val request: AuthRequest) : AuthIntent()
    data class Login(val request: AuthRequest) : AuthIntent()
}
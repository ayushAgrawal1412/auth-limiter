package com.example.authlimiter.data.model

data class LoginResponse(
    val token: String,
    val expiresIn: Long
)
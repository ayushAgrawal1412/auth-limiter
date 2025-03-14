package com.example.authlimiter.data.model

data class RateLimitResponse(
    val allowed: Boolean,
    val remainingAttempts: Int
)

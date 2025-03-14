package com.example.authlimiter.data.model

data class RateLimitRequest(
    val userId: String,
    val apiEndpoint: String
)
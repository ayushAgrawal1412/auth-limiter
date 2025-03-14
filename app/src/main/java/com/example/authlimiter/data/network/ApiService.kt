package com.example.authlimiter.data.network

import com.example.authlimiter.data.model.LoginRequest
import com.example.authlimiter.data.model.LoginResponse
import com.example.authlimiter.data.model.RateLimitRequest
import com.example.authlimiter.data.model.RateLimitResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("rate-limiter")
    suspend fun checkRateLimit(@Body request: RateLimitRequest): RateLimitResponse

    @POST("authenticate")
    suspend fun authenticate(@Body request: LoginRequest): LoginResponse
}
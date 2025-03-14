package com.example.authlimiter.data.network

import com.example.authlimiter.data.model.LoginRequest
import com.example.authlimiter.data.model.LoginResponse
import com.example.authlimiter.data.model.RateLimitRequest
import com.example.authlimiter.data.model.RateLimitResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/api/request")
    suspend fun checkRateLimit(
        @Query("clientID") clientID: String,
        @Body request: RateLimitRequest
    ): RateLimitResponse

    @POST("authenticate")
    suspend fun authenticate(@Body request: LoginRequest): LoginResponse
}
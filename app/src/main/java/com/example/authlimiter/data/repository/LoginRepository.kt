package com.example.authlimiter.data.repository

import com.example.authlimiter.data.model.LoginRequest
import com.example.authlimiter.data.model.RateLimitRequest
import com.example.authlimiter.data.network.ApiService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun checkRateLimit(clientID: String, request: RateLimitRequest) =
        apiService.checkRateLimit(clientID, request)
    suspend fun authenticate(request: LoginRequest) = apiService.authenticate(request)
}
package com.example.authlimiter.data.repository

import com.example.authlimiter.data.model.RateLimitRequest
import com.example.authlimiter.data.model.RateLimitResponse
import com.example.authlimiter.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RateLimitRepository(private val apiService: ApiService) {

    suspend fun checkRateLimit(request: RateLimitRequest): RateLimitResponse? {
        return withContext(Dispatchers.IO) {
            try {
                apiService.checkRateLimit(request)
            } catch (e: HttpException) {
                null
            }
        }
    }
}
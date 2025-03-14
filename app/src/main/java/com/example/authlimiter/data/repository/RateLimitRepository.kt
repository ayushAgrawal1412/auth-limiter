package com.example.authlimiter.data.repository

import com.example.authlimiter.data.model.RateLimitRequest
import com.example.authlimiter.data.model.RateLimitResponse
import com.example.authlimiter.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class RateLimitRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun checkRateLimit(clientID: String, request: RateLimitRequest): RateLimitResponse? {
        return withContext(Dispatchers.IO) {
            try {
                apiService.checkRateLimit(clientID, request)
            } catch (e: HttpException) {
                null
            }
        }
    }
}
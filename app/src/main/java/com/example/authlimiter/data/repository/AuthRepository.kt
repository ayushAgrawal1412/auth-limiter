package com.example.authlimiter.data.repository

import com.example.authlimiter.data.model.AuthRequest
import com.example.authlimiter.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun signup(request: AuthRequest): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.signup(request)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.success(body)
                    } else {
                        Result.failure(Exception("Empty response from server"))
                    }
                } else {
                    Result.failure(Exception("Signup failed: ${response.errorBody()?.string() 
                        ?: "Unknown error"}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun login(request: AuthRequest): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.login(request)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.success(body)
                    } else {
                        Result.failure(Exception("Empty response from server"))
                    }
                } else {
                    Result.failure(Exception("Login failed: ${response.errorBody()?.string() ?: "Unknown error"}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
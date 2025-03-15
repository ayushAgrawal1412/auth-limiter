package com.example.authlimiter.data.network

import com.example.authlimiter.data.model.AuthRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/signup")
    suspend fun signup(@Body request: AuthRequest): Response<String>

    @POST("auth/login")
    suspend fun login(@Body request: AuthRequest): Response<String>

}
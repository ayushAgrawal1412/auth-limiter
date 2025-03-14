package com.example.authlimiter.presentation.ratelimiter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authlimiter.data.model.RateLimitRequest
import com.example.authlimiter.data.repository.RateLimitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RateLimiterViewModel @Inject constructor(
    private val repository: RateLimitRepository
) : ViewModel() {

    fun checkRateLimit(userId: String, endpoint: String) {
        viewModelScope.launch {
            val request = RateLimitRequest(userId, endpoint)
            try {
                val response = repository.checkRateLimit("user-9", request)
                if (response != null) {
                    Log.d(
                        "RateLimiter",
                        "Allowed: ${response.allowed}, Remaining Attempts: ${response.remainingAttempts}"
                    )
                } else {
                    Log.e("RateLimiter", "Rate limit check failed")
                }
            } catch (e: Exception) {
                Log.e("RateLimiter", "Error: ${e.message}")
            }
        }
    }
}
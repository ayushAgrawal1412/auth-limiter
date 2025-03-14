package com.example.authlimiter.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authlimiter.data.model.LoginRequest
import com.example.authlimiter.data.model.RateLimitRequest
import com.example.authlimiter.data.repository.LoginRepository
import com.example.authlimiter.data.repository.RateLimitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val rateLimitRepository: RateLimitRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState = _loginState.asStateFlow()

    fun login(request: LoginRequest) = viewModelScope.launch {
        _loginState.value = LoginState.Loading

        // Check rate limit
        val result = rateLimitRepository.checkRateLimit(
            "user9",
            RateLimitRequest("test-user", "/authenticate")
        )

        if (result?.allowed == true) {
            try {
                val response = loginRepository.authenticate(request)
                _loginState.value = LoginState.Success(response)
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Login failed")
            }
        } else {
            _loginState.value = LoginState.Error("Rate limit exceeded!")
        }
    }
}
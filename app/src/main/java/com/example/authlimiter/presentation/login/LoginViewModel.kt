package com.example.authlimiter.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authlimiter.data.model.LoginRequest
import com.example.authlimiter.data.model.RateLimitRequest
import com.example.authlimiter.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState = _loginState.asStateFlow()

    fun login(request: LoginRequest) = viewModelScope.launch {
        val rateLimitResponse = repository.checkRateLimit(RateLimitRequest("test-user", "/authenticate"))
        if (rateLimitResponse.allowed) {
            _loginState.value = LoginState.Loading
            val response = repository.authenticate(request)
            _loginState.value = LoginState.Success(response)
        } else {
            _loginState.value = LoginState.Error("Rate limit exceeded!")
        }
    }
}
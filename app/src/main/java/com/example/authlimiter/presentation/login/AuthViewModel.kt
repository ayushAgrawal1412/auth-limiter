package com.example.authlimiter.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authlimiter.data.model.AuthRequest
import com.example.authlimiter.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> = _state.asStateFlow()

    private val intent = MutableSharedFlow<AuthIntent>(replay = 0)

    init {
        handleIntent()
    }

    fun sendIntent(authIntent: AuthIntent) {
        viewModelScope.launch {
            intent.emit(authIntent)
        }
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intent.collect { authIntent ->
                when (authIntent) {
                    is AuthIntent.Login -> handleLogin(authIntent.request)
                    is AuthIntent.Signup -> handleSignup(authIntent.request)
                }
            }
        }
    }

    private suspend fun handleLogin(request: AuthRequest) {
        _state.value = AuthState.Loading
        val result = authRepository.login(request)
        _state.value = if (result.isSuccess) {
            AuthState.Success(result.getOrNull().orEmpty())
        } else {
            AuthState.Error(result.exceptionOrNull()?.message ?: "Login failed")
        }
    }

    private suspend fun handleSignup(request: AuthRequest) {
        _state.value = AuthState.Loading
        val result = authRepository.signup(request)
        _state.value = if (result.isSuccess) {
            AuthState.Success(result.getOrNull().orEmpty())
        } else {
            AuthState.Error(result.exceptionOrNull()?.message ?: "Signup failed")
        }
    }
}
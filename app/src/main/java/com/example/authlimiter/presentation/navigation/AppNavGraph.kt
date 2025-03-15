package com.example.authlimiter.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.authlimiter.presentation.home.HomeScreen
import com.example.authlimiter.presentation.login.AuthScreen
import com.example.authlimiter.presentation.login.AuthViewModel

fun NavGraphBuilder.appNavGraph(navController: NavHostController) {
    composable("login") {
        val viewModel: AuthViewModel = hiltViewModel()
        AuthScreen(
            viewModel = viewModel,
            onAuthSuccess = {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        )
    }

    composable("home") {
        HomeScreen()
    }
}

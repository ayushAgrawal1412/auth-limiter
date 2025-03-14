package com.example.authlimiter.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.authlimiter.presentation.home.HomeScreen
import com.example.authlimiter.presentation.login.LoginScreen
import com.example.authlimiter.presentation.login.LoginViewModel

fun NavGraphBuilder.appNavGraph(navController: NavHostController) {
    composable("login") {
        val viewModel: LoginViewModel = hiltViewModel()
        LoginScreen(
            viewModel = viewModel,
            onLoginSuccess = {
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

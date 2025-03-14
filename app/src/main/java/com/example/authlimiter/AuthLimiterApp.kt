package com.example.authlimiter

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.authlimiter.presentation.navigation.appNavGraph
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AuthLimiterApp : Application()

@Composable
fun AuthLimiterAppContent() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        appNavGraph(navController)
    }
}
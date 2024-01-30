package com.example.mapsapptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mapsapptest.ui.theme.MapsAppTestTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<AuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapsAppTestTheme {
                Navigator()
            }
        }
    }

    @Composable
    private fun Navigator() {
        val navController = rememberNavController()
        viewModel.context = this
        viewModel.navigateTo = {
            navController.navigate(it)
        }

        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                LoginScreen(viewModel = viewModel)
            }
            composable("signUp") {
                SignUpScreen(viewModel = viewModel)
            }
            composable("session") {
                SessionScreen(viewModel = viewModel)
            }
        }
    }
}
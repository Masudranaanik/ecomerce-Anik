package com.example.ecomerce_anik.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecomerce_anik.ui.screens.*
import com.example.ecomerce_anik.ui.viewmodel.MainViewModel

@Composable
fun SetupNavGraph(navController: NavHostController) {
    val viewModel: MainViewModel = hiltViewModel()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(Screen.OrderSuccess.route) {
            OrderSuccessScreen(navController = navController)
        }
        composable(Screen.Admin.route) {
            AdminScreen(navController = navController, viewModel = viewModel)
        }
        composable("main_dashboard") {
            MainScreen(viewModel = viewModel, rootNavController = navController)
        }
    }
}

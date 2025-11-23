package com.example.elservice.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.elservice.ui.screens.auth.Dashboard
import com.example.elservice.ui.screens.auth.LoginScreen
import com.example.elservice.ui.screens.auth.RegitrationScreen

@Composable
fun AppNavHost(
	navController: NavHostController,
	modifier: Modifier = Modifier
) {

	NavHost(
		navController = navController,
		startDestination = "login"
	) {
		composable(AppRoute.Login.route) {
			LoginScreen(navController)
		}

		composable(AppRoute.Register.route) {
			RegitrationScreen(navController)
		}

		composable(AppRoute.Home.route) {
			Dashboard(navController)
		}
	}
}
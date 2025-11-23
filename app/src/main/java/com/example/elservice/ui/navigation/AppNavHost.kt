package com.example.elservice.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.elservice.ui.screens.auth.LoginScreen
import com.example.elservice.ui.screens.auth.ProfileScreen
import com.example.elservice.ui.screens.auth.RegitrationScreen
import com.example.elservice.ui.screens.dashboard.DashboardLoader

@Composable
fun AppNavHost(
	navController: NavHostController,
	modifier: Modifier = Modifier
) {
	val role = "customer"
	val currentRoute = navController.currentBackStackEntryFlow.collectAsState(initial = null).value?.destination?.route
	val showNavBar = currentRoute != null &&
			currentRoute != AppRoute.Login.route &&
			currentRoute != AppRoute.Register.route

	Scaffold(
		bottomBar = {
			if (showNavBar) {
				AppNavBar(
					role = role,
					currentRoute = currentRoute,
					onNavigate = { navController.navigate(it) }
				)
			}
		}
	) { innerPadding ->
		NavHost(
			navController = navController,
			startDestination = AppRoute.Login.route,
			modifier = modifier
				.padding(innerPadding)
				.fillMaxSize()
		) {
			composable(AppRoute.Login.route) {
				LoginScreen(navController)
			}

			composable(AppRoute.Register.route) {
				RegitrationScreen(navController)
			}

			composable(AppRoute.CustomerHome.route) {
				DashboardLoader(role = "customer", navController)
			}

			composable(AppRoute.TechnicianHome.route) {
				DashboardLoader(role = "technician", navController)
			}

			composable(AppRoute.Profile.route) {
				ProfileScreen(navController)
			}
		}
	}
}
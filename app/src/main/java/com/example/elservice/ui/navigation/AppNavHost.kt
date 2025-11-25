package com.example.elservice.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.elservice.core.di.AppContainer
import com.example.elservice.ui.screens.auth.LoginScreen
import com.example.elservice.ui.screens.auth.ProfileScreen
import com.example.elservice.ui.screens.auth.RegistrationScreen
import com.example.elservice.ui.screens.customer.DetailServiceScreen
import com.example.elservice.ui.screens.customer.ListServiceScreen
import com.example.elservice.ui.screens.customer.RequestServiceScreen
import com.example.elservice.ui.screens.dashboard.DashboardLoader

@Composable
fun AppNavHost(
	navController: NavHostController,
	appContainer: AppContainer,
	modifier: Modifier = Modifier
) {
	val tokenState = remember { mutableStateOf<String?>(null) }
	val roleState = remember { mutableStateOf<String?>(null) }
	val isLoading = remember { mutableStateOf(true) }

	val registrationViewModel = remember { appContainer.registerViewModelFactory() }

	// Ambil session saat pertama kali masuk
	LaunchedEffect(Unit) {
		val session = appContainer.getSessionUseCase()
		tokenState.value = session?.token
		roleState.value = session?.user?.role
		isLoading.value = false
	}

	if (isLoading.value) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(Color.White),
			contentAlignment = Alignment.Center
		) {
			CircularProgressIndicator(
				modifier = Modifier.size(50.dp)
			)
		}
		return
	}

	val startDestination = when {
		tokenState.value.isNullOrEmpty() -> AppRoute.Login.route
		roleState.value == "customer" -> AppRoute.CustomerHome.route
		roleState.value == "teknisi" -> AppRoute.TechnicianHome.route
		else -> AppRoute.Login.route
	}

	val currentBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = currentBackStackEntry?.destination?.route

	val showNavBar = currentRoute != AppRoute.Login.route &&
			currentRoute != AppRoute.Register.route

	Scaffold(
		bottomBar = {
			if (showNavBar) {
				AppNavBar(
					role = roleState.value ?: "",
					currentRoute = currentRoute,
					onNavigate = { navController.navigate(it) }
				)
			}
		}
	) { innerPadding ->
		NavHost(
			navController = navController,
			startDestination = startDestination,
			modifier = modifier
				.padding(innerPadding)
				.fillMaxSize()
		) {
			composable(AppRoute.Login.route) {
				LoginScreen(navController, appContainer)
			}

			composable(AppRoute.Register.route) {
				RegistrationScreen(navController, registrationViewModel)
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

			composable(AppRoute.ListService.route) {
				ListServiceScreen(navController)
			}

			composable(AppRoute.RequestService.route) {
				RequestServiceScreen(navController, appContainer)
			}

			composable(AppRoute.DetailService.route) {
				DetailServiceScreen(navController)
			}
		}
	}
}

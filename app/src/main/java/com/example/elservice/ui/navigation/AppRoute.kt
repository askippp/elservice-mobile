package com.example.elservice.ui.navigation

sealed class AppRoute(val route: String) {
	data object Login : AppRoute("login")
	data object Register : AppRoute("register")
	data object Profile : AppRoute("profile")

	data object CustomerHome : AppRoute("customer_home")
	data object TechnicianHome : AppRoute("technician_home")

	data object ListService : AppRoute("list_service")
	data object RequestService : AppRoute("request_service")
	data object DetailService : AppRoute("detail_service")
}
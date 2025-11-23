package com.example.elservice.ui.navigation

sealed class AppRoute(val route: String) {
	data object Login : AppRoute("login")
	data object Register : AppRoute("register")
	data object Home : AppRoute("home")
	data object Profile : AppRoute("profile")
}
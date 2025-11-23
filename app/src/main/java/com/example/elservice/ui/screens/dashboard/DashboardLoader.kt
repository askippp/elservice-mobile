package com.example.elservice.ui.screens.dashboard

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DashboardLoader(
	role: String,
	navController: NavHostController
) {
	when (role) {
		"teknisi" -> DashboardTeknisi(navController)
		"customer" -> DashboardCustomer(navController)
		else -> DashboardCustomer(navController)
	}
}
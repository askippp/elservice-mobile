package com.example.elservice.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
	val route: String,
	val label: String,
	val icon: ImageVector
)

val teknisiNavItems = listOf(
	NavItem("technician_home", "Home", Icons.Filled.Home),
	NavItem("profile", "Profile", Icons.Filled.Person),
)

val customerNavItems = listOf(
	NavItem("list_service", "Services", Icons.Filled.Build),
	NavItem("customer_home", "Home", Icons.Filled.Home),
	NavItem("profile", "Profile", Icons.Filled.Person),
)

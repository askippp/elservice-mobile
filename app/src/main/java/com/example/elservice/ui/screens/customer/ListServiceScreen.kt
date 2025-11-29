package com.example.elservice.ui.screens.customer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elservice.ui.components.buttons.MainButton
import com.example.elservice.ui.components.cards.ServiceCard
import com.example.elservice.ui.components.header.HeaderApp
import com.example.elservice.ui.components.texts.BodyText
import com.example.elservice.ui.navigation.AppRoute
import com.example.elservice.ui.screens.customer.viewmodel.ListServiceViewModel

@Composable
fun ListServiceScreen(
	navController: NavHostController,
	viewModel: ListServiceViewModel
) {
	val state by viewModel.state.collectAsState()

	Column(
		horizontalAlignment = Alignment.Start,
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
			.padding(horizontal = 16.dp)
	) {
		HeaderApp()

		MainButton(
			label = "Request Service",
			onClick = { navController.navigate("request_service") },
		)

		Spacer(modifier = Modifier.height(16.dp))

		BodyText("List of Your Services")

		HorizontalDivider(
			thickness = 2.dp,
			color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
			modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
		)

		when {
			state.loading -> {
				Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
					CircularProgressIndicator()
				}
			}
			state.error != null -> {
				BodyText("Error: ${state.error}")
			}
			else -> {
				LazyColumn(
					verticalArrangement = Arrangement.spacedBy(12.dp),
					modifier = Modifier.fillMaxSize()
				) {
					items(state.services) { service ->
						ServiceCard(
							namaAlat = service.alatList,
							tanggalMasuk = null,
							status = service.status,
							totalBiaya = service.totalBiaya,
							onClick = {
								navController.navigate(
									AppRoute.DetailService.createRoute(service.id)
								)
								Log.d("ListServiceScreen", "Navigating to detail for service ID: ${service.id}")
							}
						)
					}
				}
			}
		}
	}
}
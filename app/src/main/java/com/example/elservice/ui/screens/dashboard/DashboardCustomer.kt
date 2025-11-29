package com.example.elservice.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elservice.ElServiceApp
import com.example.elservice.R
import com.example.elservice.ui.components.cards.ServiceCard
import com.example.elservice.ui.components.carousel.AutoImageCarousel
import com.example.elservice.ui.components.header.HeaderApp
import com.example.elservice.ui.components.texts.BodyText
import com.example.elservice.ui.components.texts.SubtitleText
import com.example.elservice.ui.components.texts.TitleText
import com.example.elservice.ui.navigation.AppRoute
import com.example.elservice.ui.screens.customer.viewmodel.ListServiceViewModel

@Composable
fun DashboardCustomer(navController: NavHostController, ) {
	val context = LocalContext.current
	val appContainer = (context.applicationContext as ElServiceApp).appContainer
	val getSessionUseCase = appContainer.getSessionUseCase
	val getListServiceUseCase = appContainer.getListServiceUseCase

	var username by remember { mutableStateOf("Unknown") }
	var token by remember { mutableStateOf<String?>(null) }
	val listServiceViewModelState = remember { mutableStateOf<ListServiceViewModel?>(null) }

	// Ambil session & buat ViewModel setelah token tersedia
	LaunchedEffect(Unit) {
		val session = getSessionUseCase()
		username = session?.user?.username ?: "Unknown"
		token = session?.token

		token?.let {
			listServiceViewModelState.value = ListServiceViewModel(getListServiceUseCase, it)
		}
	}

	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
			.padding(horizontal = 16.dp)
	) {
		Spacer(modifier = Modifier.height(12.dp))

		HeaderApp()

		Spacer(modifier = Modifier.height(12.dp))

		// Carousel tetap
		AutoImageCarousel(
			images = listOf(
				R.drawable.service_photo,
				R.drawable.service_photo_two,
				R.drawable.service_photo_three
			)
		)

		Spacer(modifier = Modifier.height(24.dp))

		Column(
			horizontalAlignment = Alignment.Start,
			modifier = Modifier.fillMaxWidth()
		) {
			TitleText("Hello, $username")
			SubtitleText("Here are your services")
		}

		Spacer(modifier = Modifier.height(16.dp))

		HorizontalDivider(
			thickness = 1.dp,
			color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
		)

		Spacer(modifier = Modifier.height(12.dp))

		// List service
		if (listServiceViewModelState.value == null) {
			Box(
				modifier = Modifier.fillMaxSize(),
				contentAlignment = Alignment.Center
			) {
				CircularProgressIndicator()
			}
		} else {
			val state by listServiceViewModelState.value!!.state.collectAsState()

			when {
				state.loading -> {
					Box(
						modifier = Modifier.fillMaxSize(),
						contentAlignment = Alignment.Center
					) {
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
								tanggalMasuk = service.tanggalMasuk,
								status = service.status,
								totalBiaya = service.totalBiaya,
								onClick = {
									navController.navigate(
										AppRoute.DetailService.createRoute(service.id)
									)
								}
							)
						}
					}
				}
			}
		}
	}
}


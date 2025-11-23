package com.example.elservice.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elservice.R
import com.example.elservice.ui.components.cards.ServiceCard
import com.example.elservice.ui.components.carousel.AutoImageCarousel
import com.example.elservice.ui.components.header.HeaderApp
import com.example.elservice.ui.components.texts.SubtitleText
import com.example.elservice.ui.components.texts.TitleText

@Composable
fun DashboardCustomer(
	navController: NavHostController,
	modifier: Modifier = Modifier
) {
	var username by remember { mutableStateOf<String?>(null) }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
			.padding(horizontal = 16.dp)
	) {
		Spacer(modifier = Modifier.height(12.dp))

		HeaderApp(
			username = "John Doe",
			photo = null,
			navController = navController
		)

		Spacer(modifier = Modifier.height(12.dp))

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
			TitleText("Hello, John Doe")
			SubtitleText("Here are your services")
		}

		Spacer(modifier = Modifier.height(16.dp))

		HorizontalDivider(
			thickness = 1.dp,
			color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
		)

		Spacer(modifier = Modifier.height(12.dp))

		LazyColumn(
			verticalArrangement = Arrangement.spacedBy(12.dp),
			modifier = Modifier.weight(1f)
		) {
			items(5) { index ->
				ServiceCard(
					namaAlat = listOf("AC Panasonic", "Kulkas LG", "Kipas Angin"),
					tanggalMasuk = null,
					totalBiaya = 150000f,
					status = if (index % 2 == 0) "dalam_proses" else "menunggu",
					onClick = { navController.navigate("detail") }
				)
			}
		}
	}
}

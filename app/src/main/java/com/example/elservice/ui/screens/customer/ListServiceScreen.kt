package com.example.elservice.ui.screens.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elservice.ui.components.buttons.MainButton
import com.example.elservice.ui.components.cards.ServiceCard
import com.example.elservice.ui.components.header.HeaderApp
import com.example.elservice.ui.components.texts.BodyText

@Composable
fun ListServiceScreen(navController: NavHostController) {
	Column(
		horizontalAlignment = Alignment.Start,
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
			.background(MaterialTheme.colorScheme.background)
			.padding(horizontal = 16.dp)
	) {
		HeaderApp()

		MainButton(
			label = "Request Service",
			onClick = { navController.navigate("request_service") }
		)

		Spacer(modifier = Modifier.height(16.dp))

		BodyText("List of Your Services")

		HorizontalDivider(
			thickness = 2.dp,
			color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
			modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
		)

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
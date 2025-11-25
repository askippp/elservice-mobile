package com.example.elservice.ui.screens.customer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elservice.core.di.AppContainer
import com.example.elservice.ui.components.cards.AlatCard
import com.example.elservice.ui.components.header.HeaderApp
import com.example.elservice.ui.components.texts.BodyText
import com.example.elservice.ui.components.texts.CaptionText
import com.example.elservice.ui.components.texts.TitleText
import com.example.elservice.ui.screens.customer.viewmodel.RequestServiceViewModel

@Composable
fun RequestServiceScreen(
	navController: NavHostController,
	appContainer: AppContainer
) {
	val viewModel = remember {
		RequestServiceViewModel(appContainer.getAlatListUseCase)
	}

	val state = viewModel.state.collectAsState()

	Column(
		horizontalAlignment = Alignment.Start,
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
			.background(MaterialTheme.colorScheme.background)
			.padding(horizontal = 16.dp)
	) {
		HeaderApp()

		TitleText("Request Your Service")

		Spacer(modifier = Modifier.height(32.dp))

		if (state.value.loading) {
			CaptionText("Loading...")
			return@Column
		}

		state.value.error?.let {
			CaptionText("Error: $it")
			Log.e("RequestServiceScreen", "Error: $it")
			return@Column
		}

		BodyText("Choose Your Service")

		Spacer(modifier = Modifier.height(8.dp))

		FlowRow(
			maxItemsInEachRow = 4,
			modifier = Modifier
				.fillMaxWidth()
				.background(
					MaterialTheme.colorScheme.surfaceContainer,
					MaterialTheme.shapes.medium
				)
				.padding(vertical = 8.dp, horizontal = 12.dp)
			,
			horizontalArrangement = Arrangement.spacedBy(12.dp),
			verticalArrangement = Arrangement.spacedBy(12.dp)
		) {
			state.value.alatList.forEach { alat ->
				AlatCard(
					image = alat.foto,
					namaAlat = alat.namaAlat,
					isSelected = false,
					onClick = { }
				)
			}
		}
	}
}
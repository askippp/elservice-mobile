package com.example.elservice.ui.screens.customer

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elservice.ElServiceApp
import com.example.elservice.common.InputType
import com.example.elservice.core.di.AppContainer
import com.example.elservice.domain.usecase.alat.GetAlatListUseCase
import com.example.elservice.domain.usecase.service.CreateServiceUseCase
import com.example.elservice.ui.components.buttons.HelperButton
import com.example.elservice.ui.components.buttons.MainButton
import com.example.elservice.ui.components.cards.AlatCard
import com.example.elservice.ui.components.fields.InputField
import com.example.elservice.ui.components.header.HeaderApp
import com.example.elservice.ui.components.texts.BodyText
import com.example.elservice.ui.components.texts.CaptionText
import com.example.elservice.ui.components.texts.ErrorText
import com.example.elservice.ui.components.texts.TitleText
import com.example.elservice.ui.screens.customer.viewmodel.RequestServiceViewModel

@Composable
fun RequestServiceScreen(navController: NavHostController, ) {
	val context = LocalContext.current
	val appContainer = (context.applicationContext as ElServiceApp).appContainer
	val getSessionUseCase = appContainer.getSessionUseCase

	var sessionToken by remember { mutableStateOf<String?>(null) }
	var sessionCustomerId by remember { mutableStateOf<Int?>(null) }

	LaunchedEffect(Unit) {
		val session = getSessionUseCase()
		sessionToken = session?.token
		sessionCustomerId = session?.detail?.id
	}

	if (sessionToken == null || sessionCustomerId == null) {
		CircularProgressIndicator()
		return
	}

	val viewModel = remember {
		appContainer.requestServiceViewModelFactory(sessionToken!!)
	}


	val state = viewModel.state.collectAsState()

	var keluhan by remember { mutableStateOf("") }
	var alamat by remember { mutableStateOf("") }
	var alamatOriginal by remember { mutableStateOf("") }
	var selectedAlatIds by remember { mutableStateOf(mutableSetOf<Int>()) }

	var isEditAlamat by remember { mutableStateOf(false) }

	LaunchedEffect(Unit) {
		val session = getSessionUseCase()
		val alamatCustomer = session?.detail?.alamat ?: "Unknown User"

		alamat = alamatCustomer
		alamatOriginal = alamatCustomer
	}

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

		BodyText("Choose Your Service")

		Spacer(modifier = Modifier.height(4.dp))

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
			if(state.value.loading) {
				CaptionText("Loading...")
			}

			state.value.error?.let {
				ErrorText("Error: $it")
				Log.e("RequestServiceScreen", "Error: $it")
			}

			state.value.alatList.forEach { alat ->
				val isSelected = selectedAlatIds.contains(alat.id)

				AlatCard(
					image = alat.foto,
					namaAlat = alat.namaAlat,
					isSelected = isSelected,
					onClick = {
						val newIds = selectedAlatIds.toMutableSet().apply {
							if (isSelected) remove(alat.id) else add(alat.id)
						}
						selectedAlatIds = newIds

						viewModel.toggleAlatSelection(alat.id)
					}
				)
			}
		}

		Spacer(modifier = Modifier.height(16.dp))

		BodyText("Your Complaint")

		Box(
			modifier = Modifier
				.fillMaxWidth()
				.background(
					MaterialTheme.colorScheme.surfaceContainer,
					MaterialTheme.shapes.medium
				)
				.padding(vertical = 8.dp, horizontal = 12.dp)
				.padding(bottom = 4.dp)
		) {
			InputField(
				label = "Complaint",
				type = InputType.Text,
				value = keluhan,
				onValueChange = { keluhan = it },
			)
		}

		Spacer(modifier = Modifier.height(16.dp))

		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 4.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.Bottom
		) {
			BodyText("Your Address")

			HelperButton(
				label = if (isEditAlamat) "Cancel" else "Edit",
				onClick = {
					if (isEditAlamat) {
						alamat = alamatOriginal
					}
					isEditAlamat = !isEditAlamat
				}
			)
		}

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.background(
					MaterialTheme.colorScheme.surfaceContainer,
					MaterialTheme.shapes.medium
				)
				.padding(vertical = 8.dp, horizontal = 12.dp)
				.padding(bottom = 4.dp)
		) {
			InputField(
				label = "Address",
				type = InputType.Text,
				isEnable = isEditAlamat,
				value = alamat,
				onValueChange = { alamat = it }
			)
		}

		Spacer(modifier = Modifier.height(8.dp))

		Box(
			contentAlignment = Alignment.CenterEnd,
			modifier = Modifier.fillMaxWidth()
		) {
			Row(modifier = Modifier.width(180.dp)) {
				MainButton(
					label = "Request Service",
					onClick = {
						val customerId = sessionCustomerId ?: run {
							Toast.makeText(context, "Session error", Toast.LENGTH_SHORT).show()
							return@MainButton
						}

						if (viewModel.selectedAlatIds.isEmpty()) {
							Toast.makeText(context, "Pilih minimal 1 alat", Toast.LENGTH_SHORT).show()
							return@MainButton
						}

						viewModel.submitRequest(
							customerId = customerId,
							keluhan = keluhan,
							alamatService = alamat,
							onSuccess = {
								Toast.makeText(context, "Request terkirim", Toast.LENGTH_SHORT).show()
								navController.popBackStack()
							},
							onError = { err ->
								Toast.makeText(context, err, Toast.LENGTH_SHORT).show()
							}
						)
					}
				)
			}
		}
	}
}
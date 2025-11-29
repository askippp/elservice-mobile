package com.example.elservice.ui.screens.customer

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elservice.ElServiceApp
import com.example.elservice.common.InputType
import com.example.elservice.core.network.ApiClient
import com.example.elservice.ui.components.buttons.ErrorButton
import com.example.elservice.ui.components.cards.AlatCard
import com.example.elservice.ui.components.fields.InputField
import com.example.elservice.ui.components.header.HeaderApp
import com.example.elservice.ui.components.texts.BodyText
import com.example.elservice.ui.components.texts.CaptionText
import com.example.elservice.ui.components.texts.TitleText
import com.example.elservice.utils.formatTanggal

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailServiceScreen(
	navController: NavHostController,
	serviceId: Int
) {
	val context = LocalContext.current
	val appContainer = (context.applicationContext as ElServiceApp).appContainer
	val getSessionUseCase = appContainer.getSessionUseCase

	val token by produceState<String?>(initialValue = null) {
		val session = getSessionUseCase()
		value = session?.token
	}

	if (token == null) {
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			CircularProgressIndicator()
		}
		return
	}

	val viewModel = remember(token, serviceId) {
		appContainer.detailServiceViewModelFactory(token!!, serviceId)
	}

	val state by viewModel.state.collectAsState()
	val cancelState by viewModel.cancelState.collectAsState()
	val isLoading by viewModel.isLoading.collectAsState()
	val error by viewModel.error.collectAsState()

	val service = state.service

	var keluhan by remember { mutableStateOf("") }
	var alamat by remember { mutableStateOf("") }

	Column(
		horizontalAlignment = Alignment.Start,
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
			.background(MaterialTheme.colorScheme.background)
			.padding(horizontal = 16.dp)
	) {
		HeaderApp()

		TitleText(
			text = when {
				state.loading -> "Loading Service Details..."
				state.error != null -> "Service Details"
				service != null -> "Service Details"
				else -> "Service Details"
			}
		)

		state.error?.let {
			Spacer(modifier = Modifier.height(16.dp))
			CaptionText("Error: $it")
		}

		service?.let { s ->
			Spacer(modifier = Modifier.height(24.dp))

			Box(
				modifier = Modifier
					.fillMaxWidth()
					.background(
						MaterialTheme.colorScheme.surfaceContainer,
						MaterialTheme.shapes.medium
					)
					.padding(16.dp)
			) {
				Column {
					Row(
						modifier = Modifier.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween
					) {
						Column(modifier = Modifier.weight(1f)) {
							CaptionText("Status")
							BodyText(s.status ?: "-")
						}
						Column(modifier = Modifier.weight(1f)) {
							CaptionText("Service ID")
							BodyText("#${s.id}")
						}
					}

					Spacer(modifier = Modifier.height(12.dp))

					Row(
						modifier = Modifier.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween
					) {
						Column(modifier = Modifier.weight(1f)) {
							CaptionText("Tanggal Masuk")
							BodyText(formatTanggal(s.tanggal_masuk))
						}
						Column(modifier = Modifier.weight(1f)) {
							CaptionText("Tanggal Selesai")
							BodyText(formatTanggal(s.tanggal_selesai))
						}
					}
				}
			}

			Spacer(modifier = Modifier.height(16.dp))

			BodyText("Service Equipment")
			Spacer(modifier = Modifier.height(4.dp))

			FlowRow(
				maxItemsInEachRow = 4,
				modifier = Modifier
					.fillMaxWidth()
					.background(
						MaterialTheme.colorScheme.surfaceContainer,
						MaterialTheme.shapes.medium
					)
					.padding(vertical = 8.dp, horizontal = 12.dp),
				horizontalArrangement = Arrangement.spacedBy(12.dp),
				verticalArrangement = Arrangement.spacedBy(12.dp)
			) {
				s.alats.orEmpty().forEach { alat ->
					AlatCard(
						image = alat.foto,
						namaAlat = alat.nama_alat ?: "-",
						isSelected = true,
						onClick = {}
					)
				}
			}

			Spacer(modifier = Modifier.height(16.dp))

			BodyText("Complaint Details")
			Spacer(modifier = Modifier.height(4.dp))
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
					value = s.keluhan ?: "-",
					isEnable = false,
					onValueChange = { keluhan = it }
				)
			}

			Spacer(modifier = Modifier.height(16.dp))

			BodyText("Service Address")
			Spacer(modifier = Modifier.height(4.dp))
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
					label = "Address",
					type = InputType.Text,
					value = s.alamat_service ?: "-",
					isEnable = false,
					onValueChange = { alamat = it }
				)
			}

			Spacer(modifier = Modifier.height(16.dp))

			BodyText("Spareparts Used")
			Spacer(modifier = Modifier.height(4.dp))
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.background(
						MaterialTheme.colorScheme.surfaceContainer,
						MaterialTheme.shapes.medium
					)
					.padding(16.dp)
			) {
				if (s.spareparts.isEmpty()) {
					CaptionText("No spareparts used")
				} else {
					Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
						s.spareparts.forEach { sp ->
							Row(
								modifier = Modifier.fillMaxWidth(),
								horizontalArrangement = Arrangement.SpaceBetween
							) {
								BodyText(sp.nama ?: "-")
								CaptionText("x1")
							}
							if (sp != s.spareparts.last()) {
								HorizontalDivider(
									modifier = Modifier.padding(vertical = 4.dp),
									color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
								)
							}
						}
					}
				}
			}

			Spacer(modifier = Modifier.height(16.dp))

			BodyText("Payment Information")
			Spacer(modifier = Modifier.height(4.dp))
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.background(
						MaterialTheme.colorScheme.surfaceContainer,
						MaterialTheme.shapes.medium
					)
					.padding(16.dp)
			) {
				Column {
					Row(
						modifier = Modifier.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween
					) {
						CaptionText("Service Cost")
						BodyText("Rp ${s.biaya_service ?: "0"}")
					}

					Spacer(modifier = Modifier.height(8.dp))

					Row(
						modifier = Modifier.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween
					) {
						CaptionText("Spareparts Cost")
						BodyText("Rp ${if (s.spareparts.isEmpty()) "0" else "50.000"}")
					}

					Spacer(modifier = Modifier.height(8.dp))
					HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
					Spacer(modifier = Modifier.height(8.dp))

					Row(
						modifier = Modifier.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween
					) {
						BodyText("Total")
						BodyText("Rp ${s.total_biaya ?: "0"}")
					}
				}
			}

			if (s.status?.lowercase() == "menunggu") {
				Spacer(modifier = Modifier.height(16.dp))

				Button(
					onClick = {
						viewModel.cancelService()

						Log.d("API", "Token: $token")
						Log.d("API", "Service ID: $serviceId")
						Log.d("API", "Cancel URL: ${ApiClient.api}service/customer/$serviceId/cancel")
					},
					enabled = !isLoading,
					colors = ButtonDefaults.buttonColors(
						containerColor = MaterialTheme.colorScheme.errorContainer
					),
					modifier = Modifier.fillMaxWidth()
				) {
					if (isLoading) {
						CircularProgressIndicator(
							modifier = Modifier
								.size(20.dp)
								.padding(end = 8.dp),
							strokeWidth = 2.dp,
							color = Color.White
						)
					}
					Text("Cancel Service", color = Color.White)
				}

				cancelState?.message?.let {
					Spacer(modifier = Modifier.height(8.dp))
					Text(
						text = it,
						color = Color.Green
					)
				}

				error?.let {
					Spacer(modifier = Modifier.height(8.dp))
					Text(
						text = it,
						color = Color.Red
					)
				}
			}

			Spacer(modifier = Modifier.height(16.dp))
		}
	}
}
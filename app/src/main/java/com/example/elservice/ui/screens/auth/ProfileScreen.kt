package com.example.elservice.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.elservice.ElServiceApp
import com.example.elservice.R
import com.example.elservice.common.InputType
import com.example.elservice.data.local.City
import com.example.elservice.data.local.Province
import com.example.elservice.data.local.RegionProvider
import com.example.elservice.ui.components.buttons.ErrorButton
import com.example.elservice.ui.components.fields.InputField
import com.example.elservice.ui.components.fields.SearchableDropDownField
import com.example.elservice.ui.components.header.HeaderApp
import com.example.elservice.ui.components.texts.BodyText
import com.example.elservice.ui.components.texts.LabelText
import com.example.elservice.ui.components.texts.TitleText
import com.example.elservice.ui.navigation.AppRoute
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavHostController) {
	val context = LocalContext.current
	val appContainer = (context.applicationContext as ElServiceApp).appContainer
	val getSessionUseCase = appContainer.getSessionUseCase
	val clearSessionUseCase = appContainer.clearSessionUseCase
	val scope = rememberCoroutineScope()

	var photoUrl by remember { mutableStateOf<String?>(null) }
	var username by remember { mutableStateOf("") }
	var email by remember { mutableStateOf("") }

	var nama by remember { mutableStateOf("") }
	var noTelp by remember { mutableStateOf("") }
	var alamat by remember { mutableStateOf("") }

	val provinces = RegionProvider.provincesList()
	var selectedProvince by remember { mutableStateOf<Province?>(null) }

	val cities = remember(selectedProvince) {
		selectedProvince?.let { RegionProvider.citiesByProvince(it.id) } ?: emptyList()
	}
	var selectedCity by remember { mutableStateOf<City?>(null) }

	LaunchedEffect(Unit) {
		val session = getSessionUseCase()

		username = session?.user?.username ?: "Unknown User"
		email = session?.user?.email ?: "Unknown User"

		nama = session?.detail?.nama ?: "Unknown User"
		noTelp = session?.detail?.telp ?: "Unknown User"
		alamat = session?.detail?.alamat ?: "Unknown User"

		selectedProvince = provinces.find { it.name == session?.detail?.provinsi }

		selectedCity = selectedProvince
			?.let { RegionProvider.citiesByProvince(it.id) }
			?.find { it.name == session?.detail?.kota }
	}

	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
			.background(MaterialTheme.colorScheme.background)
			.padding(horizontal = 16.dp)
	) {
		HeaderApp()
		Spacer(modifier = Modifier.height(16.dp))

		AsyncImage(
			model = photoUrl ?: R.drawable.ic_launcher_foreground,
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.size(110.dp)
				.clip(CircleShape)
				.background(MaterialTheme.colorScheme.primary)
				.clickable { /* TODO: Update photo */ }
		)

		Spacer(modifier = Modifier.height(12.dp))

		TitleText(username)
		LabelText(email)

		Spacer(modifier = Modifier.height(30.dp))

		Column(modifier = Modifier.fillMaxWidth()) {

			BodyText("Account Info")
			Spacer(modifier = Modifier.height(4.dp))

			Column(
				verticalArrangement = Arrangement.spacedBy(8.dp),
				modifier = Modifier
					.fillMaxWidth()
					.background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
					.padding(16.dp)
			) {
				InputField(
					label = "Username",
					value = username,
					onValueChange = { username = it }
				)

				InputField(
					label = "Email",
					value = email,
					onValueChange = { email = it }
				)
			}

			Spacer(modifier = Modifier.height(16.dp))

			BodyText("Personal Info")
			Spacer(modifier = Modifier.height(4.dp))

			Column(
				verticalArrangement = Arrangement.spacedBy(8.dp),
				modifier = Modifier
					.fillMaxWidth()
					.background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
					.padding(16.dp)
			) {
				InputField(
					label = "Full Name",
					value = nama,
					onValueChange = { nama = it }
				)

				InputField(
					label = "Phone Number",
					value = noTelp,
					onValueChange = { noTelp = it },
					type = InputType.Number
				)

				SearchableDropDownField(
					label = "Province",
					selectedItem = selectedProvince,
					items = provinces,
					itemLabel = { it.name },
					onItemSelected = {
						selectedProvince = it
						selectedCity = null
					},
					prefixIcon = {
						Icon(Icons.Default.LocationOn, contentDescription = null)
					}
				)

				SearchableDropDownField(
					label = "City",
					selectedItem = selectedCity,
					items = cities,
					itemLabel = { it.name },
					onItemSelected = { selectedCity = it },
					prefixIcon = {
						Icon(Icons.Default.LocationCity, contentDescription = null)
					}
				)

				InputField(
					label = "Address",
					value = alamat,
					onValueChange = { alamat = it }
				)
			}

			Spacer(modifier = Modifier.height(30.dp))

			ErrorButton(
				label = "Logout",
				onClick = {
					scope.launch {
						clearSessionUseCase()
						navController.navigate(AppRoute.Login.route) {
							popUpTo(0) { inclusive = true }
						}
					}
				}
			)
		}
	}
}

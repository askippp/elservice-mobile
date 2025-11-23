package com.example.elservice.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
import com.example.elservice.ui.components.texts.SubtitleText
import com.example.elservice.ui.components.texts.TitleText

@Composable
fun ProfileScreen(navController: NavHostController) {
	var username by remember { mutableStateOf<String?>(null) }
	var email by remember { mutableStateOf<String?>(null) }
	var password by remember { mutableStateOf<String?>(null) }
	var photo by remember { mutableStateOf<Int?>(null) }

	var nama by remember { mutableStateOf<String?>(null) }
	var noTelp by remember { mutableStateOf<String?>(null) }
	val listProvinsi = RegionProvider.provincesList()
	var provinsi by remember { mutableStateOf<Province?>(null) }
	val listKota = remember(provinsi) {
		provinsi?.let { RegionProvider.citiesByProvince(it.id) } ?: emptyList()
	}
	var kota by remember { mutableStateOf<City?>(null) }
	var alamat by remember { mutableStateOf<String?>(null) }

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

		Image(
			painter = painterResource(photo ?: R.drawable.ic_launcher_foreground),
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.padding(start = 8.dp)
				.size(100.dp)
				.clip(CircleShape)
				.background(MaterialTheme.colorScheme.primary)
				.clickable(
					enabled = true,
					onClick = { /*TODO*/ }
				)
		)

		Spacer(modifier = Modifier.height(12.dp))

		TitleText("John Doe")

		LabelText("John Doe Muslim")

		Spacer(modifier = Modifier.height(30.dp))

		Column(
			horizontalAlignment = Alignment.Start,
			modifier = Modifier.fillMaxWidth()
		) {
			BodyText("Account Info")

			Spacer(modifier = Modifier.height(4.dp))

			Column(
				verticalArrangement = Arrangement.spacedBy(8.dp),
				modifier = Modifier
					.fillMaxWidth()
					.background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
					.padding(horizontal = 16.dp, vertical = 8.dp)
					.padding(bottom = 8.dp)
			) {
				InputField(
					label = "Username",
					value = username ?: "John Doe Muslim",
					onValueChange = { username = it },
				)

				InputField(
					label = "Email",
					value = email ?: "john@example.com",
					onValueChange = { email = it },
				)

				InputField(
					label = "Password",
					type = InputType.Password,
					value = password ?: "password",
					onValueChange = { password = it },
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
					.padding(horizontal = 16.dp, vertical = 8.dp)
					.padding(bottom = 8.dp)
			) {
				InputField(
					label = "Full Name",
					placeholder = "Enter your full name",
					value = nama ?: "John Doe",
					type = InputType.Text,
					onValueChange = { nama = it }
				)

				InputField(
					label = "Phone Number",
					placeholder = "Enter your phone number",
					value = noTelp.toString(),
					type = InputType.Number,
					onValueChange = { noTelp }
				)

				SearchableDropDownField(
					label = "Province",
					selectedItem = provinsi,
					items = listProvinsi,
					itemLabel = { it.name },
					onItemSelected = { provinsi = it },
					prefixIcon = {
						Icon(Icons.Default.LocationOn, contentDescription = null)
					}
				)

				SearchableDropDownField(
					label = "City",
					selectedItem = kota,
					items = listKota,
					itemLabel = { it.name },
					onItemSelected = { kota = it },
					prefixIcon = {
						Icon(Icons.Default.LocationCity, contentDescription = null)
					}
				)

				InputField(
					label = "Address",
					placeholder = "Enter your home address",
					value = alamat ?: "Bekasi",
					onValueChange = { alamat = it }
				)
			}

			Spacer(modifier = Modifier.height(30.dp))

			ErrorButton(
				label = "Logout",
				onClick = {  }
			)
		}
	}
}
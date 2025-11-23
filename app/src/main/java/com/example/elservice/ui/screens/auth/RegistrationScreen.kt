package com.example.elservice.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elservice.R
import com.example.elservice.common.InputType
import com.example.elservice.data.local.City
import com.example.elservice.data.local.Province
import com.example.elservice.data.local.RegionProvider
import com.example.elservice.ui.components.buttons.MainButton
import com.example.elservice.ui.components.fields.InputField
import com.example.elservice.ui.components.fields.SearchableDropDownField
import com.example.elservice.ui.components.links.LinkToAnotherScreen
import com.example.elservice.ui.components.texts.TitleText

@Composable
fun RegitrationScreen(navController: NavHostController) {
	var nama by remember { mutableStateOf("") }
	var noTelp by remember { mutableStateOf("") }

	val listProvinsi = RegionProvider.provincesList()
	var provinsi by remember { mutableStateOf<Province?>(null) }

	val listKota = remember(provinsi) {
		provinsi?.let { RegionProvider.citiesByProvince(it.id) } ?: emptyList()
	}
	var kota by remember { mutableStateOf<City?>(null) }

	var alamat by remember { mutableStateOf("") }
	var email by remember { mutableStateOf("") }
	var username by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	var confirmPassword by remember { mutableStateOf("") }

	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
			.padding(16.dp)
			.verticalScroll(rememberScrollState())
	) {
		Image(
			painter = painterResource(id = R.drawable.login_picture),
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.fillMaxWidth()
				.height(300.dp)
		)

		Spacer(modifier = Modifier.height(16.dp))

		TitleText("Please, Coming In!")

		Spacer(modifier = Modifier.height(16.dp))

		InputField(
			label = "Full Name",
			placeholder = "Enter your full name",
			value = nama,
			type = InputType.Text,
			onValueChange = { nama = it }
		)

		Spacer(modifier = Modifier.height(8.dp))

		InputField(
			label = "Phone Number",
			placeholder = "Enter your phone number",
			value = noTelp.toString(),
			type = InputType.Number,
			onValueChange = { noTelp }
		)

		Spacer(modifier = Modifier.height(8.dp))

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

		Spacer(modifier = Modifier.height(8.dp))

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

		Spacer(modifier = Modifier.height(8.dp))

		InputField(
			label = "Address",
			placeholder = "Enter your home address",
			value = alamat,
			onValueChange = { alamat = it }
		)

		Spacer(modifier = Modifier.height(8.dp))

		InputField(
			label = "Email",
			placeholder = "Enter your email",
			value = email,
			onValueChange = { email = it }
		)

		Spacer(modifier = Modifier.height(8.dp))

		InputField(
			label = "Username",
			placeholder = "Enter your username",
			value = username,
			onValueChange = { username = it }
		)

		Spacer(modifier = Modifier.height(8.dp))

		InputField(
			label = "Password",
			placeholder = "Enter your password",
			value = password,
			type = InputType.Password,
			onValueChange = { password = it }
		)

		Spacer(modifier = Modifier.height(8.dp))

		InputField(
			label = "Confirm Password",
			placeholder = "Enter your password again",
			value = confirmPassword,
			type = InputType.Password,
			onValueChange = { confirmPassword = it }
		)

		Spacer(modifier = Modifier.height(8.dp))

		LinkToAnotherScreen(
			staticText = "Already have an account?",
			clickableText = "Login here",
			onClick = { navController.navigate("login") }
		)

		Spacer(modifier = Modifier.height(8.dp))

		MainButton(
			label = "Register",
			onClick = { /* Handle login button click */ }
		)
	}
}
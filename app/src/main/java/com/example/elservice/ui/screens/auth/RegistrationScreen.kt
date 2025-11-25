package com.example.elservice.ui.screens.auth

import android.util.Patterns.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.elservice.ElServiceApp
import com.example.elservice.R
import com.example.elservice.common.InputType
import com.example.elservice.data.local.City
import com.example.elservice.data.local.Province
import com.example.elservice.data.local.RegionProvider
import com.example.elservice.data.remote.dto.request.auth.RegisterRequestDto
import com.example.elservice.ui.components.buttons.MainButton
import com.example.elservice.ui.components.fields.InputField
import com.example.elservice.ui.components.fields.SearchableDropDownField
import com.example.elservice.ui.components.links.LinkToAnotherScreen
import com.example.elservice.ui.components.texts.ErrorText
import com.example.elservice.ui.components.texts.TitleText
import com.example.elservice.ui.screens.auth.viewmodel.RegistrationViewModel

@Composable
fun RegistrationScreen(navController: NavHostController, viewModel: RegistrationViewModel) {
	val loading by remember { viewModel::loading }
	val success by remember { viewModel::success }
	val serverError by remember { viewModel::error }

	var nama by rememberSaveable { mutableStateOf("") }
	var noTelp by rememberSaveable { mutableStateOf("") }
	var alamat by rememberSaveable { mutableStateOf("") }
	var email by rememberSaveable { mutableStateOf("") }
	var username by rememberSaveable { mutableStateOf("") }
	var password by rememberSaveable { mutableStateOf("") }
	var confirmPassword by rememberSaveable { mutableStateOf("") }

	val listProvinsi = RegionProvider.provincesList()
	var provinsi by remember { mutableStateOf<Province?>(null) }

	val listKota by remember(provinsi) {
		mutableStateOf(provinsi?.let {
			RegionProvider.citiesByProvince(it.id)
		} ?: emptyList())
	}
	var kota by remember { mutableStateOf<City?>(null) }

	var localError by remember { mutableStateOf<String?>(null) }

	LaunchedEffect(success) {
		success?.let {
			navController.navigate("login") {
				popUpTo("register") { inclusive = true }
			}
		}
	}

	LaunchedEffect(serverError) {
		serverError?.let { localError = it }
	}

	fun validate(): Boolean {
		when {
			nama.isBlank() -> localError = "Name cannot be empty"
			noTelp.isBlank() -> localError = "Phone number cannot be empty"
			!noTelp.all { it.isDigit() } -> localError = "Phone number must be numbers only"
			email.isBlank() || !EMAIL_ADDRESS.matcher(email).matches() -> localError = "Invalid email format"
			username.isBlank() -> localError = "Username cannot be empty"
			password.length < 6 -> localError = "Password must be at least 6 characters"
			password != confirmPassword -> localError = "Password doesn't match"
			provinsi == null -> localError = "Province must be selected"
			kota == null -> localError = "City must be selected"
			alamat.isBlank() -> localError = "Address cannot be empty"
			else -> return true
		}
		return false
	}

	Column(
		verticalArrangement = Arrangement.spacedBy(8.dp),
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

		Spacer(modifier = Modifier.height(8.dp))

		TitleText("Please, Coming In!")

		Spacer(modifier = Modifier.height(8.dp))

		InputField(
			label = "Full Name",
			value = nama,
			onValueChange = { nama = it; localError = null }
		)

		InputField(
			label = "Phone Number",
			placeholder = "Example: 628123456789",
			value = noTelp,
			type = InputType.Number,
			onValueChange = { noTelp = it; localError = null }
		)

		SearchableDropDownField(
			label = "Province",
			selectedItem = provinsi,
			items = listProvinsi,
			itemLabel = { it.name },
			onItemSelected = { provinsi = it; kota = null; localError = null },
			prefixIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) }
		)

		SearchableDropDownField(
			label = "City",
			selectedItem = kota,
			items = listKota,
			itemLabel = { it.name },
			onItemSelected = { kota = it; localError = null },
			prefixIcon = { Icon(Icons.Default.LocationCity, contentDescription = null) }
		)

		InputField(
			label = "Address",
			value = alamat,
			onValueChange = { alamat = it; localError = null }
		)

		InputField(
			label = "Email",
			value = email,
			placeholder = "Example: william.henrt@example.com",
			type = InputType.Email,
			onValueChange = { email = it; localError = null }
		)

		InputField(
			label = "Username",
			value = username,
			onValueChange = { username = it; localError = null }
		)

		InputField(
			label = "Password",
			value = password,
			type = InputType.Password,
			onValueChange = { password = it; localError = null }
		)

		InputField(
			label = "Confirm Password",
			value = confirmPassword,
			type = InputType.Password,
			onValueChange = { confirmPassword = it; localError = null }
		)

		LinkToAnotherScreen(
			staticText = "Already have an account?",
			clickableText = "Login here",
			onClick = { navController.navigate("login") }
		)

		localError?.let { ErrorText(it) }

		MainButton(
			label = if (loading) "Loading..." else "Register",
			onClick = {
				if (!validate()) return@MainButton
				localError = null
				viewModel.register(
					RegisterRequestDto(
						nama = nama,
						no_telp = noTelp,
						alamat = alamat,
						provinsi = provinsi?.name ?: "",
						kota = kota?.name ?: "",
						email = email,
						username = username,
						password = password,
						confirm_password = confirmPassword
					)
				)
			}
		)
	}
}

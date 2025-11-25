package com.example.elservice.ui.screens.auth

import android.annotation.SuppressLint
import android.util.Log
import android.util.Patterns.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elservice.R
import com.example.elservice.common.InputType
import com.example.elservice.core.di.AppContainer
import com.example.elservice.ui.components.buttons.MainButton
import com.example.elservice.ui.components.fields.InputField
import com.example.elservice.ui.components.links.LinkToAnotherScreen
import com.example.elservice.ui.components.texts.ErrorText
import com.example.elservice.ui.components.texts.TitleText
import com.example.elservice.ui.navigation.AppRoute

@Composable
fun LoginScreen(
	navController: NavHostController,
	appConteiner: AppContainer
) {
	val viewModel = remember { appConteiner.loginViewModelFactory() }

	val loading by viewModel.loading.collectAsState()
	val serverError by viewModel.error.collectAsState()
	val session by viewModel.session.collectAsState()

	var email by rememberSaveable { mutableStateOf("") }
	var password by rememberSaveable { mutableStateOf("") }

	var localError by remember { mutableStateOf<String?>(null) }

	LaunchedEffect(session) {
		session?.let {
			val role = it.user.role

			navController.navigate(
				if (role == "customer") AppRoute.CustomerHome.route
				else AppRoute.TechnicianHome.route
			) {
				popUpTo(AppRoute.Login.route) { inclusive = true }
			}
		}
	}

	fun validate(): Boolean {
		if (email.isBlank()) {
			localError = "Email cannot be empty"
			return false
		}
		if (!EMAIL_ADDRESS.matcher(email).matches()) {
			localError = "Invalid email format"
			return false
		}
		if (password.isBlank()) {
			localError = "Password cannot be empty"
			return false
		}
		return true
	}

	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
			.padding(16.dp)
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

		TitleText("Hello Again!")

		Spacer(modifier = Modifier.height(16.dp))

		InputField(
			label = "Email",
			placeholder = "Enter your email",
			value = email,
			type = InputType.Email,
			onValueChange = {
				email = it
				localError = null
			}
		)

		Spacer(modifier = Modifier.height(8.dp))

		InputField(
			label = "Password",
			placeholder = "Enter your password",
			value = password,
			type = InputType.Password,
			onValueChange = {
				password = it
				localError = null
			}
		)

		Spacer(modifier = Modifier.height(8.dp))

		LinkToAnotherScreen(
			staticText = "Don't have an account?",
			clickableText = "Register here",
			onClick = { navController.navigate("register") }
		)

		Spacer(modifier = Modifier.height(8.dp))

		localError?.let {
			ErrorText(it)
		}

		serverError?.let {
			ErrorText(it)
		}

		MainButton(
			label = if (loading) "Loading..." else "Login",
			onClick = {
				if (loading) return@MainButton

				if (!validate()) return@MainButton

				viewModel.login(email, password)
			}
		)
	}
}
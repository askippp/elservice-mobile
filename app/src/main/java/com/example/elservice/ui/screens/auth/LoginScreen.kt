package com.example.elservice.ui.screens.auth

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
import com.example.elservice.ui.components.buttons.MainButton
import com.example.elservice.ui.components.fields.InputField
import com.example.elservice.ui.components.links.LinkToAnotherScreen
import com.example.elservice.ui.components.texts.TitleText

@Composable
fun LoginScreen(navController: NavHostController) {
	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }

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
			type = InputType.Text,
			onValueChange = { email = it }
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

		LinkToAnotherScreen(
			staticText = "Don't have an account?",
			clickableText = "Register here",
			onClick = { navController.navigate("register") }
		)

		Spacer(modifier = Modifier.height(8.dp))

		MainButton(
			label = "Login",
			onClick = { navController.navigate("home")}
		)
	}
}
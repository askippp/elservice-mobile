package com.example.elservice.ui.components.inputs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun InputField(
	label: String,
	placeholder: String,
	value: String,
	valueChange: (String) -> Unit
) {
	Box(modifier = Modifier.fillMaxWidth()) {
		OutlinedTextField(
			value = value,
			onValueChange = { valueChange },
			label = { Text(label) },
			placeholder = { Text(placeholder) }
		)
	}
}
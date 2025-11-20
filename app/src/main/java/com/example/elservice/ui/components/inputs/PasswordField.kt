package com.example.elservice.ui.components.inputs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordField(
	label: String,
	placeholder: String,
	value: String,
	valueChange: (String) -> Unit
) {
	var isVisible by remember { mutableStateOf(false) }

	Box(modifier = Modifier.fillMaxWidth()) {
		OutlinedTextField(
			value = value,
			onValueChange = { valueChange },
			label = { Text(label) },
			placeholder = { Text(placeholder) },
			visualTransformation = if (isVisible) {
				VisualTransformation.None
			} else {
				PasswordVisualTransformation()
			},
			trailingIcon = {
				IconButton(onClick = { isVisible = !isVisible }) {
					Icon(
						imageVector = if (isVisible) {
							Icons.Default.VisibilityOff
						} else {
							Icons.Default.Visibility
						},
						contentDescription = null
					)
				}
			}
		)
	}
}
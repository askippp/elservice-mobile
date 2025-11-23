package com.example.elservice.ui.components.fields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.example.elservice.common.InputType
import com.example.elservice.ui.components.dialogs.DatePickerDialog
import com.example.elservice.ui.components.dialogs.TimePickerDialog
import com.example.elservice.utils.*

@Composable
fun InputField(
	label: String,
	value: String,
	type: InputType = InputType.Text,
	placeholder: String = "",
	prefixIcon: ImageVector? = null,
	errorMessage: String? = null,
	maxLength: Int? = type.maxLength,
	onValueChange: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	var isPasswordVisible by remember { mutableStateOf(false) }
	var showDatePicker by remember { mutableStateOf(false) }
	var showTimePicker by remember { mutableStateOf(false) }

	fun handleInput(text: String): String {
		val clean = text.filter { it.isDigit() }

		return when (type) {
			InputType.Date -> formatDate(clean)
			InputType.Time -> formatTime(clean)
			InputType.Phone -> formatPhone(clean)
			else -> text
		}
	}

	val clickableModifier =
		if (type == InputType.Date || type == InputType.Time)
			Modifier.clickable {
				if (type == InputType.Date) showDatePicker = true
				if (type == InputType.Time) showTimePicker = true
			}
		else Modifier

	OutlinedTextField(
		value = value,
		onValueChange = { new ->
			if (maxLength != null && new.length > maxLength) return@OutlinedTextField
			onValueChange(handleInput(new))
		},
		label = { Text(label) },
		placeholder = { Text(placeholder) },
		shape = RoundedCornerShape(16.dp),
		colors = OutlinedTextFieldDefaults.colors(
			focusedBorderColor = MaterialTheme.colorScheme.primary,
			unfocusedBorderColor = MaterialTheme.colorScheme.outline
		),
		leadingIcon = prefixIcon?.let {
			{ Icon(it, contentDescription = null) }
		},
		trailingIcon = {
			if (type == InputType.Password) {
				IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
					Icon(
						if (isPasswordVisible) Icons.Default.VisibilityOff
						else Icons.Default.Visibility,
						contentDescription = null
					)
				}
			}
		},
		singleLine = true,
		keyboardOptions = KeyboardOptions(keyboardType = type.keyboard),
		visualTransformation = when (type) {
			InputType.Password ->
				if (isPasswordVisible) VisualTransformation.None
				else PasswordVisualTransformation()
			else -> VisualTransformation.None
		},
		enabled = !(type == InputType.Date || type == InputType.Time),
		isError = errorMessage != null,
		modifier = modifier
			.fillMaxWidth()
			.then(clickableModifier)
	)

	if (errorMessage != null) {
		Text(
			text = errorMessage,
			color = MaterialTheme.colorScheme.error,
			style = MaterialTheme.typography.bodySmall
		)
	}

	if (showDatePicker) {
		DatePickerDialog(
			onDateSelected = { onValueChange(it) },
			onDismiss = { showDatePicker = false }
		)
	}

	if (showTimePicker) {
		TimePickerDialog(
			onTimeSelected = { onValueChange(it) },
			onDismiss = { showTimePicker = false }
		)
	}
}
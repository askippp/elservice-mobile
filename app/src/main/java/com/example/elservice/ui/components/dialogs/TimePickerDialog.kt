package com.example.elservice.ui.components.dialogs

import androidx.compose.material3.*
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
	onTimeSelected: (String) -> Unit,
	onDismiss: () -> Unit
) {
	val state = rememberTimePickerState()

	AlertDialog(
		onDismissRequest = onDismiss,
		confirmButton = {
			TextButton(onClick = {
				val h = state.hour.toString().padStart(2, '0')
				val m = state.minute.toString().padStart(2, '0')
				onTimeSelected("$h:$m")
				onDismiss()
			}) { Text("OK") }
		},
		text = {
			TimePicker(state = state)
		}
	)
}

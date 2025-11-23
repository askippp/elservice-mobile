package com.example.elservice.ui.components.dialogs

import androidx.compose.material3.*
import androidx.compose.runtime.*
import java.util.*

@Composable
fun DatePickerDialog(
	onDateSelected: (String) -> Unit,
	onDismiss: () -> Unit
) {
	val state = rememberDatePickerState()

	DatePickerDialog(
		onDismissRequest = { onDismiss() },
		confirmButton = {
			TextButton(onClick = {
				val millis = state.selectedDateMillis ?: return@TextButton
				val c = Calendar.getInstance()
				c.timeInMillis = millis

				val d = c.get(Calendar.DAY_OF_MONTH).toString().padStart(2, '0')
				val m = (c.get(Calendar.MONTH) + 1).toString().padStart(2, '0')
				val y = c.get(Calendar.YEAR)

				onDateSelected("$d/$m/$y")
				onDismiss()
			}) {
				Text("OK")
			}
		}
	) {
		DatePicker(state = state)
	}
}

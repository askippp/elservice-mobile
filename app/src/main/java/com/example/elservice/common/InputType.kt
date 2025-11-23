package com.example.elservice.common

import androidx.compose.ui.text.input.KeyboardType

enum class InputType(
	val keyboard: KeyboardType,
	val maxLength: Int? = null
) {
	Text(KeyboardType.Text),
	Number(KeyboardType.Number),
	Password(KeyboardType.Password),
	Email(KeyboardType.Email),
	Phone(KeyboardType.Phone, maxLength = 15),
	Date(KeyboardType.Number, maxLength = 10),      // DD/MM/YYYY
	Time(KeyboardType.Number, maxLength = 5),       // HH:MM
	DateTime(KeyboardType.Number) // DD/MM/YYYY HH:MM
}


package com.example.elservice.ui.components.texts

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import java.time.format.TextStyle

@Composable
fun HeaderText(text: String) {
	Text(
		text = text,
		color = MaterialTheme.colorScheme.onSurface,
		style = MaterialTheme.typography.titleLarge
	)
}
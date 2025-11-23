package com.example.elservice.ui.components.texts

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LabelText(text: String) {
	Text(
		text = text,
		color = MaterialTheme.colorScheme.onSurfaceVariant,
		style = MaterialTheme.typography.labelSmall
	)
}

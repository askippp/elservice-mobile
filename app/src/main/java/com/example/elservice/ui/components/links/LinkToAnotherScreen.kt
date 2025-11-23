package com.example.elservice.ui.components.links

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun LinkToAnotherScreen(
	staticText: String,
	clickableText: String,
	onClick: () -> Unit
) {
	Row(
		horizontalArrangement = Arrangement.End,
		modifier = Modifier
			.fillMaxWidth()
			.padding(end = 16.dp)
	) {
		Text(
			text = staticText,
			style = MaterialTheme.typography.bodyMedium,
			color = MaterialTheme.colorScheme.onSurface
		)

		Spacer(modifier = Modifier.width(4.dp))

		Text(
			text = clickableText,
			style = MaterialTheme.typography.bodyMedium,
			color = MaterialTheme.colorScheme.primary,
			textDecoration = TextDecoration.Underline,
			modifier = Modifier.clickable { onClick() }
		)
	}
}
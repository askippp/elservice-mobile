package com.example.elservice.ui.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainButton(label: String, onClick: () -> Unit) {
	Box(modifier = Modifier.fillMaxWidth()) {
		Button(
			onClick = onClick,
			shape = RoundedCornerShape(16.dp),
			colors = ButtonDefaults.buttonColors(
				containerColor = MaterialTheme.colorScheme.primary,
				contentColor = MaterialTheme.colorScheme.surface
			),
			modifier = Modifier.fillMaxWidth()
		) {
			Text(label)
		}
	}
}
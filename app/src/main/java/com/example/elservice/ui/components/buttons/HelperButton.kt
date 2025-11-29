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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elservice.R

@Composable
fun HelperButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
	Button(
		onClick = onClick ,
		shape = RoundedCornerShape(12.dp),
		colors = ButtonDefaults.buttonColors(
			containerColor = MaterialTheme.colorScheme.background,
			contentColor = MaterialTheme.colorScheme.primary
		),
		border = ButtonDefaults.outlinedButtonBorder(),
		modifier = modifier
	) {
		Text(
			text = label,
			fontSize = 12.sp,
			fontFamily = FontFamily(Font(R.font.poppins_medium))
		)
	}
}
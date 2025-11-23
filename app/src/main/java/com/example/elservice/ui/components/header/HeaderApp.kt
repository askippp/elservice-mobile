package com.example.elservice.ui.components.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elservice.R
import com.example.elservice.ui.components.texts.BodyText

@Composable
fun HeaderApp(
	username: String? = null,
	photo: Int? = null,
	navController: NavHostController
) {
	Column(modifier = Modifier.fillMaxWidth()) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth()
		) {
			Image(
				painter = painterResource(R.drawable.logo),
				contentDescription = null
			)

			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier.clickable(
					enabled = true,
					onClick = { navController.navigate("profile") }
				)
			) {
				BodyText(text = username ?: "Unknown user")

				Image(
					painter = painterResource(photo ?: R.drawable.ic_launcher_foreground),
					contentDescription = null,
					contentScale = ContentScale.Crop,
					modifier = Modifier
						.padding(start = 8.dp)
						.size(40.dp)
						.clip(CircleShape)
						.background(MaterialTheme.colorScheme.primary)
				)
			}
		}

		Spacer(modifier = Modifier.height(8.dp))

		HorizontalDivider(
			thickness = 2.dp,
			color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
		)

		Spacer(modifier = Modifier.height(16.dp))
	}
}

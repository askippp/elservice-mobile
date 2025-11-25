package com.example.elservice.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.elservice.R
import com.example.elservice.ui.components.texts.CaptionText

@Composable
fun AlatCard(
	image: String?,
	namaAlat: String,
	isSelected: Boolean,
	onClick: () -> Unit
) {
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.width(80.dp)
			.clickable(
				onClick = { onClick() }
			)
			.background(
				color = MaterialTheme.colorScheme.surfaceContainer.copy(1f),
				shape = MaterialTheme.shapes.medium
			)
			.then(
				if (isSelected) {
					Modifier
						.padding(2.dp)
						.background(
							color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
							shape = MaterialTheme.shapes.medium
						)
				} else Modifier
			)
			.padding(vertical = 8.dp)
	) {
		val painter = if (image.isNullOrEmpty()) {
			painterResource(R.drawable.ic_launcher_foreground)
		} else {
			rememberAsyncImagePainter(image)
		}

		Box {
			Image(
				painter = painter,
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.size(65.dp)
					.background(
						color = MaterialTheme.colorScheme.onSurface,
						shape = MaterialTheme.shapes.medium
					)
			)
		}

		Spacer(modifier = Modifier.height(4.dp))

		CaptionText(namaAlat)
	}
}
package com.example.elservice.ui.components.carousel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AutoImageCarousel(
	images: List<Int>,
	modifier: Modifier = Modifier,
	autoScrollDuration: Long = 3000
) {
	if (images.isEmpty()) return

	val pagerState = rememberPagerState(pageCount = { images.size })
	val scope = rememberCoroutineScope()

	LaunchedEffect(Unit) {
		while (true) {
			delay(autoScrollDuration)
			val next = (pagerState.currentPage + 1) % images.size
			scope.launch { pagerState.animateScrollToPage(next) }
		}
	}

	Box(modifier = modifier.padding(bottom = 16.dp)) {
		HorizontalPager(
			state = pagerState,
			modifier = Modifier
				.fillMaxWidth()
				.height(180.dp)
		) { page ->
			Card(
				modifier = Modifier
					.fillMaxWidth()
					.clip(RoundedCornerShape(16.dp)),
				colors = CardDefaults.cardColors(Color.Transparent)
			) {
				AsyncImage(
					model = images[page],
					contentDescription = null,
					contentScale = ContentScale.Crop,
					placeholder = painterResource(images[page]),
					modifier = Modifier.fillMaxSize()
				)
			}
		}

		Row(
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.align(Alignment.BottomCenter)
				.padding(bottom = 12.dp)
		) {
			repeat(images.size) { index ->
				val isActive = pagerState.currentPage == index

				Box(
					modifier = Modifier
						.padding(horizontal = 4.dp)
						.size(if (isActive) 10.dp else 8.dp)
						.clip(CircleShape)
						.background(
							if (isActive)
								Color.White.copy(alpha = 0.95f)
							else
								Color.White.copy(alpha = 0.45f)
						)
				)
			}
		}
	}
}
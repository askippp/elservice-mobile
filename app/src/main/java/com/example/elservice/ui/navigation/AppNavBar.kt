package com.example.elservice.ui.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppNavBar(
	role: String,
	currentRoute: String?,
	onNavigate: (String) -> Unit
) {
	val items = if (role == "teknisi") teknisiNavItems else customerNavItems

	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 20.dp, vertical = 12.dp),
		color = Color.Transparent
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.height(64.dp)
				.clip(RoundedCornerShape(32.dp))
				.background(MaterialTheme.colorScheme.surface),
			horizontalArrangement = Arrangement.SpaceEvenly,
			verticalAlignment = Alignment.CenterVertically
		) {
			items.forEach { item ->
				val selected = currentRoute == item.route
				NavBarItem(
					item = item,
					selected = selected,
					onClick = { onNavigate(item.route) }
				)
			}
		}
	}
}

@Composable
private fun RowScope.NavBarItem(
	item: NavItem,
	selected: Boolean,
	onClick: () -> Unit
) {
	val scale by animateFloatAsState(
		targetValue = if (selected) 1f else 0.9f,
		animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
	)

	val iconColor by animateColorAsState(
		targetValue = if (selected)
			MaterialTheme.colorScheme.primary
		else
			MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
		animationSpec = tween(durationMillis = 200)
	)

	val backgroundColor by animateColorAsState(
		targetValue = if (selected)
			MaterialTheme.colorScheme.primaryContainer
		else
			Color.Transparent,
		animationSpec = tween(durationMillis = 200)
	)

	val width by animateDpAsState(
		targetValue = if (selected) 100.dp else 48.dp,
		animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
	)

	Box(
		modifier = Modifier
			.weight(1f)
			.fillMaxHeight(),
		contentAlignment = Alignment.Center
	) {
		Surface(
			onClick = onClick,
			modifier = Modifier
				.width(width)
				.height(48.dp)
				.scale(scale),
			shape = RoundedCornerShape(24.dp),
			color = backgroundColor
		) {
			Row(
				modifier = Modifier
					.fillMaxSize()
					.padding(horizontal = 12.dp),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				Icon(
					imageVector = item.icon,
					contentDescription = item.label,
					tint = iconColor,
					modifier = Modifier.size(22.dp)
				)

				if (selected) {
					Spacer(modifier = Modifier.width(8.dp))
					Text(
						text = item.label,
						style = MaterialTheme.typography.labelMedium,
						color = iconColor,
						maxLines = 1
					)
				}
			}
		}
	}
}
package com.example.elservice.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
	primary = PurplePrimary,
	onPrimary = Color.White,
	primaryContainer = PurplePrimaryContainer,
	onPrimaryContainer = Color(0xFF3B1E47),

	secondary = PurpleSecondary,
	onSecondary = Color(0xFF3B1E47),

	tertiary = PurpleTertiary,
	onTertiary = Color.White,

	background = BackgroundLight,
	onBackground = Color(0xFF1A111D),

	surface = SurfaceLight,
	onSurface = Color(0xFF1A111D)
)

private val DarkColorScheme = darkColorScheme(
	primary = PurplePrimaryContainer,
	onPrimary = Color(0xFF3B1E47),
	primaryContainer = Color(0xFF513062),
	onPrimaryContainer = Color(0xFFE8DDF2),

	secondary = PurpleSecondaryDark,
	onSecondary = Color(0xFF2A1832),

	tertiary = PurpleTertiaryDark,
	onTertiary = Color(0xFF341B41),

	background = BackgroundDark,
	onBackground = Color(0xFFE9DDEF),

	surface = SurfaceDark,
	onSurface = Color(0xFFE9DDEF)
)

@Composable
fun ElserviceTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	dynamicColor: Boolean = false, // matikan agar follow Figma
	content: @Composable () -> Unit
) {
	val colorScheme = when {
		dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
			val context = LocalContext.current
			if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
		}
		darkTheme -> DarkColorScheme
		else -> LightColorScheme
	}

	MaterialTheme(
		colorScheme = colorScheme,
		typography = AppTypography,
		content = content
	)
}

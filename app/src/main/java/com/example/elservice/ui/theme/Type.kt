package com.example.elservice.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.elservice.R

val Poppins = FontFamily(
	Font(R.font.poppins_medium, FontWeight.Medium),
)

val AppTypography = Typography(

	// --- Body Text Utama ---
	bodyLarge = TextStyle(
		fontFamily = Poppins,
		fontWeight = FontWeight.ExtraBold,
		fontSize = 16.sp,
		lineHeight = 24.sp
	),

	// --- Judul Besar (Header) ---
	titleLarge = TextStyle(
		fontFamily = FontFamily(Font(R.font.poppins_bold)),
		fontWeight = FontWeight.ExtraBold,
		fontSize = 22.sp,
		lineHeight = 28.sp
	),

	// --- Judul Medium (SubtitleText) ---
	titleMedium = TextStyle(
		fontFamily = Poppins,
		fontWeight = FontWeight.ExtraBold,
		fontSize = 20.sp,
		lineHeight = 24.sp
	),

	// --- Body Medium (opsional untuk teks sekunder) ---
	bodyMedium = TextStyle(
		fontFamily = Poppins,
		fontWeight = FontWeight.ExtraBold,
		fontSize = 14.sp,
		lineHeight = 20.sp
	),

	// --- Caption / Info kecil ---
	labelMedium = TextStyle(
		fontFamily = Poppins,
		fontWeight = FontWeight.ExtraBold,
		fontSize = 12.sp,
		lineHeight = 16.sp
	),

	// --- Label input field dan error kecil ---
	labelSmall = TextStyle(
		fontFamily = Poppins,
		fontWeight = FontWeight.ExtraBold,
		fontSize = 14.sp,
		lineHeight = 16.sp
	)
)

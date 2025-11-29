package com.example.elservice.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@RequiresApi(Build.VERSION_CODES.O)
fun formatTanggal(input: String?): String {
	return try {
		input?.let {
			// Asumsi input: "2025-11-29T16:00:00"
			val parsedDate = LocalDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME)
			parsedDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
		} ?: "-"
	} catch (e: DateTimeParseException) {
		"-"
	}
}


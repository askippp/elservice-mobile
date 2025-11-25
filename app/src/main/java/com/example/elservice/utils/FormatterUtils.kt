package com.example.elservice.utils

fun formatDate(s: String): String {
	val clean = s.filter { it.isDigit() }.take(8)
	return clean.replace(
		Regex("(\\d{2})(\\d{2})(\\d{0,4})?")
	) {
		val (d, m, y) = it.destructured
		listOf(d, m, y).filter { part -> part.isNotEmpty() }.joinToString("/")
	}
}

fun formatTime(s: String): String {
	val clean = s.filter { it.isDigit() }.take(4)
	return clean.replace(
		Regex("(\\d{2})(\\d{0,2})?")
	) {
		val (h, m) = it.destructured
		if (m.isNotEmpty()) "$h:$m" else h
	}
}

fun formatPhone(s: String): String {
	val clean = s.filter { it.isDigit() }.take(20)
	return clean
}

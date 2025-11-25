package com.example.elservice.domain.model.user

data class RegisteredUser(
	val userId: Int,
	val username: String,
	val email: String,
	val role: String,
	val customerName: String,
	val phone: String,
	val city: String,
	val province: String
)


package com.example.elservice.data.remote.dto.response.auth

data class ErrorResponseDto(
	val message: String,
	val errors: Map<String, List<String>>? = null
)

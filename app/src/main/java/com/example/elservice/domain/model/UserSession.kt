package com.example.elservice.domain.model

data class UserSession(
	val token: String,
	val user: User,
	val detail: UserDetail
)

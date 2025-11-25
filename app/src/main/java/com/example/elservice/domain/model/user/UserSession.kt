package com.example.elservice.domain.model.user

data class UserSession(
	val token: String,
	val user: User,
	val detail: UserDetail
)

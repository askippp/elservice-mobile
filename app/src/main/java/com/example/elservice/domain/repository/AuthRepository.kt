package com.example.elservice.domain.repository

import com.example.elservice.data.remote.dto.request.auth.RegisterRequestDto
import com.example.elservice.domain.model.user.RegisteredUser
import com.example.elservice.domain.model.user.UserSession

interface AuthRepository {
	suspend fun register(body: RegisterRequestDto): RegisteredUser
	suspend fun login(email: String, password: String): UserSession
	suspend fun getSession(): UserSession?
	suspend fun clearSession()
}
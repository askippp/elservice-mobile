package com.example.elservice.domain.repository

import com.example.elservice.domain.model.UserSession

interface AuthRepository {

	suspend fun login(email: String, password: String): UserSession

	suspend fun saveToken(token: String)

	suspend fun getToken(): String?

	suspend fun clearToken()
}
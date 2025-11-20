package com.example.elservice.data.repository

import com.example.elservice.core.token.TokenManager
import com.example.elservice.data.datastore.UserPreferences
import com.example.elservice.data.mapper.LoginMapper
import com.example.elservice.data.remote.api.ApiService
import com.example.elservice.data.remote.dto.request.LoginRequestDto
import com.example.elservice.domain.model.UserSession
import com.example.elservice.domain.repository.AuthRepository

class AuthRepositoryImpl(
	private val api: ApiService,
	private val userPreferences: UserPreferences
) : AuthRepository {

	override suspend fun login(email: String, password: String): UserSession {
		val response = api.login(LoginRequestDto(email, password))
		val session = LoginMapper.toUserSession(response)

		userPreferences.saveToken(session.token)
		TokenManager.updateToken(session.token)

		return session
	}

	override suspend fun saveToken(token: String) {
		userPreferences.saveToken(token)
		TokenManager.updateToken(token)
	}

	override suspend fun getToken(): String? {
		return userPreferences.getToken()
	}

	override suspend fun clearToken() {
		userPreferences.clearToken()
		TokenManager.clearToken()
	}
}

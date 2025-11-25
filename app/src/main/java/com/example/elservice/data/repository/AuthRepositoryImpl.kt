package com.example.elservice.data.repository

import com.example.elservice.core.token.TokenManager
import com.example.elservice.data.datastore.UserPreferences
import com.example.elservice.data.mapper.auth.LoginMapper
import com.example.elservice.data.mapper.auth.RegisterMapper
import com.example.elservice.data.remote.api.ApiService
import com.example.elservice.data.remote.dto.request.auth.LoginRequestDto
import com.example.elservice.data.remote.dto.request.auth.RegisterRequestDto
import com.example.elservice.data.remote.dto.response.auth.ErrorResponseDto
import com.example.elservice.domain.model.user.RegisteredUser
import com.example.elservice.domain.model.user.UserSession
import com.example.elservice.domain.repository.AuthRepository

class AuthRepositoryImpl(
	private val api: ApiService,
	private val prefs: UserPreferences
) : AuthRepository {

	override suspend fun register(body: RegisterRequestDto): RegisteredUser {
		return try {
			val response = api.register(body)
			RegisterMapper.toRegisteredUser(response)
		} catch (e: retrofit2.HttpException) {
			// parse JSON error dari server
			val errorJson = e.response()?.errorBody()?.string()
			val gson = com.google.gson.Gson()
			val errorResponse = gson.fromJson(errorJson, ErrorResponseDto::class.java)
			// gabung semua error field jadi string
			val errorMsg = errorResponse.errors?.values?.flatten()?.joinToString("\n")
				?: errorResponse.message
			throw Exception(errorMsg)
		}
	}

	override suspend fun login(email: String, password: String): UserSession {
		val response = api.login(LoginRequestDto(email, password))
		val session = LoginMapper.toUserSession(response)

		prefs.saveSession(session)
		TokenManager.updateToken(session.token)

		return session
	}

	override suspend fun getSession(): UserSession? {
		return prefs.getSession()
	}

	override suspend fun clearSession() {
		prefs.clearSession()
		TokenManager.clearToken()
	}
}

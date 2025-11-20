package com.example.elservice.core.token

object TokenManager {
	private var token: String? = null

	fun updateToken(newToken: String) {
		token = newToken
	}

	fun getToken(): String? = token

	fun clearToken() {
		token = null
	}
}
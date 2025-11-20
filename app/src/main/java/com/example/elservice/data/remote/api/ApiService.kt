package com.example.elservice.data.remote.api

import com.example.elservice.data.remote.dto.request.LoginRequestDto
import com.example.elservice.data.remote.dto.response.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

	@POST("login")
	suspend fun login(
		@Body body: LoginRequestDto
	): LoginResponseDto
}
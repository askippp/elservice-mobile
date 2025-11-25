package com.example.elservice.data.remote.api

import com.example.elservice.data.remote.dto.request.auth.LoginRequestDto
import com.example.elservice.data.remote.dto.request.auth.RegisterRequestDto
import com.example.elservice.data.remote.dto.response.auth.LoginResponseDto
import com.example.elservice.data.remote.dto.response.alat.AlatListResponseDto
import com.example.elservice.data.remote.dto.response.alat.AlatResponseDto
import com.example.elservice.data.remote.dto.response.auth.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

	@POST("login")
	suspend fun login(@Body body: LoginRequestDto): LoginResponseDto

	@POST("register")
	suspend fun register(@Body body: RegisterRequestDto): RegisterResponseDto

	@GET("customer/alat")
	suspend fun getListAlat(): AlatListResponseDto

}
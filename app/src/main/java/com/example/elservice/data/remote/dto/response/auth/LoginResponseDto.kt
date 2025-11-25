package com.example.elservice.data.remote.dto.response.auth

data class LoginResponseDto(
	val token: String,
	val user: UserLoginDto,
	val detail: UserDetailDto
)

data class UserLoginDto(
	val id: Int,
	val username: String,
	val email: String,
	val role: String
)

data class UserDetailDto(
	val id: Int,
	val nama: String,
	val no_telp: String,
	val alamat: String,
	val foto: String,
	val provinsi: String,
	val kota: String
)

package com.example.elservice.data.remote.dto.response

data class LoginResponseDto(
	val token: String,
	val user: UserDto,
	val detail: UserDetailDto
)

data class UserDto(
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
	val provinsi: String,
	val kota: String
)

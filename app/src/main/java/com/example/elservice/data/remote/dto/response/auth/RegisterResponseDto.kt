package com.example.elservice.data.remote.dto.response.auth

data class RegisterResponseDto(
	val message: String,
	val user: UserRegisterDto,
	val customer: CustomerDto
)

data class UserRegisterDto(
	val id: Int,
	val email: String,
	val username: String,
	val role: String,
	val created_at: String?,
	val updated_at: String?
)

data class CustomerDto(
	val id: Int,
	val id_user: Int,
	val nama: String,
	val no_telp: String,
	val alamat: String,
	val provinsi: String,
	val kota: String,
	val email: String,
	val created_at: String?,
	val updated_at: String?
)
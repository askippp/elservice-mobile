package com.example.elservice.data.remote.dto.request.auth

import com.google.gson.annotations.SerializedName

data class RegisterRequestDto(
	val nama: String,
	val no_telp: String,
	val provinsi: String,
	val kota: String,
	val alamat: String,
	val email: String,
	val username: String,
	val password: String,
	val confirm_password: String
)

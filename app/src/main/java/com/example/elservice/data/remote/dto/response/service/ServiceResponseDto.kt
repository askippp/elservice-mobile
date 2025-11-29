package com.example.elservice.data.remote.dto.response.service

import java.sql.Date

data class ServiceResponseDto(
	val message: String,
	val data: List<ServiceDto>
)

data class DetailServiceDto(
	val id: Int,
	val keluhan: String,
	val alamat_service: String,
	val status: String,
	val tanggal_masuk: Date?,
	val total_biaya: Float?,
	val alats: List<AlatDto>
)

data class AlatDto(
	val id: Int,
	val nama_alat: String
)

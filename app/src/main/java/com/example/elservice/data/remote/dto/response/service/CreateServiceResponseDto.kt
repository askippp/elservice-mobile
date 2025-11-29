package com.example.elservice.data.remote.dto.response.service

data class CreateServiceResponseDto(
	val message: String,
	val data: ServiceDto?
)

data class ServiceDto(
	val id: Int,
	val id_customer: Int,
	val id_cabang: Int?,
	val id_operator: Int?,
	val alamat_service: String?,
	val keluhan: String?,
	val status: String?,
	val tanggal_masuk: String?,
	val alats: List<AlatItemDto> = emptyList()
)

data class AlatItemDto(
	val id: Int,
	val id_kategori: Int?,
	val id_merek: Int?,
	val nama_alat: String,
	val tipe_model: String?,
	val deskripsi: String?,
	val foto: String?,
	val status: String?
)

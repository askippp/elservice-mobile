package com.example.elservice.data.remote.dto.response.service

data class CancelServiceResponseDto(
	val message: String,
	val data: ServiceData?
)

data class ServiceData(
	val id: Int,
	val id_customer: Int,
	val id_cabang: Int,
	val id_operator: Int,
	val alamat_service: String?,
	val keluhan: String?,
	val diagnosa: String?,
	val biaya_service: Float?,
	val biaya_kunjungan: Float?,
	val total_biaya: Float?,
	val status: String?,
	val tanggal_masuk: String?,
	val tanggal_selesai: String?,
	val catatan: String?,
	val created_at: String?,
	val updated_at: String?
)
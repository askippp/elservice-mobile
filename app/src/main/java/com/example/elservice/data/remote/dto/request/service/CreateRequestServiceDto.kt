package com.example.elservice.data.remote.dto.request.service

data class CreateRequestServiceDto(
	val id_customer: Int,
	val keluhan: String,
	val alamat_service: String,
	val alat_ids: List<Int>
)

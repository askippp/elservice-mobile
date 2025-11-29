	package com.example.elservice.domain.model.service

	data class Service(
		val id: Int,
		val alamatService: String?,
		val keluhan: String?,
		val status: String?,
		val tanggalMasuk: String?,
		val totalBiaya: Float?,
		val alatList: List<String>
	)

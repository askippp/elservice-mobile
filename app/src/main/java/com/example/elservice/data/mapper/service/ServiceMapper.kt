package com.example.elservice.data.mapper.service

import com.example.elservice.data.remote.dto.response.service.ServiceDto
import com.example.elservice.domain.model.service.Service

object ServiceMapper {

	fun ServiceDto.toDomain(): Service {
		return Service(
			id = id,
			alamatService = alamat_service,
			keluhan = keluhan,
			status = status,
			tanggalMasuk = tanggal_masuk,
			totalBiaya = null,
			alatList = alats.map { it.nama_alat }
		)
	}
}
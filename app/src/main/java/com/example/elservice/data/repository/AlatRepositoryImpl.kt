package com.example.elservice.data.repository

import com.example.elservice.data.mapper.alat.AlatMapper.toDomain
import com.example.elservice.data.remote.api.ApiService
import com.example.elservice.domain.model.alat.Alat
import com.example.elservice.domain.repository.AlatRepository

class AlatRepositoryImpl(
	private val api: ApiService,
) : AlatRepository {

	override suspend fun getAlats(): List<Alat> {
		return api.getListAlat().data.map { it.toDomain() }
	}
}

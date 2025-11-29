package com.example.elservice.data.repository

import com.example.elservice.data.mapper.service.ServiceMapper.toDomain
import com.example.elservice.data.remote.api.ApiService
import com.example.elservice.data.remote.dto.request.service.CreateRequestServiceDto
import com.example.elservice.data.remote.dto.response.service.CancelServiceResponseDto
import com.example.elservice.data.remote.dto.response.service.DetailServiceResponse
import com.example.elservice.domain.model.service.Service
import com.example.elservice.domain.repository.ServiceRepository

class ServiceRepositoryImpl(
	private val api: ApiService
) : ServiceRepository {

	override suspend fun createService(token: String, body: CreateRequestServiceDto): Service {
		val response = api.createCustomerService("Bearer $token", body)
		val dto = response.data ?: throw Exception(response.message)
		return dto.toDomain()
	}

	override suspend fun getListService(token: String): List<Service> {
		val response = api.getCustomerServices("Bearer $token")
		return response.data.map { it.toDomain() }
	}

	override suspend fun getDetailService(token: String, id: Int): DetailServiceResponse {
		val response = api.getDetailCustomerService("Bearer $token", id)
		return response.data
	}

	override suspend fun cancelService(token: String, id: Int): CancelServiceResponseDto {
		return api.cancelService("Bearer $token", id)
	}
}

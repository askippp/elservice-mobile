package com.example.elservice.domain.repository

import com.example.elservice.data.remote.dto.request.service.CreateRequestServiceDto
import com.example.elservice.data.remote.dto.response.service.CancelServiceResponseDto
import com.example.elservice.data.remote.dto.response.service.DetailServiceResponse
import com.example.elservice.data.remote.dto.response.service.ServiceDto
import com.example.elservice.domain.model.service.Service

interface ServiceRepository {
	suspend fun createService(token: String, body: CreateRequestServiceDto): Service
	suspend fun getListService(token: String): List<Service>
	suspend fun getDetailService(token: String, id: Int): DetailServiceResponse
	suspend fun cancelService(token: String, id: Int): CancelServiceResponseDto
}

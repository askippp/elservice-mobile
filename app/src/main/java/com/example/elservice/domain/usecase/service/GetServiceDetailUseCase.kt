package com.example.elservice.domain.usecase.service

import com.example.elservice.data.remote.dto.response.service.DetailServiceResponse
import com.example.elservice.domain.repository.ServiceRepository

class GetServiceDetailUseCase(private val repo: ServiceRepository) {
	suspend operator fun invoke(token: String, id: Int) = repo.getDetailService(token, id)
}
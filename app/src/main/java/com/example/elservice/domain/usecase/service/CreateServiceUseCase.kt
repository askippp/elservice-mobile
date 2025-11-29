package com.example.elservice.domain.usecase.service

import com.example.elservice.data.remote.dto.request.service.CreateRequestServiceDto
import com.example.elservice.domain.model.service.Service
import com.example.elservice.domain.repository.ServiceRepository

class CreateServiceUseCase(
	private val repository: ServiceRepository
) {
	suspend operator fun invoke(token: String, body: CreateRequestServiceDto): Service {
		return repository.createService(token, body)
	}
}

package com.example.elservice.domain.usecase.service

import com.example.elservice.data.remote.dto.response.service.CancelServiceResponseDto
import com.example.elservice.domain.repository.ServiceRepository

class CancelServiceUseCase(
	private val repository: ServiceRepository
) {
	suspend operator fun invoke(token: String, serviceId: Int): CancelServiceResponseDto {
		return repository.cancelService(token, serviceId)
	}
}

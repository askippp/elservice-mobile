package com.example.elservice.domain.usecase.service

import com.example.elservice.domain.repository.ServiceRepository

class GetListServiceUseCase(
	private val repo: ServiceRepository
) {
	suspend operator fun invoke(token: String) = repo.getListService(token)
}

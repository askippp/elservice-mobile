package com.example.elservice.domain.usecase

import com.example.elservice.domain.repository.AuthRepository

class ClearTokenUseCase(private val repo: AuthRepository) {
	suspend operator fun invoke() = repo.clearToken()
}

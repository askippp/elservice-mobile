package com.example.elservice.domain.usecase.auth

import com.example.elservice.domain.repository.AuthRepository

class ClearSessionUseCase(private val repo: AuthRepository) {
	suspend operator fun invoke() = repo.clearSession()
}

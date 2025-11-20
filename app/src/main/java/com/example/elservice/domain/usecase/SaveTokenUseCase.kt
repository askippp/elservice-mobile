package com.example.elservice.domain.usecase

import com.example.elservice.domain.repository.AuthRepository

class SaveTokenUseCase(private val repo: AuthRepository) {
	suspend operator fun invoke(token: String) = repo.saveToken(token)
}
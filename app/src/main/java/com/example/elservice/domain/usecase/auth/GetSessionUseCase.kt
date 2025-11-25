package com.example.elservice.domain.usecase.auth

import com.example.elservice.domain.model.user.UserSession
import com.example.elservice.domain.repository.AuthRepository

class GetSessionUseCase(private val repo: AuthRepository) {
	suspend operator fun invoke() = repo.getSession()
}

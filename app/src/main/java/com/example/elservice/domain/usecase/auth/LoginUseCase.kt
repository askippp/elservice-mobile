package com.example.elservice.domain.usecase.auth

import com.example.elservice.domain.model.user.UserSession
import com.example.elservice.domain.repository.AuthRepository

class LoginUseCase(private val repo: AuthRepository) {
	suspend operator fun invoke(email: String, password: String): UserSession {
		return repo.login(email, password)
	}
}
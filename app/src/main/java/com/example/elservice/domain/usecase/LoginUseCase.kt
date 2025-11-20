package com.example.elservice.domain.usecase

import com.example.elservice.domain.repository.AuthRepository

class LoginUseCase(private val repo: AuthRepository) {
	suspend operator fun invoke(email: String, password: String) =
		repo.login(email, password)
}

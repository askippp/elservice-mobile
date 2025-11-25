package com.example.elservice.domain.usecase.auth

import com.example.elservice.data.remote.dto.request.auth.RegisterRequestDto
import com.example.elservice.domain.model.user.RegisteredUser
import com.example.elservice.domain.repository.AuthRepository

class RegisterUseCase(private val repo: AuthRepository) {
	suspend operator fun invoke(body: RegisterRequestDto): RegisteredUser {
		return repo.register(body)
	}
}

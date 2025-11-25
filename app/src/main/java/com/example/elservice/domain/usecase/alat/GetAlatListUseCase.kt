package com.example.elservice.domain.usecase.alat

import com.example.elservice.domain.model.alat.Alat
import com.example.elservice.domain.repository.AlatRepository

class GetAlatListUseCase(
	private val repository: AlatRepository
) {
	suspend operator fun invoke(): List<Alat> {
		return repository.getAlats()
	}
}

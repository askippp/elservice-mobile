package com.example.elservice.domain.repository

import com.example.elservice.domain.model.alat.Alat

interface AlatRepository {
	suspend fun getAlats(): List<Alat>
}
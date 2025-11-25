package com.example.elservice.data.mapper.alat

import com.example.elservice.data.remote.dto.response.alat.AlatResponseDto
import com.example.elservice.data.remote.dto.response.alat.KategoriDto
import com.example.elservice.data.remote.dto.response.alat.MerekDto
import com.example.elservice.domain.model.alat.Alat
import com.example.elservice.domain.model.alat.Kategori
import com.example.elservice.domain.model.alat.Merek

object AlatMapper {

	fun AlatResponseDto.toDomain(): Alat {
		return Alat(
			id = id,
			namaAlat = namaAlat,
			tipeModel = tipeModel,
			deskripsi = deskripsi,
			foto = foto,
			status = status,
			kategori = kategori.toDomain(),
			merek = merek.toDomain()
		)
	}

	fun KategoriDto.toDomain(): Kategori {
		return Kategori(
			id = id,
			nama = namaKategori
		)
	}

	fun MerekDto.toDomain(): Merek {
		return Merek(
			id = id,
			nama = namaMerek
		)
	}
}
package com.example.elservice.domain.model.alat

data class Alat(
	val id: Int,
	val namaAlat: String,
	val tipeModel: String,
	val deskripsi: String,
	val foto: String?,
	val status: String,
	val kategori: Kategori,
	val merek: Merek
)

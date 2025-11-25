package com.example.elservice.data.remote.dto.response.alat

import com.google.gson.annotations.SerializedName

data class AlatListResponseDto(
	val message: String,
	val data: List<AlatResponseDto>
)

data class AlatResponseDto(
	val id: Int,
	@SerializedName("id_kategori") val idKategori: Int,
	@SerializedName("id_merek") val idMerek: Int,
	@SerializedName("nama_alat") val namaAlat: String,
	@SerializedName("tipe_model") val tipeModel: String,
	val deskripsi: String,
	val foto: String?,
	val status: String,
	@SerializedName("created_at") val createdAt: String,
	@SerializedName("updated_at") val updatedAt: String,
	val kategori: KategoriDto,
	val merek: MerekDto
)

data class KategoriDto(
	val id: Int,
	@SerializedName("nama_kategori") val namaKategori: String,
	val deskripsi: String?,
	@SerializedName("created_at") val createdAt: String,
	@SerializedName("updated_at") val updatedAt: String
)

data class MerekDto(
	val id: Int,
	@SerializedName("nama_merek") val namaMerek: String,
	@SerializedName("negara_asal") val negaraAsal: String?,
	@SerializedName("created_at") val createdAt: String,
	@SerializedName("updated_at") val updatedAt: String
)


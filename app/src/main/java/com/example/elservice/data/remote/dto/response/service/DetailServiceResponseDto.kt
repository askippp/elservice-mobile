package com.example.elservice.data.remote.dto.response.service

data class DetailServiceWrapper(
	val message: String,
	val data: DetailServiceResponse
)

data class DetailServiceResponse(
	val id: Int,
	val id_customer: Int,
	val id_cabang: Int,
	val id_operator: Int,
	val alamat_service: String?,
	val keluhan: String?,
	val diagnosa: String?,
	val biaya_service: Float?,
	val biaya_kunjungan: Float?,
	val total_biaya: Float?,
	val status: String?,
	val tanggal_masuk: String?,
	val tanggal_selesai: String?,
	val catatan: String?,
	val customer: Customer?,
	val operator: Operator?,
	val teknisis: List<Teknisi> = emptyList(),
	val alats: List<Alat> = emptyList(),
	val spareparts: List<Sparepart> = emptyList()
)

data class Customer(
	val id: Int,
	val id_user: Int?,
	val nama: String?,
	val no_telp: String?,
	val alamat: String?,
	val provinsi: String?,
	val kota: String?,
	val email: String?,
	val foto: String?
)

data class Operator(
	val id: Int,
	val id_user: Int?,
	val id_cabang: Int?,
	val email: String?,
	val nama: String?,
	val no_telp: String?,
	val alamat: String?,
	val foto: String?
)

data class Alat(
	val id: Int,
	val id_kategori: Int?,
	val id_merek: Int?,
	val nama_alat: String?,
	val tipe_model: String?,
	val deskripsi: String?,
	val foto: String?,
	val status: String?
)

data class Sparepart(
	val id: Int,
	val nama: String?,
	val jumlah: Int?,
	val harga: Float?
)

data class Teknisi(
	val id: Int,
	val nama: String?,
	val no_telp: String?
)
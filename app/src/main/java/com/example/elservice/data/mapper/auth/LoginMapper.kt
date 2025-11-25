package com.example.elservice.data.mapper.auth

import com.example.elservice.data.remote.dto.response.auth.LoginResponseDto
import com.example.elservice.domain.model.user.User
import com.example.elservice.domain.model.user.UserDetail
import com.example.elservice.domain.model.user.UserSession

object LoginMapper {
	fun toUserSession(dto: LoginResponseDto): UserSession {
		return UserSession(
			token = dto.token,
			user = User(
				id = dto.user.id,
				username = dto.user.username,
				email = dto.user.email,
				role = dto.user.role
			),
			detail = UserDetail(
				id = dto.detail.id,
				nama = dto.detail.nama,
				telp = dto.detail.no_telp,
				alamat = dto.detail.alamat,
				provinsi = dto.detail.provinsi,
				kota = dto.detail.kota
			)
		)
	}
}

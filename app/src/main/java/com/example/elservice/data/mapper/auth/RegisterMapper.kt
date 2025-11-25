package com.example.elservice.data.mapper.auth

import com.example.elservice.data.remote.dto.response.auth.RegisterResponseDto
import com.example.elservice.domain.model.user.RegisteredUser

object RegisterMapper {
	fun toRegisteredUser(dto: RegisterResponseDto): RegisteredUser {
		return RegisteredUser(
			userId = dto.user.id,
			username = dto.user.username,
			email = dto.user.email,
			role = dto.user.role,
			customerName = dto.customer.nama,
			phone = dto.customer.no_telp,
			city = dto.customer.kota,
			province = dto.customer.provinsi
		)
	}
}

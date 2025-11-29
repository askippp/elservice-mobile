package com.example.elservice.data.remote.api

import com.example.elservice.data.remote.dto.request.auth.LoginRequestDto
import com.example.elservice.data.remote.dto.request.auth.RegisterRequestDto
import com.example.elservice.data.remote.dto.request.service.CreateRequestServiceDto
import com.example.elservice.data.remote.dto.response.auth.LoginResponseDto
import com.example.elservice.data.remote.dto.response.alat.AlatListResponseDto
import com.example.elservice.data.remote.dto.response.auth.RegisterResponseDto
import com.example.elservice.data.remote.dto.response.service.CancelServiceResponseDto
import com.example.elservice.data.remote.dto.response.service.CreateServiceResponseDto
import com.example.elservice.data.remote.dto.response.service.DetailServiceResponse
import com.example.elservice.data.remote.dto.response.service.DetailServiceWrapper
import com.example.elservice.data.remote.dto.response.service.ServiceResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

	@POST("login")
	suspend fun login(@Body body: LoginRequestDto): LoginResponseDto

	@POST("register")
	suspend fun register(@Body body: RegisterRequestDto): RegisterResponseDto

	//Customer Endpoint's
	@GET("customer/alat")
	suspend fun getListAlat(
		@Header("Authorization") token: String
	): AlatListResponseDto

	@POST("service/customer/request")
	suspend fun createCustomerService(
		@Header("Authorization") token: String,
		@Body body: CreateRequestServiceDto
	): CreateServiceResponseDto

	@GET("service/customer/list")
	suspend fun getCustomerServices(
		@Header("Authorization") token: String
	): ServiceResponseDto

	@GET("service/customer/{id_service}")
	suspend fun getDetailCustomerService(
		@Header("Authorization") token: String,
		@Path("id_service") id: Int
	): DetailServiceWrapper

	@PATCH("service/customer/{id_service}/cancel")
	suspend fun cancelService(
		@Header("Authorization") token: String,
		@Path("id_service") idService: Int
	): CancelServiceResponseDto

}
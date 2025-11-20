package com.example.elservice.core.network

import com.example.elservice.core.token.TokenManager
import com.example.elservice.data.remote.api.ApiService
import com.example.elservice.data.remote.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

	private const val BASE_URL = "http://192.168.1.10:8000/api/"

	private val client by lazy {
		OkHttpClient.Builder()
			.addInterceptor(AuthInterceptor { TokenManager.getToken() })
			.build()
	}

	val api: ApiService by lazy {
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.build()
			.create(ApiService::class.java)
	}
}
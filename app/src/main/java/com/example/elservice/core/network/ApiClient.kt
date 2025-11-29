package com.example.elservice.core.network

import com.example.elservice.core.token.TokenManager
import com.example.elservice.data.remote.api.ApiService
import com.example.elservice.data.remote.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

	//10.43.225.148 hotspot
	//192.168.1.6 rumah nenek 4G
	//192.168.1.11 rumah 4G

	private const val BASE_URL = "http://192.168.1.11:8000/api/"

	private val client by lazy {
		val logging = HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		}

		OkHttpClient.Builder()
			.addInterceptor(AuthInterceptor { TokenManager.getToken() })
			.addInterceptor(logging)
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
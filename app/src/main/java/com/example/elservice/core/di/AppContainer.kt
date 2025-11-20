package com.example.elservice.core.di

import android.content.Context
import com.example.elservice.core.network.ApiClient
import com.example.elservice.data.datastore.UserPreferences
import com.example.elservice.data.repository.AuthRepositoryImpl
import com.example.elservice.domain.repository.AuthRepository
import com.example.elservice.domain.usecase.*

class AppContainer(context: Context) {

	private val userPreferences = UserPreferences(context)

	private val authRepository: AuthRepository =
		AuthRepositoryImpl(ApiClient.api, userPreferences)

	val loginUseCase = LoginUseCase(authRepository)
	val saveTokenUseCase = SaveTokenUseCase(authRepository)
	val getTokenUseCase = GetTokenUseCase(authRepository)
	val clearTokenUseCase = ClearTokenUseCase(authRepository)
}

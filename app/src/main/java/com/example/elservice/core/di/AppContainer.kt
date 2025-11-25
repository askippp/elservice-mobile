package com.example.elservice.core.di

import android.content.Context
import com.example.elservice.core.network.ApiClient
import com.example.elservice.data.datastore.UserPreferences
import com.example.elservice.data.repository.AlatRepositoryImpl
import com.example.elservice.data.repository.AuthRepositoryImpl
import com.example.elservice.domain.repository.AlatRepository
import com.example.elservice.domain.repository.AuthRepository
import com.example.elservice.domain.usecase.*
import com.example.elservice.domain.usecase.alat.GetAlatListUseCase
import com.example.elservice.domain.usecase.auth.ClearSessionUseCase
import com.example.elservice.domain.usecase.auth.GetSessionUseCase
import com.example.elservice.domain.usecase.auth.LoginUseCase
import com.example.elservice.domain.usecase.auth.RegisterUseCase
import com.example.elservice.ui.screens.auth.viewmodel.LoginViewModel
import com.example.elservice.ui.screens.auth.viewmodel.RegistrationViewModel

class AppContainer(context: Context) {

	private val userPreferences = UserPreferences(context)

	private val authRepository: AuthRepository =
		AuthRepositoryImpl(ApiClient.api, userPreferences)
	val registerUseCase = RegisterUseCase(authRepository)
	val loginUseCase = LoginUseCase(authRepository)
	val getSessionUseCase = GetSessionUseCase(authRepository)
	val clearSessionUseCase = ClearSessionUseCase(authRepository)

	val registerViewModelFactory = {
		RegistrationViewModel(registerUseCase)
	}

	val loginViewModelFactory = {
		LoginViewModel(loginUseCase)
	}

	private val alatRepository: AlatRepository = AlatRepositoryImpl(ApiClient.api)
	val getAlatListUseCase = GetAlatListUseCase(alatRepository)
}



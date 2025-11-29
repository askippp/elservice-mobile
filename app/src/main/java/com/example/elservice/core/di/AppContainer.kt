package com.example.elservice.core.di

import android.content.Context
import com.example.elservice.core.network.ApiClient
import com.example.elservice.data.datastore.UserPreferences
import com.example.elservice.data.repository.AlatRepositoryImpl
import com.example.elservice.data.repository.AuthRepositoryImpl
import com.example.elservice.data.repository.ServiceRepositoryImpl
import com.example.elservice.domain.repository.AlatRepository
import com.example.elservice.domain.repository.AuthRepository
import com.example.elservice.domain.repository.ServiceRepository
import com.example.elservice.domain.usecase.*
import com.example.elservice.domain.usecase.alat.GetAlatListUseCase
import com.example.elservice.domain.usecase.auth.ClearSessionUseCase
import com.example.elservice.domain.usecase.auth.GetSessionUseCase
import com.example.elservice.domain.usecase.auth.LoginUseCase
import com.example.elservice.domain.usecase.auth.RegisterUseCase
import com.example.elservice.domain.usecase.service.CancelServiceUseCase
import com.example.elservice.domain.usecase.service.CreateServiceUseCase
import com.example.elservice.domain.usecase.service.GetListServiceUseCase
import com.example.elservice.domain.usecase.service.GetServiceDetailUseCase
import com.example.elservice.ui.screens.auth.viewmodel.LoginViewModel
import com.example.elservice.ui.screens.auth.viewmodel.RegistrationViewModel
import com.example.elservice.ui.screens.customer.viewmodel.DetailServiceViewModel
import com.example.elservice.ui.screens.customer.viewmodel.ListServiceViewModel
import com.example.elservice.ui.screens.customer.viewmodel.RequestServiceViewModel

class AppContainer(context: Context) {

	private val userPreferences = UserPreferences(context)

	// AUTH
	private val authRepository: AuthRepository =
		AuthRepositoryImpl(ApiClient.api, userPreferences)

	val registerUseCase = RegisterUseCase(authRepository)
	val loginUseCase = LoginUseCase(authRepository)
	val getSessionUseCase = GetSessionUseCase(authRepository)
	val clearSessionUseCase = ClearSessionUseCase(authRepository)

	val registerViewModelFactory = { RegistrationViewModel(registerUseCase) }
	val loginViewModelFactory = { LoginViewModel(loginUseCase) }

	// ALAT
	private val alatRepository: AlatRepository = AlatRepositoryImpl(ApiClient.api)
	val getAlatListUseCase = GetAlatListUseCase(alatRepository)

	// SERVICE
	private val serviceRepository: ServiceRepository = ServiceRepositoryImpl(ApiClient.api)
	val createServiceUseCase = CreateServiceUseCase(serviceRepository)
	val getListServiceUseCase = GetListServiceUseCase(serviceRepository)
	val getServiceDetailUseCase = GetServiceDetailUseCase(serviceRepository)
	val cancelServiceUseCase = CancelServiceUseCase(serviceRepository)

	val requestServiceViewModelFactory = { token: String ->
		RequestServiceViewModel(
			getAlatListUseCase,
			createServiceUseCase,
			token
		)
	}

	val listServiceViewModelFactory = { token: String ->
		ListServiceViewModel(
			getListServiceUseCase,
			token
		)
	}

	val detailServiceViewModelFactory = { token: String, serviceId: Int ->
		DetailServiceViewModel(
			getServiceDetailUseCase,
			cancelServiceUseCase,
			token,
			serviceId
		)
	}
}
package com.example.elservice.ui.screens.auth.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elservice.data.remote.dto.request.auth.RegisterRequestDto
import com.example.elservice.domain.model.user.RegisteredUser
import com.example.elservice.domain.usecase.auth.RegisterUseCase
import kotlinx.coroutines.launch

class RegistrationViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {

	var loading by mutableStateOf(false)
	var success by mutableStateOf<RegisteredUser?>(null)
	var error by mutableStateOf<String?>(null)

	fun register(body: RegisterRequestDto) {
		viewModelScope.launch {
			try {
				loading = true
				error = null
				val result = registerUseCase(body)
				success = result
			} catch (e: Exception) {
				error = e.message
			} finally {
				loading = false
			}
		}
	}
}

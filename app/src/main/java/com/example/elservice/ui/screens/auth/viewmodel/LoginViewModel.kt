package com.example.elservice.ui.screens.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elservice.domain.model.user.UserSession
import com.example.elservice.domain.usecase.auth.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
	private val loginUseCase: LoginUseCase
) : ViewModel() {

	private val _loading = MutableStateFlow(false)
	val loading: StateFlow<Boolean> = _loading

	private val _error = MutableStateFlow<String?>(null)
	val error: StateFlow<String?> = _error

	private val _session = MutableStateFlow<UserSession?>(null)
	val session: StateFlow<UserSession?> = _session

	fun login(email: String, password: String) {
		viewModelScope.launch {
			_loading.value = true
			_error.value = null

			try {
				val session = loginUseCase(email, password)
				_session.value = session
			} catch (e: Exception) {
				e.printStackTrace()
				_error.value = e.message ?: "Login failed"
			} finally {
				_loading.value = false
			}
		}
	}
}
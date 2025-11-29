package com.example.elservice.ui.screens.customer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elservice.data.remote.dto.response.service.CancelServiceResponseDto
import com.example.elservice.data.remote.dto.response.service.DetailServiceResponse
import com.example.elservice.domain.usecase.service.CancelServiceUseCase
import com.example.elservice.domain.usecase.service.GetServiceDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class DetailServiceState(
	val loading: Boolean = false,
	val error: String? = null,
	val service: DetailServiceResponse? = null
)
class DetailServiceViewModel(
	private val getServiceDetailUseCase: GetServiceDetailUseCase,
	private val cancelServiceUseCase: CancelServiceUseCase,
	private val token: String,
	private val serviceId: Int
) : ViewModel() {

	private val _cancelState = MutableStateFlow<CancelServiceResponseDto?>(null)
	val cancelState: StateFlow<CancelServiceResponseDto?> = _cancelState

	private val _isLoading = MutableStateFlow(false)
	val isLoading: StateFlow<Boolean> = _isLoading

	private val _error = MutableStateFlow<String?>(null)
	val error: StateFlow<String?> = _error

	private val _state = MutableStateFlow(DetailServiceState())
	val state: StateFlow<DetailServiceState> = _state

	init {
		loadDetail()
	}

	private fun loadDetail() {
		viewModelScope.launch {
			try {
				_state.value = _state.value.copy(loading = true)
				val detail = getServiceDetailUseCase(token, serviceId)
				_state.value = _state.value.copy(loading = false, service = detail)
			} catch (e: Exception) {
				_state.value = _state.value.copy(loading = false, error = e.message ?: "Unknown error")
			}
		}
	}

	fun cancelService() {
		viewModelScope.launch {
			_isLoading.value = true
			try {
				val response = cancelServiceUseCase(token, serviceId) // pakai use case
				_cancelState.value = response
				// update detail state juga supaya status service terbaru langsung muncul
				_state.value = _state.value.copy(
					service = _state.value.service?.copy(status = response.data?.status)
				)
			} catch (e: Exception) {
				_error.value = e.localizedMessage
			} finally {
				_isLoading.value = false
			}
		}
	}
}
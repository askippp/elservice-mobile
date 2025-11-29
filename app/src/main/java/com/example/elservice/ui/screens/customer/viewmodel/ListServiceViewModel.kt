package com.example.elservice.ui.screens.customer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elservice.domain.model.service.Service
import com.example.elservice.domain.usecase.service.GetListServiceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

data class ListServiceState(
	val loading: Boolean = false,
	val error: String? = null,
	val services: List<Service> = emptyList()
)

class ListServiceViewModel(
	private val useCase: GetListServiceUseCase,
	private val token: String
) : ViewModel() {

	private val _state = MutableStateFlow(ListServiceState())
	val state: StateFlow<ListServiceState> = _state

	init {
		loadServices()
	}

	fun loadServices() {
		viewModelScope.launch {
			try {
				_state.value = _state.value.copy(loading = true)
				val data = useCase(token)

				_state.value = _state.value.copy(
					loading = false,
					error = null,
					services = data
				)

			} catch (e: Exception) {
				var msg = e.message ?: "Unknown error"

				if (e is HttpException) {
					val body = e.response()?.errorBody()?.string()
					if (!body.isNullOrEmpty()) {
						msg = try {
							JSONObject(body).optString("message", msg)
						} catch (_: Exception) {
							msg
						}
					}
				}

				_state.value = _state.value.copy(
					loading = false,
					error = msg
				)
			}
		}
	}
}

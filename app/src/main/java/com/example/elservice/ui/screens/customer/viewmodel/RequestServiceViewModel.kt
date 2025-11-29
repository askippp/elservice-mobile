package com.example.elservice.ui.screens.customer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elservice.data.remote.dto.request.service.CreateRequestServiceDto
import com.example.elservice.domain.model.alat.Alat
import com.example.elservice.domain.usecase.alat.GetAlatListUseCase
import com.example.elservice.domain.usecase.service.CreateServiceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

data class RequestServiceState(
	val alatList: List<Alat> = emptyList(),
	val loading: Boolean = false,
	val error: String? = null
)

class RequestServiceViewModel(
	private val getAlatListUseCase: GetAlatListUseCase,
	private val createServiceUseCase: CreateServiceUseCase,
	private val sessionToken: String
) : ViewModel() {

	private val _state = MutableStateFlow(RequestServiceState())
	val state: StateFlow<RequestServiceState> = _state

	var selectedAlatIds: Set<Int> = emptySet()
		private set

	init {
		loadAlats()
	}

	private fun loadAlats() {
		viewModelScope.launch {
			try {
				_state.value = _state.value.copy(loading = true)
				val data = getAlatListUseCase(sessionToken)
				_state.value = _state.value.copy(alatList = data, loading = false, error = null)
			} catch (e: Exception) {
				e.printStackTrace()
				_state.value = _state.value.copy(
					loading = false,
					error = e.message ?: "Unknown error"
				)
			}
		}
	}

	fun toggleAlatSelection(id: Int) {
		selectedAlatIds = if (selectedAlatIds.contains(id)) {
			selectedAlatIds - id
		} else {
			selectedAlatIds + id
		}
	}

	fun submitRequest(
		customerId: Int,
		keluhan: String,
		alamatService: String,
		onSuccess: () -> Unit,
		onError: (String) -> Unit
	) {
		if (selectedAlatIds.isEmpty()) {
			onError("Pilih minimal 1 alat")
			return
		}

		viewModelScope.launch {
			try {
				_state.value = _state.value.copy(loading = true)

				val body = CreateRequestServiceDto(
					id_customer = customerId,
					keluhan = keluhan,
					alamat_service = alamatService,
					alat_ids = selectedAlatIds.toList()
				)

				createServiceUseCase(sessionToken, body)

				_state.value = _state.value.copy(loading = false)
				onSuccess()

			} catch (e: Exception) {
				e.printStackTrace()

				var errorMessage = e.message ?: "Unknown error"

				if (e is HttpException) {
					val errorBody = e.response()?.errorBody()?.string()
					if (!errorBody.isNullOrEmpty()) {
						try {
							val json = JSONObject(errorBody)
							errorMessage = json.optString("message", errorMessage)
						} catch (_: Exception) {}
					}
				}

				_state.value = _state.value.copy(loading = false, error = errorMessage)
				onError(errorMessage)
			}
		}
	}
}

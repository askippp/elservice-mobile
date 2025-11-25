package com.example.elservice.ui.screens.customer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elservice.domain.model.alat.Alat
import com.example.elservice.domain.usecase.alat.GetAlatListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RequestServiceState(
	val alatList: List<Alat> = emptyList(),
	val loading: Boolean = false,
	val error: String? = null
)

class RequestServiceViewModel(
	private val getAlatListUseCase: GetAlatListUseCase
) : ViewModel() {

	private val _state = MutableStateFlow(RequestServiceState())
	val state: StateFlow<RequestServiceState> = _state

	init {
		loadAlats()
	}

	private fun loadAlats() {
		viewModelScope.launch {
			try {
				_state.value = _state.value.copy(loading = true)

				val data = getAlatListUseCase()

				_state.value = _state.value.copy(
					alatList = data,
					loading = false,
					error = null
				)

			} catch (e: Exception) {
				e.printStackTrace()
				_state.value = _state.value.copy(
					loading = false,
					error = e.message ?: "Unknown error"
				)
			}
		}
	}
}

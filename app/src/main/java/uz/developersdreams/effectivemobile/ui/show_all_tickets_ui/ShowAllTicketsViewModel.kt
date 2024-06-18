package uz.developersdreams.effectivemobile.ui.show_all_tickets_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.developersdreams.effectivemobile.feature.domain.repository.MainRepository
import javax.inject.Inject

@HiltViewModel
class ShowAllTicketsViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShowTicketsState())
    val uiState = _uiState.asStateFlow()

    init {
        getTickets()
    }

    private fun getTickets() = viewModelScope.launch {
        repository.getTickets().let { response ->
            if (response.isSuccessful && response.body()?.tickets?.isNotEmpty() == true) {
                _uiState.update { state ->
                    state.copy(
                        tickets = response.body()!!.tickets
                    )
                }
            }
        }
    }
}
package uz.developersdreams.effectivemobile.ui.air_tickets_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.developersdreams.effectivemobile.feature.domain.repository.MainRepository
import uz.developersdreams.effectivemobile.feature.domain.repository.UserTextRepository
import uz.developersdreams.effectivemobile.feature.extensions.extractUserTextName
import uz.developersdreams.effectivemobile.feature.util.Constants
import javax.inject.Inject

@HiltViewModel
class AirTicketsViewModel @Inject constructor(
    private val repository: MainRepository,
    private val userTextRepository: UserTextRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AirTicketsState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserTexts()
        getOffers()
    }

    private fun getUserTexts() = viewModelScope.launch {
        userTextRepository.getUserTexts().collectLatest { list ->
            _uiState.update { state ->
                state.copy(
                    fromText = list.extractUserTextName(Constants.FROM),
                    toText = list.extractUserTextName(Constants.TO)
                )
            }
        }
    }

    private fun getOffers() = viewModelScope.launch {
        repository.getOffers().let { response ->
            if (response.isSuccessful && response.body()?.offers?.isNotEmpty() == true) {
                _uiState.update { state ->
                    state.copy(
                        offers = response.body()?.offers!!
                    )
                }
            }
        }
    }
}
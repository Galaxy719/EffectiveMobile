package uz.developersdreams.effectivemobile.ui.search_selected_country_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.developersdreams.effectivemobile.feature.domain.model.user_text.UserText
import uz.developersdreams.effectivemobile.feature.domain.repository.MainRepository
import uz.developersdreams.effectivemobile.feature.domain.repository.UserTextRepository
import uz.developersdreams.effectivemobile.feature.extensions.extractUserText
import uz.developersdreams.effectivemobile.feature.util.Constants
import javax.inject.Inject

@HiltViewModel
class SearchSelectedViewModel @Inject constructor(
    private val repository: UserTextRepository,
    private val mainRepository: MainRepository
) : ViewModel() {

    private var getUserTextJob: Job? = null

    private val _uiState = MutableStateFlow(SearchSelectedState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserTexts()
        getTicketOffers()
    }

    fun onEvent(event: SearchSelectedEvent) {
        when (event) {
            is SearchSelectedEvent.FromText -> {
                saveUserText(event.fromText, Constants.FROM)
            }

            is SearchSelectedEvent.ToText -> {
                saveUserText(event.toText, Constants.TO)
            }

            is SearchSelectedEvent.TvBackText -> {
                _uiState.update { state ->
                    state.copy(
                        tvBackText = event.tvBackText
                    )
                }
            }

            is SearchSelectedEvent.TvDepartureText -> {
                _uiState.update { state ->
                    state.copy(
                        tvDepartureText = event.tvDepartureText
                    )
                }
            }
        }
    }

    private fun getUserTexts() {
        getUserTextJob = repository.getUserTexts().onEach { userTexts ->
            _uiState.update { state ->
                state.copy(
                    userTextFrom = userTexts.extractUserText(Constants.FROM),
                    userTextTo = userTexts.extractUserText(Constants.TO)
                )
            }
            getUserTextJob?.cancel()
        }.launchIn(viewModelScope)
    }

    private fun getTicketOffers() = viewModelScope.launch {
        mainRepository.getTicketOffers().let { response ->
            if (response.isSuccessful && response.body()?.ticketsOffers?.isNotEmpty() == true) {
                _uiState.update { state ->
                    state.copy(
                        ticketOffers = response.body()!!.ticketsOffers
                    )
                }
            }
        }
    }

    private fun saveUserText(text: String, isFrom: Int = Constants.FROM) = viewModelScope.launch {
        var userText = if (isFrom == Constants.FROM) uiState.value.userTextFrom else uiState.value.userTextTo

        userText = userText?.copy(
            text = text
        ) ?: UserText(text = text, isFrom = isFrom)

        repository.insertUserText(userText)
    }
}
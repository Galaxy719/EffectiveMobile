package uz.developersdreams.effectivemobile.ui.search_ui

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
import uz.developersdreams.effectivemobile.feature.domain.repository.UserTextRepository
import uz.developersdreams.effectivemobile.feature.extensions.extractUserText
import uz.developersdreams.effectivemobile.feature.util.Constants
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: UserTextRepository
) : ViewModel() {

    private var getUserTextJob: Job? = null

    private val _uiState = MutableStateFlow(SearchState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserTexts()
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.FromText -> {
                saveUserText(event.fromText, Constants.FROM)
            }

            is SearchEvent.ToText -> {
                saveUserText(event.toText, Constants.TO)
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

    private fun saveUserText(text: String, isFrom: Int = Constants.FROM) = viewModelScope.launch {
        var userText = if (isFrom == Constants.FROM) uiState.value.userTextFrom else uiState.value.userTextTo

        userText = userText?.copy(
            text = text
        ) ?: UserText(text = text, isFrom = isFrom)

        repository.insertUserText(userText)
    }
}
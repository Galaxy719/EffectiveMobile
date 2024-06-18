package uz.developersdreams.effectivemobile.ui.search_ui

import uz.developersdreams.effectivemobile.feature.domain.model.user_text.UserText

data class SearchState (
    val userTextFrom: UserText? = null,
    val userTextTo: UserText? = null
)
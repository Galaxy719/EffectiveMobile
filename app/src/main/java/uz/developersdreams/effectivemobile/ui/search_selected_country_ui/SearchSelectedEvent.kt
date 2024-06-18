package uz.developersdreams.effectivemobile.ui.search_selected_country_ui

sealed class SearchSelectedEvent {

    data class FromText(val fromText: String) : SearchSelectedEvent()

    data class ToText(val toText: String) : SearchSelectedEvent()

    data class TvBackText(val tvBackText: String) : SearchSelectedEvent()

    data class TvDepartureText(val tvDepartureText: String) : SearchSelectedEvent()
}
package uz.developersdreams.effectivemobile.ui.search_ui

sealed class SearchEvent {

    data class FromText(val fromText: String) : SearchEvent()

    data class ToText(val toText: String) : SearchEvent()
}
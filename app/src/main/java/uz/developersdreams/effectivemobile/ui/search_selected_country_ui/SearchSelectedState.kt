package uz.developersdreams.effectivemobile.ui.search_selected_country_ui

import uz.developersdreams.effectivemobile.feature.domain.model.ticket_offers.TicketOffers
import uz.developersdreams.effectivemobile.feature.domain.model.user_text.UserText

data class SearchSelectedState(
    val userTextFrom: UserText? = null,
    val userTextTo: UserText? = null,
    val tvBackText: String? = null,
    val tvDepartureText: String? = null,
    val ticketOffers: List<TicketOffers.TicketsOffer> = emptyList()
)

package uz.developersdreams.effectivemobile.ui.show_all_tickets_ui

import uz.developersdreams.effectivemobile.feature.domain.model.ticket.Ticket

data class ShowTicketsState(
    val tickets: List<Ticket.Ticket> = emptyList()
)

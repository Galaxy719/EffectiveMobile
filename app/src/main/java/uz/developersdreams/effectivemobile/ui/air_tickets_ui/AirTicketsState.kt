package uz.developersdreams.effectivemobile.ui.air_tickets_ui

import uz.developersdreams.effectivemobile.feature.domain.model.offers.OfferModel

data class AirTicketsState (
    val fromText: String = "",
    val toText: String = "",
    val offers: List<OfferModel.Offer> = emptyList()
)
package uz.developersdreams.effectivemobile.feature.domain.repository

import retrofit2.Response
import uz.developersdreams.effectivemobile.feature.domain.model.offers.OfferModel
import uz.developersdreams.effectivemobile.feature.domain.model.ticket.Ticket
import uz.developersdreams.effectivemobile.feature.domain.model.ticket_offers.TicketOffers

interface MainRepository {

    suspend fun getOffers() : Response<OfferModel>

    suspend fun getTicketOffers() : Response<TicketOffers>

    suspend fun getTickets() : Response<Ticket>
}
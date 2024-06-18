package uz.developersdreams.effectivemobile.feature.data.data_source.remote

import retrofit2.Response
import retrofit2.http.GET
import uz.developersdreams.effectivemobile.feature.domain.model.offers.OfferModel
import uz.developersdreams.effectivemobile.feature.domain.model.ticket.Ticket
import uz.developersdreams.effectivemobile.feature.domain.model.ticket_offers.TicketOffers
import uz.developersdreams.effectivemobile.feature.util.Constants

interface ApiService {

    @GET(Constants.OFFERS_URL)
    suspend fun getOffers(): Response<OfferModel>

    @GET(Constants.TICKET_OFFERS_URL)
    suspend fun getTicketOffers(): Response<TicketOffers>

    @GET(Constants.TICKETS_URL)
    suspend fun getTickets(): Response<Ticket>
}
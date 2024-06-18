package uz.developersdreams.effectivemobile.feature.data.data_source.remote

import retrofit2.Response
import uz.developersdreams.effectivemobile.feature.domain.model.offers.OfferModel
import uz.developersdreams.effectivemobile.feature.domain.model.ticket.Ticket
import uz.developersdreams.effectivemobile.feature.domain.model.ticket_offers.TicketOffers
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override suspend fun getOffers(): Response<OfferModel> {
        return apiService.getOffers()
    }

    override suspend fun getTicketOffers(): Response<TicketOffers> {
        return apiService.getTicketOffers()
    }

    override suspend fun getTickets(): Response<Ticket> {
        return apiService.getTickets()
    }
}
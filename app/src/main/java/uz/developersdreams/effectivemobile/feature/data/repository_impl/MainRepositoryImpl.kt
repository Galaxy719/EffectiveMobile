package uz.developersdreams.effectivemobile.feature.data.repository_impl

import retrofit2.Response
import uz.developersdreams.effectivemobile.feature.data.data_source.remote.ApiHelper
import uz.developersdreams.effectivemobile.feature.domain.model.offers.OfferModel
import uz.developersdreams.effectivemobile.feature.domain.model.ticket.Ticket
import uz.developersdreams.effectivemobile.feature.domain.model.ticket_offers.TicketOffers
import uz.developersdreams.effectivemobile.feature.domain.repository.MainRepository

class MainRepositoryImpl(
    private val apiHelper: ApiHelper
) : MainRepository {

    override suspend fun getOffers(): Response<OfferModel> {
        return apiHelper.getOffers()
    }

    override suspend fun getTicketOffers(): Response<TicketOffers> {
        return apiHelper.getTicketOffers()
    }

    override suspend fun getTickets(): Response<Ticket> {
        return apiHelper.getTickets()
    }
}
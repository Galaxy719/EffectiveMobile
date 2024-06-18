package uz.developersdreams.effectivemobile.feature.domain.model.ticket_offers


import com.google.gson.annotations.SerializedName

data class TicketOffers(
    @SerializedName("tickets_offers")
    val ticketsOffers: List<TicketsOffer>
) {
    data class TicketsOffer(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("time_range")
        val timeRange: List<String>,
        @SerializedName("price")
        val price: Price
    ) {
        data class Price(
            @SerializedName("value")
            val value: Int
        )
    }
}
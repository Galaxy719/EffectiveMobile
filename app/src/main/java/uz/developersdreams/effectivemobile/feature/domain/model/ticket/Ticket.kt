package uz.developersdreams.effectivemobile.feature.domain.model.ticket


import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("tickets")
    val tickets: List<Ticket>
) {
    data class Ticket(
        @SerializedName("id")
        val id: Int,
        @SerializedName("badge")
        val badge: String?,
        @SerializedName("price")
        val price: Price,
        @SerializedName("provider_name")
        val providerName: String,
        @SerializedName("company")
        val company: String,
        @SerializedName("departure")
        val departure: Departure,
        @SerializedName("arrival")
        val arrival: Arrival,
        @SerializedName("has_transfer")
        val hasTransfer: Boolean,
        @SerializedName("has_visa_transfer")
        val hasVisaTransfer: Boolean,
        @SerializedName("luggage")
        val luggage: Luggage,
        @SerializedName("hand_luggage")
        val handLuggage: HandLuggage,
        @SerializedName("is_returnable")
        val isReturnable: Boolean,
        @SerializedName("is_exchangable")
        val isExchangable: Boolean
    ) {
        data class Price(
            @SerializedName("value")
            val value: Int
        )

        data class Departure(
            @SerializedName("town")
            val town: String,
            @SerializedName("date")
            val date: String,
            @SerializedName("airport")
            val airport: String
        )

        data class Arrival(
            @SerializedName("town")
            val town: String,
            @SerializedName("date")
            val date: String,
            @SerializedName("airport")
            val airport: String
        )

        data class Luggage(
            @SerializedName("has_luggage")
            val hasLuggage: Boolean,
            @SerializedName("price")
            val price: Price?
        ) {
            data class Price(
                @SerializedName("value")
                val value: Int
            )
        }

        data class HandLuggage(
            @SerializedName("has_hand_luggage")
            val hasHandLuggage: Boolean,
            @SerializedName("size")
            val size: String?
        )
    }
}
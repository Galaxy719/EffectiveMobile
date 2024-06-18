package uz.developersdreams.effectivemobile.feature.domain.model.offers


import com.google.gson.annotations.SerializedName

data class OfferModel(
    @SerializedName("offers")
    val offers: List<Offer>
) {
    data class Offer(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("town")
        val town: String,
        @SerializedName("price")
        val price: Price
    ) {
        data class Price(
            @SerializedName("value")
            val value: Int
        )
    }
}
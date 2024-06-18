package uz.developersdreams.effectivemobile.adapter.air_tickets_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.developersdreams.effectivemobile.R
import uz.developersdreams.effectivemobile.databinding.RvOfferItemBinding
import uz.developersdreams.effectivemobile.feature.domain.model.offers.OfferModel
import uz.developersdreams.effectivemobile.feature.extensions.priceTransform


class OffersRvAdapter : RecyclerView.Adapter<OffersRvAdapter.OffersViewHolder>() {
    private var screenWidth = 0

    inner class OffersViewHolder(private val binding: RvOfferItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(offer: OfferModel.Offer) {
            binding.apply {
                rvImageView.setImageResource(getImageTemp(id = offer.id))
                rvPlaceName.text = offer.title
                rvCityName.text =  offer.town
                rvPrice.text = offer.price.value.priceTransform()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        screenWidth = parent.context.resources.displayMetrics.widthPixels

        val inflater = LayoutInflater.from(parent.context)
        val binding = RvOfferItemBinding.inflate(inflater, parent, false)
        return OffersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        val itemWidth = (screenWidth).div(2.5)

        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = layoutParams.height
        layoutParams.width = itemWidth.toInt()
        holder.itemView.layoutParams = layoutParams

        holder.binding(differ.currentList[position])
    }

    private val diffCallback = object : DiffUtil.ItemCallback<OfferModel.Offer>() {
        override fun areItemsTheSame(oldItem: OfferModel.Offer, newItem: OfferModel.Offer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OfferModel.Offer, newItem: OfferModel.Offer): Boolean {
            return oldItem == newItem
        }
    }

    var differ =  AsyncListDiffer(this, diffCallback)

    private fun getImageTemp(id: Int): Int {
        return when (id) {
            1 -> R.drawable.img_offer_1
            2 -> R.drawable.img_offer_2
            else -> R.drawable.img_offer_3
        }
    }
}
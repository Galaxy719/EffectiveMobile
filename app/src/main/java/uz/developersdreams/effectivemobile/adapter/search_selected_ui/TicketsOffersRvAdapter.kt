package uz.developersdreams.effectivemobile.adapter.search_selected_ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.developersdreams.effectivemobile.databinding.RvDirectFlightsBinding
import uz.developersdreams.effectivemobile.feature.domain.model.ticket_offers.TicketOffers
import uz.developersdreams.effectivemobile.feature.extensions.extractTime
import uz.developersdreams.effectivemobile.feature.extensions.priceTransform

class TicketsOffersRvAdapter : RecyclerView.Adapter<TicketsOffersRvAdapter.TicketsOffersViewHolder>() {

    inner class TicketsOffersViewHolder(private val binding: RvDirectFlightsBinding): RecyclerView.ViewHolder(binding.root) {
        fun binding(item: TicketOffers.TicketsOffer) {
            binding.apply {
                rvNameTv.text = item.title
                rvPriceTv.text = item.price.value.priceTransform()
                rvTimeTv.text = item.timeRange.extractTime()
                rvCircle.setCardBackgroundColor(getCardColor(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsOffersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvDirectFlightsBinding.inflate(inflater, parent, false)
        return TicketsOffersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TicketsOffersViewHolder, position: Int) {
        holder.binding(differ.currentList[position])
    }

    private fun getCardColor(position: Int): Int {
        return when (position) {
            0 -> Color.RED
            1 -> Color.BLUE
            else -> Color.WHITE
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<TicketOffers.TicketsOffer>() {
        override fun areItemsTheSame(oldItem: TicketOffers.TicketsOffer, newItem: TicketOffers.TicketsOffer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TicketOffers.TicketsOffer, newItem: TicketOffers.TicketsOffer): Boolean {
            return oldItem == newItem
        }
    }

    var differ =  AsyncListDiffer(this, diffCallback)
}
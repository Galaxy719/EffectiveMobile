package uz.developersdreams.effectivemobile.adapter.show_all_tickets_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.developersdreams.effectivemobile.databinding.RvShowTicketBinding
import uz.developersdreams.effectivemobile.feature.domain.model.ticket.Ticket
import uz.developersdreams.effectivemobile.feature.extensions.priceTransform
import uz.developersdreams.effectivemobile.feature.extensions.showHideView
import uz.developersdreams.effectivemobile.feature.extensions.substringDate
import uz.developersdreams.effectivemobile.feature.util.getTimeDifference

class ShowAllTicketRvAdapter : RecyclerView.Adapter<ShowAllTicketRvAdapter.TicketViewHolder>() {

    inner class TicketViewHolder(private val binding: RvShowTicketBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(item: Ticket.Ticket) {
            binding.apply {
                rvBadgeContainer.showHideView(isShow = !item.badge.isNullOrBlank())
                item.badge?.let {
                    rvBadgeTv.text = it
                }
                rvPriceTv.text = item.price.value.priceTransform()
                rvTimeDepartureTv.text = item.departure.date.substringDate()
                rvCodeDepartureTv.text = item.departure.airport
                rvTimeArrivalTv.text = item.arrival.date.substringDate()
                rvCodeArrivalTv.text = item.arrival.airport
                rvOtherInfo.text = getTimeDifference(item.departure.date, item.arrival.date)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvShowTicketBinding.inflate(inflater, parent, false)
        return TicketViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.binding(differ.currentList[position])
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Ticket.Ticket>() {
        override fun areItemsTheSame(oldItem: Ticket.Ticket, newItem: Ticket.Ticket): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Ticket.Ticket, newItem: Ticket.Ticket): Boolean {
            return oldItem == newItem
        }
    }

    var differ =  AsyncListDiffer(this, diffCallback)
}
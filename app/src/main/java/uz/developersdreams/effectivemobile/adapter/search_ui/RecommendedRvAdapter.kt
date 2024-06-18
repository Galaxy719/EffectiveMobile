package uz.developersdreams.effectivemobile.adapter.search_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.developersdreams.effectivemobile.databinding.RvRecommendedPlaceBinding
import uz.developersdreams.effectivemobile.feature.domain.model.recommended_place.RecommendedPlace

class RecommendedRvAdapter : RecyclerView.Adapter<RecommendedRvAdapter.RecommendedViewHolder>() {

    private lateinit var clickListener: OnClickListener

    interface OnClickListener {
        fun onItemClicked(
            item: RecommendedPlace
        )
    }

    fun setOnItemClickListener(listener: OnClickListener) {
        clickListener = listener
    }

    inner class RecommendedViewHolder(private val binding: RvRecommendedPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(item: RecommendedPlace) {
            binding.apply {
                rvImageView.setImageResource(item.img)
                rvNameTv.text = item.name
                rvDescTv.text = item.desc
                root.setOnClickListener {
                    clickListener.onItemClicked(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvRecommendedPlaceBinding.inflate(inflater, parent, false)
        return RecommendedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {
        holder.binding(differ.currentList[position])
    }

    private val diffCallback = object : DiffUtil.ItemCallback<RecommendedPlace>() {
        override fun areItemsTheSame(oldItem: RecommendedPlace, newItem: RecommendedPlace): Boolean {
            return oldItem.img == newItem.img
        }

        override fun areContentsTheSame(oldItem: RecommendedPlace, newItem: RecommendedPlace): Boolean {
            return oldItem == newItem
        }
    }

    var differ =  AsyncListDiffer(this, diffCallback)
}
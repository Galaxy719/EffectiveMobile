package uz.developersdreams.effectivemobile.adapter.search_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.developersdreams.effectivemobile.databinding.RvSearchItemBinding
import uz.developersdreams.effectivemobile.feature.domain.model.search_item.SearchItem

class SearchItemAdapter : RecyclerView.Adapter<SearchItemAdapter.SearchViewHolder>() {
    private var screenWidth = 0
    private lateinit var clickListener: OnClickListener

    interface OnClickListener {
        fun onItemClicked(
            searchItem: SearchItem
        )
    }

    fun setOnItemClickListener(listener: OnClickListener) {
        clickListener = listener
    }

    inner class SearchViewHolder(private val binding: RvSearchItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun binding(item: SearchItem) {
            binding.apply {
                rvImageView.setImageResource(item.img)
                rvTextView.text = item.text

                rvImageContainer.setOnClickListener {
                    clickListener.onItemClicked(
                        item
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        screenWidth = parent.context.resources.displayMetrics.widthPixels

        val inflater = LayoutInflater.from(parent.context)
        val binding = RvSearchItemBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val itemWidth = (screenWidth).div(4)

        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = layoutParams.height
        layoutParams.width = itemWidth
        holder.itemView.layoutParams = layoutParams

        holder.binding(differ.currentList[position])
    }

    private val diffCallback = object : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem.img == newItem.img
        }

        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem == newItem
        }
    }

    var differ =  AsyncListDiffer(this, diffCallback)
}
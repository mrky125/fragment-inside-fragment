package com.example.fragmentinsidefragment.ui.concat

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentinsidefragment.databinding.ChildItemBinding

class ItemPagingAdapter(
    private val viewLifecycleOwner: LifecycleOwner
) : PagedListAdapter<String, ItemPagingAdapter.PagingListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PagingListViewHolder(
            ChildItemBinding.inflate(layoutInflater, parent, false),
            viewLifecycleOwner
        )
    }

    override fun onBindViewHolder(holder: PagingListViewHolder, position: Int) {
        Log.d("adapter", "position: $position")
        holder.bind(getItem(position))
    }

    class PagingListViewHolder(
        private val binding: ChildItemBinding,
        private val viewLifecycleOwner: LifecycleOwner
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String?) {
            binding.tvDescription.text = item
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
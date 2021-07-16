package com.example.fragmentinsidefragment.ui.concat

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentinsidefragment.databinding.ChildItemBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ItemPagingAdapter(
    private val viewLifecycleOwner: LifecycleOwner
) : ListAdapter<String, ItemPagingAdapter.PagingListViewHolder>(DiffCallback) {

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
        fun bind(item: String) {
            binding.tvDescription.text = item

            // APIレスポンス取得後にUIを描画する動作を、TextViewのデータを変えて模擬実装
            viewLifecycleOwner.lifecycleScope.launch {
                delay(2000)
                binding.tvDescription.text = "$item\n\n\n\n\n"
            }
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
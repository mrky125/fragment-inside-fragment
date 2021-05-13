package com.example.fragmentinsidefragment.ui.fragmentinside

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

class ChildItemListAdapter(
    private val viewLifecycleOwner: LifecycleOwner
) : ListAdapter<String, ChildItemListAdapter.ChildListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChildListViewHolder(
            ChildItemBinding.inflate(layoutInflater, parent, false),
            viewLifecycleOwner
        )
    }

    override fun onBindViewHolder(holder: ChildListViewHolder, position: Int) {
        Log.d("adapter", "position: $position")
        holder.bind(getItem(position))
    }

    class ChildListViewHolder(
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
}

private object DiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

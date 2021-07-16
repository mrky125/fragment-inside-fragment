package com.example.fragmentinsidefragment.ui.concat

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentinsidefragment.databinding.ItemNetworkStateBinding

class NetworkStateAdapter(
    private val viewLifecycleOwner: LifecycleOwner
) : ListAdapter<Boolean, NetworkStateAdapter.NetworkStateViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkStateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NetworkStateViewHolder(
            ItemNetworkStateBinding.inflate(layoutInflater, parent, false),
            viewLifecycleOwner
        )
    }

    override fun onBindViewHolder(holder: NetworkStateViewHolder, position: Int) {
        Log.d("network state adapter", "position: $position")
        holder.bind(getItem(position))
    }

    class NetworkStateViewHolder(
        private val binding: ItemNetworkStateBinding,
        private val viewLifecycleOwner: LifecycleOwner
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Boolean) {
        }
    }
}

private object DiffCallback : DiffUtil.ItemCallback<Boolean>() {
    override fun areItemsTheSame(oldItem: Boolean, newItem: Boolean): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Boolean, newItem: Boolean): Boolean {
        return oldItem == newItem
    }
}
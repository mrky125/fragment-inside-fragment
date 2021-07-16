package com.example.fragmentinsidefragment.ui.concat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentinsidefragment.databinding.FragmentConcatBinding

class ConcatFragment : Fragment() {

    private lateinit var binding: FragmentConcatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModel()
        return FragmentConcatBinding.inflate(inflater).let {
            binding = it
            it.root
        }
    }

    private fun setupViewModel() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setupConcatAdapter()
    }

    private fun setupConcatAdapter() {
        val firstAdapter = ItemPagingAdapter(viewLifecycleOwner)
        val secondAdapter = NetworkStateAdapter(viewLifecycleOwner)

        val concatAdapter = ConcatAdapter(firstAdapter, secondAdapter)
        binding.recyclerView.apply {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL)
            )
        }
        val list = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
        firstAdapter.submitList(list)
        secondAdapter.submitList(listOf(false, true, false))
    }
}
package com.example.fragmentinsidefragment.ui.concat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentinsidefragment.databinding.FragmentConcatBinding

class ConcatFragment : Fragment() {

    private lateinit var binding: FragmentConcatBinding
    private lateinit var viewModel: ConcatViewModel
    private lateinit var firstAdapter: ItemPagingAdapter
    private lateinit var secondAdapter: NetworkStateAdapter

    companion object {
        private const val TAG = "ConcatFragment"
    }

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
        viewModel = ViewModelProvider.NewInstanceFactory().create(ConcatViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setupConcatAdapter()
        setupSubmitList()
    }

    private fun setupConcatAdapter() {
        firstAdapter = ItemPagingAdapter(viewLifecycleOwner)
        secondAdapter = NetworkStateAdapter(viewLifecycleOwner)

        val concatAdapter = ConcatAdapter(firstAdapter, secondAdapter)
        binding.recyclerView.apply {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL)
            )
        }
    }

    private fun setupSubmitList() {
        viewModel.listStrItems?.observe(viewLifecycleOwner) {
            Log.d(TAG, "updated list: $it")
            firstAdapter.submitList(it)
            secondAdapter.submitList(listOf(false, true, false))
        }
    }
}
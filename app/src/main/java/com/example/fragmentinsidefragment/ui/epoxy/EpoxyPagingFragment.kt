package com.example.fragmentinsidefragment.ui.epoxy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentinsidefragment.databinding.FragmentEpoxyPagingBinding
import com.example.fragmentinsidefragment.ui.epoxy.controller.EpoxyPagingController
import com.example.fragmentinsidefragment.viewmodel.EpoxyPagingViewModel

class EpoxyPagingFragment : Fragment() {

    companion object {
        private const val TAG = "EpoxyPagingFragment"
    }

    private lateinit var binding: FragmentEpoxyPagingBinding
    private lateinit var viewModel: EpoxyPagingViewModel
    private lateinit var epoxyController: EpoxyPagingController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupViewModel()
        return FragmentEpoxyPagingBinding.inflate(inflater).let {
            binding = it
            it.root
        }
    }

    private fun setupViewModel() {
        viewModel = EpoxyPagingViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupEpoxyAdapter()
        observeListItem()
    }

    private fun setupEpoxyAdapter() {
        epoxyController = EpoxyPagingController()
        binding.epoxyRecyclerView.apply {
            adapter = epoxyController.adapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun observeListItem() {
        viewModel.listStrItems?.observe(viewLifecycleOwner, {
            Log.d(TAG, "updated list: $it")
            epoxyController.submitList(it)
        })
    }
}
package com.example.fragmentinsidefragment.ui.epoxy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentinsidefragment.databinding.FragmentEpoxyPagingBinding
import com.example.fragmentinsidefragment.ui.epoxy.controller.EpoxyMultiPagingController
import com.example.fragmentinsidefragment.viewmodel.EpoxyMultiPagingViewModel

class EpoxyMultiPagingFragment : Fragment() {

    companion object {
        private const val TAG = "EpoxyMultiPagingFragment"
    }

    private lateinit var binding: FragmentEpoxyPagingBinding
    private lateinit var viewModel: EpoxyMultiPagingViewModel
    private lateinit var epoxyController: EpoxyMultiPagingController

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
        viewModel = EpoxyMultiPagingViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupEpoxyAdapter()
        observeListItem()
        observeNetworkState()
    }

    private fun setupEpoxyAdapter() {
        epoxyController = EpoxyMultiPagingController(viewModel, viewLifecycleOwner)
        binding.epoxyRecyclerView.apply {
            adapter = epoxyController.adapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun observeListItem() {
        viewModel.listMultiItem.observe(viewLifecycleOwner, {
            Log.d(TAG, "updated list: $it")
            epoxyController.submitList(it)
        })
    }

    private fun observeNetworkState() {
        viewModel.networkState.observe(viewLifecycleOwner, {
            Log.d(TAG, "network state: $it")
            epoxyController.setNetworkState(it)
            if (it == false) {
                epoxyController.requestModelBuild()
            }
        })
    }
}
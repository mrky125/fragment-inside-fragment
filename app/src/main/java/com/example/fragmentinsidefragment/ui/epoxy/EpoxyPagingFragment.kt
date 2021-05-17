package com.example.fragmentinsidefragment.ui.epoxy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentinsidefragment.databinding.FragmentEpoxyPagingBinding
import com.example.fragmentinsidefragment.ui.epoxy.controller.EpoxyPagingController

class EpoxyPagingFragment : Fragment() {

    private lateinit var binding: FragmentEpoxyPagingBinding

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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupEpoxyAdapter()
    }

    private fun setupEpoxyAdapter() {
        val controller = EpoxyPagingController()
        binding.epoxyRecyclerView.apply {
            adapter = controller.adapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }
}
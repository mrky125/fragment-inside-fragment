package com.example.fragmentinsidefragment.ui.epoxy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fragmentinsidefragment.databinding.FragmentEpoxyParentBinding
import com.example.fragmentinsidefragment.viewmodel.EpoxyViewModel

class EpoxyParentFragment : Fragment() {

    private lateinit var binding: FragmentEpoxyParentBinding
    private lateinit var viewModel: EpoxyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupViewModel()
        return FragmentEpoxyParentBinding.inflate(inflater).let {
            binding = it
            binding.viewModel = viewModel
            it.root
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider.NewInstanceFactory().create(EpoxyViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupEpoxyAdapter()
        setViewModelList()
    }

    private fun setViewModelList() {
        viewModel.apply {
            setFirstList()
            setSecondList()
        }
    }

    private fun setupEpoxyAdapter() {
        val controller = EpoxyChildController().apply {
            spanCount = EpoxyChildController.SPAN_SIZE_NORMAL
        }
        binding.epoxyRecyclerView.apply {
            adapter = controller.adapter
            layoutManager =
                GridLayoutManager(requireActivity(), EpoxyChildController.SPAN_SIZE_NORMAL).apply {
                    spanSizeLookup = controller.spanSizeLookup
                }
        }
        controller.setData(emptyList(), listOf("foo", "bar"))
        viewModel.firstList.observe(viewLifecycleOwner, {
            controller.setData(it, listOf("foo", "bar"))
        })
    }

}
package com.example.fragmentinsidefragment.ui.epoxy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentinsidefragment.data.EpoxyListModel
import com.example.fragmentinsidefragment.databinding.FragmentEpoxyParentBinding
import com.example.fragmentinsidefragment.ui.epoxy.controller.EpoxyChildController
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
        val controller = EpoxyChildController(viewModel, viewLifecycleOwner).apply {
            spanCount = EpoxyChildController.SPAN_SIZE_NORMAL
        }
        val gridLayoutManager = GridLayoutManager(requireActivity(), EpoxyChildController.SPAN_SIZE_NORMAL).apply {
            spanSizeLookup = controller.spanSizeLookup
        }
        // 先頭のアイテムが挿入されたらスクロール位置を先頭に移動する
        controller.adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                if (positionStart == 0) {
                    gridLayoutManager.scrollToPosition(0)
                }
            }
        })
        binding.epoxyRecyclerView.apply {
            adapter = controller.adapter
            layoutManager = gridLayoutManager
        }
        controller.setData(createEpoxyListModel())
        viewModel.firstList.observe(viewLifecycleOwner, {
            controller.setData(createEpoxyListModel())
        })
        viewModel.secondList.observe(viewLifecycleOwner, {
            controller.setData(createEpoxyListModel())
        })
    }

    private fun createEpoxyListModel(): EpoxyListModel {
        return EpoxyListModel(
            viewModel.firstList.value,
            viewModel.secondList.value,
            viewModel.buttons
        )
    }

}
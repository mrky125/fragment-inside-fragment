package com.example.fragmentinsidefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fragmentinsidefragment.databinding.FragmentEpoxyParentBinding

class EpoxyParentFragment : Fragment() {

    private lateinit var binding: FragmentEpoxyParentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentEpoxyParentBinding.inflate(inflater).let {
            binding = it
            it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEpoxyAdapter()
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
        controller.setData(
            listOf("one", "two", "three", "four", "five", "six", "seven", "eight"),
            listOf("foo", "bar")
        )
    }

}
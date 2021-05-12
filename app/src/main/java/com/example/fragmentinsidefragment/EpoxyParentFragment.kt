package com.example.fragmentinsidefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        val controller = EpoxyChildController()
        binding.epoxyRecyclerView.adapter = controller.adapter
        controller.setData(listOf("one", "two", "three", "four", "five"), listOf("foo", "bar"))
    }

}
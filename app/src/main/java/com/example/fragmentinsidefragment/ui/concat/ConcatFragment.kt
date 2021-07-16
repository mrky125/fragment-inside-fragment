package com.example.fragmentinsidefragment.ui.concat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    }
}
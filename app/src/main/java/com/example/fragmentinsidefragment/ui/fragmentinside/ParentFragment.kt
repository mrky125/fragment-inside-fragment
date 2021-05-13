package com.example.fragmentinsidefragment.ui.fragmentinside

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentinsidefragment.databinding.FragmentParentBinding

class ParentFragment : Fragment() {

    private lateinit var binding: FragmentParentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentParentBinding.inflate(inflater).let {
            binding = it
            it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
         * 各FragmentでRecyclerViewを持っているが、全部のアイテムのViewがメモリに展開されたままになる
         * RecyclerViewのように、異なるViewType（Fragmentなど）を当てはめるように実装できないか・・？
         * 実装概念はありそう、RecyclerView in Epoxy で実現できるか調べる
         * https://techblog.lclco.com/entry/2019/10/02/100000
         */
        childFragmentManager.beginTransaction().apply {
            add(binding.containerFirst.id, ChildFragmentOne(), "child_first")
            add(binding.containerSecond.id, ChildFragmentOne(), "child_second")
            add(binding.containerThird.id, ChildFragmentOne(), "child_third")
            add(binding.containerFourth.id, ChildFragmentOne(), "child_fourth")
            add(binding.containerFifth.id, ChildFragmentOne(), "child_fifth")
            commit()
        }
    }
}
package com.example.fragmentinsidefragment.ui.epoxy.controller

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.airbnb.epoxy.TypedEpoxyController
import com.example.fragmentinsidefragment.model.EpoxyListModel
import com.example.fragmentinsidefragment.itemBody
import com.example.fragmentinsidefragment.ui.epoxy.model.itemFooter
import com.example.fragmentinsidefragment.ui.epoxy.model.itemHeader
import com.example.fragmentinsidefragment.viewmodel.EpoxyViewModel

class EpoxyChildController(
    private val viewModel: EpoxyViewModel,
    private val lifecycleOwner: LifecycleOwner
) : TypedEpoxyController<EpoxyListModel>() {

    companion object {
        const val SPAN_SIZE_HALF = 1
        const val SPAN_SIZE_NORMAL = 2
    }

    override fun buildModels(data: EpoxyListModel?) {
        data?.firstList?.also {
            setupFirstList(it)
        }
        data?.buttons?.also {
            setupButtons(it)
        }
        data?.secondList?.also {
            setupSecondList(it)
        }
        data?.buttons?.also {
            setupButtons(it)
        }
        data?.firstList?.also {
            setupFirstList(it)
        }
        repeat(3) {
            itemFooter {
                spanSizeOverride { _, _, _ ->
                    SPAN_SIZE_NORMAL
                }
                id("$it footer")
                footer("footer!")
            }
        }
    }

    private fun setupFirstList(list: List<LiveData<String>>) {
        list.forEach {
            itemHeader {
                spanSizeOverride { _, position, _ ->
                    when (position) {
                        0, 1 -> SPAN_SIZE_NORMAL
                        else -> SPAN_SIZE_HALF
                    }
                }
                id(it.toString()) // idは一意にする（仮でLiveDataのインスタンスそのままにしている）
                viewModel(viewModel)
                lifecycleOwner(lifecycleOwner)
                item(it)
            }
        }
    }

    private fun setupButtons(list: List<String>) {
        list.forEach {
            itemBody {
                spanSizeOverride { _, _, _ ->
                    SPAN_SIZE_NORMAL
                }
                id(it)
                buttonTitle(it)
            }
        }
    }

    private fun setupSecondList(list: List<LiveData<String>>) {
        list.forEach {
            itemHeader {
                id(it.toString()) // idは一意にする（仮でLiveDataのインスタンスそのままにしている）
                viewModel(viewModel)
                lifecycleOwner(lifecycleOwner)
                item(it)
            }
        }
    }
}
package com.example.fragmentinsidefragment.ui.epoxy

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.airbnb.epoxy.Typed2EpoxyController
import com.example.fragmentinsidefragment.data.EpoxyListModel
import com.example.fragmentinsidefragment.itemBody
import com.example.fragmentinsidefragment.ui.epoxy.model.itemFooter
import com.example.fragmentinsidefragment.ui.epoxy.model.itemHeader

class EpoxyChildController : Typed2EpoxyController<LifecycleOwner, EpoxyListModel>() {

    companion object {
        const val SPAN_SIZE_HALF = 1
        const val SPAN_SIZE_NORMAL = 2
    }

    override fun buildModels(lifecycleOwner: LifecycleOwner?, data: EpoxyListModel?) {
        data?.firstList?.also {
            setupFirstList(lifecycleOwner, it)
        }
        data?.buttons?.also {
            setupButtons(it)
        }
        data?.secondList?.also {
            setupSecondList(it)
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

    private fun setupFirstList(lifecycleOwner: LifecycleOwner?, list: List<LiveData<String>>) {
        list.forEach {
            itemHeader {
                spanSizeOverride { _, position, _ ->
                    when (position) {
                        0, 1 -> SPAN_SIZE_NORMAL
                        else -> SPAN_SIZE_HALF
                    }
                }
                id(it.value)
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
                id(it.value)
            }
        }
    }
}
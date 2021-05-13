package com.example.fragmentinsidefragment

import com.airbnb.epoxy.Typed2EpoxyController

class EpoxyChildController : Typed2EpoxyController<List<String>, List<String>>() {

    companion object {
        const val SPAN_SIZE_HALF = 1
        const val SPAN_SIZE_NORMAL = 2
    }

    override fun buildModels(headers: List<String>?, buttons: List<String>?) {
        headers?.forEach {
            itemHeader {
                spanSizeOverride { totalSpanCount, position, itemCount ->
                    when (position) {
                        0, 1 -> SPAN_SIZE_NORMAL
                        else -> SPAN_SIZE_HALF
                    }
                }
                id(it)
                descriptionText(it)
            }
        }
        buttons?.forEach {
            itemBody {
                spanSizeOverride { _, _, _ ->
                    SPAN_SIZE_NORMAL
                }
                id(it)
                buttonTitle(it)
            }
        }
        headers?.forEach {
            itemHeader {
                id(it)
                descriptionText(it)
            }
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
}
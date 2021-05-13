package com.example.fragmentinsidefragment

import com.airbnb.epoxy.Typed2EpoxyController

class EpoxyChildController : Typed2EpoxyController<List<String>, List<String>>() {

    override fun buildModels(headers: List<String>?, buttons: List<String>?) {
        headers?.forEach {
            itemHeader {
                id(it)
                descriptionText(it)
            }
        }
        buttons?.forEach {
            itemBody {
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
                id("$it footer")
                footer("footer!")
            }
        }
    }
}
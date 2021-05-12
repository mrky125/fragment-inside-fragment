package com.example.fragmentinsidefragment

import com.airbnb.epoxy.TypedEpoxyController

class EpoxyChildController : TypedEpoxyController<List<String>>() {

    override fun buildModels(data: List<String>?) {
        data?.forEach { it ->
            headerView {
                id(it)
                title(it)
            }
        }
        bodyItem {
            id("foo")
            buttonTitle("foo")
        }
        data?.forEach { it ->
            headerView {
                id(it)
                title(it)
            }
        }
    }
}
package com.example.fragmentinsidefragment.ui.epoxy.controller

import androidx.lifecycle.MutableLiveData
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.fragmentinsidefragment.model.MultiPaging
import com.example.fragmentinsidefragment.ui.epoxy.model.ItemFooterModel_
import com.example.fragmentinsidefragment.ui.epoxy.model.ItemMainModel_

class EpoxyMultiPagingController : PagedListEpoxyController<MultiPaging>() {

    override fun buildItemModel(currentPosition: Int, item: MultiPaging?): EpoxyModel<*> {
        return when (item) {
            is MultiPaging.Footer -> {
                ItemFooterModel_().apply {
                    id("$item$currentPosition")
                    footer(item.toString())
                }
            }
            is MultiPaging.MainItem -> {
                ItemMainModel_()
                    .apply {
                        id("$item$currentPosition")
                        item.also {
                            item(MutableLiveData(item.toString()))
                        }
                    }
            }
            else -> throw Exception("Must extend a parent sealed class.")
        }
    }
}
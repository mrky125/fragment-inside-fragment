package com.example.fragmentinsidefragment.ui.epoxy.controller

import androidx.lifecycle.MutableLiveData
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.fragmentinsidefragment.ui.epoxy.model.ItemFooterModel_
import com.example.fragmentinsidefragment.ui.epoxy.model.ItemHeaderModel_

class EpoxyPagingController : PagedListEpoxyController<String>() {

    override fun buildItemModel(currentPosition: Int, item: String?): EpoxyModel<*> {
        return when (currentPosition) {
            3, 4, 10, 11, 24, 30, 31, 40, 41, 42, 63, 67, 77, 89, 93 -> {
                ItemFooterModel_().apply {
                    id(currentPosition)
                    footer(item)
                }
            }
            else -> {
                ItemHeaderModel_().apply {
                    id(currentPosition)
                    item?.also {
                        item(MutableLiveData(it))
                    }
                }
            }
        }
    }
}
package com.example.fragmentinsidefragment.ui.epoxy.controller

import androidx.lifecycle.MutableLiveData
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.fragmentinsidefragment.ui.epoxy.model.ItemHeaderModel_

class EpoxyPagingController : PagedListEpoxyController<String>() {

    override fun buildItemModel(currentPosition: Int, item: String?): EpoxyModel<*> {
        return ItemHeaderModel_().apply {
            id(currentPosition)
            item?.also {
                item(MutableLiveData(it))
            }
        }
    }
}
package com.example.fragmentinsidefragment.ui.epoxy.controller

import androidx.lifecycle.LifecycleOwner
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.fragmentinsidefragment.model.MultiPaging
import com.example.fragmentinsidefragment.ui.epoxy.model.ItemFooterModel_
import com.example.fragmentinsidefragment.ui.epoxy.model.ItemMainForMulti_
import com.example.fragmentinsidefragment.viewmodel.EpoxyMultiPagingViewModel

class EpoxyMultiPagingController(
    private val viewModel: EpoxyMultiPagingViewModel,
    private val lifecycleOwner: LifecycleOwner
) : PagedListEpoxyController<MultiPaging>() {

    override fun buildItemModel(currentPosition: Int, item: MultiPaging?): EpoxyModel<*> {
        return when (item) {
            is MultiPaging.Footer -> {
                ItemFooterModel_().apply {
                    id("$item$currentPosition")
                    footer(item.toString())
                }
            }
            is MultiPaging.MainItem -> {
                ItemMainForMulti_().apply {
                    id("$item$currentPosition")
                    itemMain(item)
                    viewModel(this@EpoxyMultiPagingController.viewModel)
                    lifecycleOwner(this@EpoxyMultiPagingController.lifecycleOwner)
                }
            }
            else -> throw Exception("Must extend a parent sealed class.")
        }
    }
}
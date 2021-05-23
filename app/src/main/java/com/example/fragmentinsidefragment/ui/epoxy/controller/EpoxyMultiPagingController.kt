package com.example.fragmentinsidefragment.ui.epoxy.controller

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.fragmentinsidefragment.model.MultiPaging
import com.example.fragmentinsidefragment.ui.epoxy.model.ItemFooterModel_
import com.example.fragmentinsidefragment.ui.epoxy.model.ItemMainForMulti_
import com.example.fragmentinsidefragment.ui.epoxy.model.itemError
import com.example.fragmentinsidefragment.viewmodel.EpoxyMultiPagingViewModel

class EpoxyMultiPagingController(
    private val viewModel: EpoxyMultiPagingViewModel,
    private val lifecycleOwner: LifecycleOwner
) : PagedListEpoxyController<MultiPaging>() {

    private var networkState: Boolean? = null

    companion object {
        private const val TAG = "EpoxyMultiPagingController"
    }

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

    fun setNetworkState(argState: Boolean) {
        Log.d(TAG, "state: $argState")
        networkState = argState
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        Log.d(TAG, "addModels: $models")
        super.addModels(models)

        // display error item at bottom of list adapter
        if (networkState == false) {
            itemError {
                id("error")
                viewModel(viewModel)
            }
        }
    }
}
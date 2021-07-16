package com.example.fragmentinsidefragment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.fragmentinsidefragment.datasource.factory.EpoxyMultiPagingDataSourceFactory
import com.example.fragmentinsidefragment.model.Listing
import com.example.fragmentinsidefragment.model.MultiPaging

class EpoxyMultiPagingViewModel : ViewModel() {

    companion object {
        const val PAGE_SIZE = 5
    }

    private val result = getList()
    val listMultiItem = result.pagedList
    val networkState = result.networkState

    private fun getList(): Listing<MultiPaging> {
        val pagingDataSourceFactory = EpoxyMultiPagingDataSourceFactory()
        val pageListConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .build()
        val livePagedList = LivePagedListBuilder(pagingDataSourceFactory, pageListConfig)
            .build()
        return Listing(
            livePagedList,
            pagingDataSourceFactory.dataSourceLiveData.switchMap {
                it.networkState
            },
            retry = {
                pagingDataSourceFactory.dataSourceLiveData.value?.retryAllFailed()
            }
        )
    }

    fun tapItem(item: MultiPaging.MainItem) {
        Log.d("viewModel", "tapped item: $item")
        item.name.value = "${item.name.value} tapped!"
    }

    fun tapRetry() {
        result.retry.invoke()
    }
}
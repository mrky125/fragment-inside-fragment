package com.example.fragmentinsidefragment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.fragmentinsidefragment.datasource.factory.EpoxyMultiPagingDataSourceFactory
import com.example.fragmentinsidefragment.model.MultiPaging

class EpoxyMultiPagingViewModel : ViewModel() {

    companion object {
        const val PAGE_SIZE = 5
    }

    var listMultiItem: LiveData<PagedList<MultiPaging>>? = null

    init {
        val pagingDataSourceFactory = EpoxyMultiPagingDataSourceFactory()
        val pageListConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .build()
        listMultiItem = LivePagedListBuilder(pagingDataSourceFactory, pageListConfig)
            .build()
    }

    fun tapItem(item: MultiPaging.MainItem) {
        Log.d("viewModel","tapped item: $item")
        item.name.value = "${item.name.value} tapped!"
    }
}
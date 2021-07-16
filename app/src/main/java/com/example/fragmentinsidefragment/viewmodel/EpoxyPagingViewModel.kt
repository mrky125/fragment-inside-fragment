package com.example.fragmentinsidefragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.fragmentinsidefragment.datasource.factory.EpoxyPagingDataSourceFactory

class EpoxyPagingViewModel : ViewModel() {

    companion object {
        const val PAGE_SIZE = 10
    }

    var listStrItems: LiveData<PagedList<String>>? = null

    init {
        val pagingDataSourceFactory = EpoxyPagingDataSourceFactory()
        val pageListConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .build()
        listStrItems = LivePagedListBuilder(pagingDataSourceFactory, pageListConfig)
            .build()
    }

}
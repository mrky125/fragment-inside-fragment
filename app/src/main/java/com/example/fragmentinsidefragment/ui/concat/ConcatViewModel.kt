package com.example.fragmentinsidefragment.ui.concat

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.fragmentinsidefragment.datasource.factory.EpoxyPagingDataSourceFactory

class ConcatViewModel : ViewModel() {

    companion object {
        const val PAGE_SIZE = 15
    }

    var listStrItems: LiveData<PagedList<String>>? = null

    init {
        val pagingDataSourceFactory = EpoxyPagingDataSourceFactory(PAGE_SIZE)
        val pageListConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .build()
        listStrItems = LivePagedListBuilder(pagingDataSourceFactory, pageListConfig)
            .build()
    }
}
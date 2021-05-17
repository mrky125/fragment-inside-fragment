package com.example.fragmentinsidefragment.datasource

import androidx.paging.PageKeyedDataSource

class EpoxyPagingDataSource : PageKeyedDataSource<Int, String>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, String>
    ) {
        val list = listOf("1", "2", "3", "4", "5")
        callback.onResult(list, null, 2)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, String>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, String>) {
        val list = listOf("1", "2", "3", "4", "5")
        callback.onResult(list, params.key.plus(1))
    }

}
package com.example.fragmentinsidefragment.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource

class EpoxyPagingDataSource(
    private val pageSize: Int
) : PageKeyedDataSource<Int, String>() {

    companion object {
        private const val TAG = "PageKeyedDataSource"
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, String>
    ) {
        val list = MutableList(pageSize) { count ->
            count.toString()
        }
        Log.d(TAG, "list: $list")
        callback.onResult(list, null, 2)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, String>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, String>) {
        val list = MutableList(pageSize) { count ->
            (((params.key - 1) * pageSize) + count).toString()
        }
        Log.d(TAG, "list: $list")
        callback.onResult(list, params.key.plus(1))
    }

}
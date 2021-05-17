package com.example.fragmentinsidefragment.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.fragmentinsidefragment.viewmodel.EpoxyPagingViewModel

class EpoxyPagingDataSource : PageKeyedDataSource<Int, String>() {

    companion object {
        private const val TAG = "PageKeyedDataSource"
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, String>
    ) {
        val list = MutableList(EpoxyPagingViewModel.PAGE_SIZE) { count ->
            count.toString()
        }
        Log.d(TAG, "list: $list")
        callback.onResult(list, null, 2)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, String>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, String>) {
        val list = MutableList(EpoxyPagingViewModel.PAGE_SIZE) { count ->
            (((params.key - 1) * EpoxyPagingViewModel.PAGE_SIZE) + count).toString()
        }
        Log.d(TAG, "list: $list")
        callback.onResult(list, params.key.plus(1))
    }

}
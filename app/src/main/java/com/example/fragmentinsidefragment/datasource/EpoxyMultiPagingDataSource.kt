package com.example.fragmentinsidefragment.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.fragmentinsidefragment.model.MultiPaging

class EpoxyMultiPagingDataSource : PageKeyedDataSource<Int, MultiPaging>() {

    companion object {
        private const val TAG = "PageKeyedDataSource"
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MultiPaging>
    ) {
        val list = MutableList(1) { count ->
            MultiPaging.Carousel(count.toString())
        } as List<MultiPaging>
        Log.d(TAG, "list: $list")
        callback.onResult(list, null, 2)
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MultiPaging>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MultiPaging>
    ) {
        val list = when (params.key) {
            in 2..4, in 10..13 -> {
                MutableList(4) { count ->
                    MultiPaging.MainItem(count.toString())
                }
            }
            in 6..8, in 17..20 -> {
                MutableList(2) { count ->
                    MultiPaging.Carousel(count.toString())
                }
            }
            else -> {
                MutableList(4) { count ->
                    MultiPaging.MainItem(count.toString())
                }
            }
        } as List<MultiPaging>
        Log.d(TAG, "list: $list")
        callback.onResult(list, params.key.plus(1))
    }

}
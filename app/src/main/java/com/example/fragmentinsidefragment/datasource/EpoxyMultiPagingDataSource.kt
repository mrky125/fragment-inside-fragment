package com.example.fragmentinsidefragment.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.fragmentinsidefragment.model.MultiPaging

class EpoxyMultiPagingDataSource : PageKeyedDataSource<Int, MultiPaging>() {

    companion object {
        private const val TAG = "MultiPageKeyedDataSource"
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MultiPaging>
    ) {
        val list = MutableList(1) { count ->
            MultiPaging.Footer(count.toString())
        } as List<MultiPaging>
        Log.d(TAG, "loadInitial list: $list")
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
            4, 5, 7, 9, 11, 14, 19, 21, 25, 26, 30, 31, 35, 41, 45, 48, 58, 60, 62, 70, 73 -> {
                MutableList(2) { count ->
                    MultiPaging.Footer("page: ${params.key}, count: $count")
                }
            }
            else -> {
                MutableList(3) { count ->
                    MultiPaging.MainItem(MutableLiveData("page: ${params.key}, count: $count"))
                }
            }
        } as List<MultiPaging>
        Log.d(TAG, "loadAfter page: ${params.key}, list: $list")
        callback.onResult(list, params.key.plus(1))
    }

}
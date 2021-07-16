package com.example.fragmentinsidefragment.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.fragmentinsidefragment.model.MultiPaging
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EpoxyMultiPagingDataSource : PageKeyedDataSource<Int, MultiPaging>() {

    val networkState = MutableLiveData<Boolean>()

    private var retry: (() -> Unit)? = null

    companion object {
        private const val TAG = "MultiPageKeyedDataSource"
    }

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.invoke()
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
        GlobalScope.launch {
            // 擬似ネットワーク遅延
            delay((30..200).random().toLong())

            // 1/10の確率で擬似的にエラーを起こす
            val errorPercent = (0..100).random() % 10 == 0
            if (errorPercent) {
                networkState.postValue(false)
                retry = {
                    loadAfter(params, callback)
                }
                return@launch
            }

            // 正常系とみなしてデータをコールバック
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

            networkState.postValue(true)
            // とりあえず30ページを最後とみなして次ページは設定しない
            val nextPage = if (params.key == 30) null else params.key+1
            callback.onResult(list, nextPage)
        }
    }

}
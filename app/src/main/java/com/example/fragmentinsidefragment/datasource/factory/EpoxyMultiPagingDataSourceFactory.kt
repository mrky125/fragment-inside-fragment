package com.example.fragmentinsidefragment.datasource.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.fragmentinsidefragment.datasource.EpoxyMultiPagingDataSource
import com.example.fragmentinsidefragment.model.MultiPaging

class EpoxyMultiPagingDataSourceFactory : DataSource.Factory<Int, MultiPaging>() {

    val dataSourceLiveData = MutableLiveData<EpoxyMultiPagingDataSource>()

    override fun create(): DataSource<Int, MultiPaging> {
        return EpoxyMultiPagingDataSource().also {
            dataSourceLiveData.postValue(it)
        }
    }
}
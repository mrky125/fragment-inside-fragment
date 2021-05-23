package com.example.fragmentinsidefragment.datasource.factory

import androidx.paging.DataSource
import com.example.fragmentinsidefragment.datasource.EpoxyMultiPagingDataSource
import com.example.fragmentinsidefragment.model.MultiPaging

class EpoxyMultiPagingDataSourceFactory : DataSource.Factory<Int, MultiPaging>() {

    override fun create(): DataSource<Int, MultiPaging> {
        return EpoxyMultiPagingDataSource()
    }
}
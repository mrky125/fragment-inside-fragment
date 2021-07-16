package com.example.fragmentinsidefragment.datasource.factory

import androidx.paging.DataSource
import com.example.fragmentinsidefragment.datasource.EpoxyPagingDataSource

class EpoxyPagingDataSourceFactory(private val pageSize: Int) : DataSource.Factory<Int, String>() {

    override fun create(): DataSource<Int, String> {
        return EpoxyPagingDataSource(pageSize)
    }
}
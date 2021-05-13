package com.example.fragmentinsidefragment.data

import androidx.lifecycle.LiveData

data class EpoxyListModel(
    val firstList: List<LiveData<String>>?,
    val secondList: List<LiveData<String>>?,
    val buttons: List<String>?
)
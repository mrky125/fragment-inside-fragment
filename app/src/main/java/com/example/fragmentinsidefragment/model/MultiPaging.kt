package com.example.fragmentinsidefragment.model

import androidx.lifecycle.MutableLiveData

sealed class MultiPaging {
    data class Footer(val name: String = "") : MultiPaging()
    data class MainItem(val name: MutableLiveData<String>) : MultiPaging()
}
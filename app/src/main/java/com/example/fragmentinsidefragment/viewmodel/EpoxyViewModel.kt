package com.example.fragmentinsidefragment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EpoxyViewModel : ViewModel() {

    val firstList = MutableLiveData(listOf(MutableLiveData<String>()))
    val secondList = MutableLiveData(listOf(MutableLiveData<String>()))
    val buttons = listOf("foo", "bar")

    fun setFirstList() {
        viewModelScope.launch {
            delay(1000)
            val list = listOf("one", "two", "three", "four", "five", "six", "seven", "eight")
            firstList.value = list.map { MutableLiveData(it) }
            delay(3000)
            firstList.value?.forEach {
                it.value = "updated! ${it.value}"
            }
        }
    }

    fun setSecondList() {
        viewModelScope.launch {
            delay(2000)
            val list = listOf("one", "two", "three", "four", "five", "six")
            secondList.value = list.map { MutableLiveData(it) }
        }
    }

    fun tapItem(itemValue: String) {
        Log.d("viewModel","tapped item: $itemValue")
        firstList.value?.forEach {
            if (it.value == itemValue) {
                it.value = "$itemValue clicked!"
            }
        }
    }

}
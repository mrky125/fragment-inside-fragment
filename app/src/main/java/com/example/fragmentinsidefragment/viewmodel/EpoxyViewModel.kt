package com.example.fragmentinsidefragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EpoxyViewModel : ViewModel() {

    val firstList: MutableLiveData<List<String>> = MutableLiveData(emptyList())
    val secondList: MutableLiveData<List<String>> = MutableLiveData(emptyList())

    fun setFirstList() {
        viewModelScope.launch {
            delay(1000)
            firstList.value = listOf("one", "two", "three", "four", "five", "six", "seven", "eight")
        }
    }

    fun setSecondList() {
        viewModelScope.launch {
            delay(1000)
            secondList.value = listOf("one", "two", "three", "four", "five", "six")
        }
    }

}
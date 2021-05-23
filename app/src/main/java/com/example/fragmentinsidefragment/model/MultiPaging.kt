package com.example.fragmentinsidefragment.model

sealed class MultiPaging {
    data class Footer(val name: String = "") : MultiPaging()
    data class MainItem(val name: String = "") : MultiPaging()
}
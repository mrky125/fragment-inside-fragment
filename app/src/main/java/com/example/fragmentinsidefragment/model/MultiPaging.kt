package com.example.fragmentinsidefragment.model

sealed class MultiPaging {
    data class Carousel(val name: String = "") : MultiPaging()
    data class MainItem(val name: String = "") : MultiPaging()
}
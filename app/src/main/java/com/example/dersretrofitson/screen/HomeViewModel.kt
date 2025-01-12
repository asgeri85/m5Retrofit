package com.example.dersretrofitson.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    // var counter: Int = 0

    val counter: MutableLiveData<Int> = MutableLiveData(0)

    fun artir() {
        val deger = counter.value
        counter.value = deger!! + 1

    }

    fun azalt() {
        val deger = counter.value
        counter.value = deger!! - 1
    }
}
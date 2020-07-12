package com.blank.chapter10.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val count: MutableLiveData<Int> = MutableLiveData(0)
    val _count: LiveData<Int> = count

    fun getCount() = count

    /*fun start() {
        count.observe(getApplication(), Observer {

        })
    }*/

    fun increment() {
        count.value = count.value?.plus(1)
    }

    fun decrement() {
        count.value = count.value?.minus(1)
    }
}
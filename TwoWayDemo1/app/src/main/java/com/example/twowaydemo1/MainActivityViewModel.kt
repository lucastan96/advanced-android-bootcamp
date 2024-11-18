package com.example.twowaydemo1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var inputName = MutableLiveData<String>()

    init {
        inputName.value = "Lucas"
    }
}
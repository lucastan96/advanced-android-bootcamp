package com.lucastan.didemo

import android.util.Log
import javax.inject.Inject

class ServiceProvider @Inject constructor() {
    init {
        Log.i("TAG", "Service provider constructed")
    }

    fun getServiceProvider() {
        Log.i("TAG", "Service provider connected")
    }
}
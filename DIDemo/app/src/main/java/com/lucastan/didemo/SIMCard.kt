package com.lucastan.didemo

import android.util.Log
import javax.inject.Inject

class SIMCard @Inject constructor(private val serviceProvider: ServiceProvider) {
    init {
        Log.i("TAG", "SIM Card constructed")
    }

    fun getConnection() {
        serviceProvider.getServiceProvider()
    }
}
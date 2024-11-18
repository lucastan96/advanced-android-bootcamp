package com.lucastan.didemo

import android.util.Log

class MemoryCard {
    init {
        Log.i("TAG", "Memory card constructed")
    }

    fun getSpaceAvailability() {
        Log.i("TAG", "Memory space available")
    }
}
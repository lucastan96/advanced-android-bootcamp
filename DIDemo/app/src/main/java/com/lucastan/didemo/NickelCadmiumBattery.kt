package com.lucastan.didemo

import android.util.Log
import javax.inject.Inject

class NickelCadmiumBattery @Inject constructor() : Battery {
    override fun getPower() {
        Log.i("TAG", "Nickel Cadmium battery powered up")
    }
}
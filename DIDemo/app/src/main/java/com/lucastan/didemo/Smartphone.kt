package com.lucastan.didemo

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Smartphone @Inject constructor(
    private val battery: Battery,
    private val simCard: SIMCard,
    private val memoryCard: MemoryCard
) {
    init {
        battery.getPower()
        simCard.getConnection()
        memoryCard.getSpaceAvailability()
        Log.i("TAG", "Smartphone constructed")
    }

    fun makeACallWithRecording() {
        Log.i("TAG", "Calling.....")
    }
}


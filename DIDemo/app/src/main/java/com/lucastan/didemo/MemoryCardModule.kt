package com.lucastan.didemo

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class MemoryCardModule(private val memorySize: Int) {

    @Provides
    fun providesMemoryCard(): MemoryCard {
        Log.i("TAG", "Memory size is $memorySize")
        return MemoryCard()
    }
}
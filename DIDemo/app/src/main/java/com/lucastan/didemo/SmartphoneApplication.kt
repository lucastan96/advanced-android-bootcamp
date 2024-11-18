package com.lucastan.didemo

import android.app.Application

class SmartphoneApplication : Application() {
    lateinit var smartphoneComponent: SmartphoneComponent

    override fun onCreate() {
        smartphoneComponent = initSmartphone()
        super.onCreate()
    }

    private fun initSmartphone(): SmartphoneComponent {
        return DaggerSmartphoneComponent.builder()
            .memoryCardModule(MemoryCardModule(1000))
            .build()
    }
}
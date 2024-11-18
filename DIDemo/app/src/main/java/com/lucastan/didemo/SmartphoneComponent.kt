package com.lucastan.didemo

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MemoryCardModule::class, NickelCadmiumBatteryModule::class])
interface SmartphoneComponent {
    fun inject(mainActivity: MainActivity)
}
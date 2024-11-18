package com.lucastan.didemo

import dagger.Binds
import dagger.Module

@Module
abstract class NickelCadmiumBatteryModule {

    @Binds
    abstract fun bindsNickelCadmiumBattery(nickelCadmiumBattery: NickelCadmiumBattery): Battery
}
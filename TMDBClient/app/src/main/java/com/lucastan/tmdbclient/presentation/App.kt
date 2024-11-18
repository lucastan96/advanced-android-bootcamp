package com.lucastan.tmdbclient.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()

//class App : Application(), Injector {
//    private lateinit var appComponent: AppComponent
//
//    override fun onCreate() {
//        super.onCreate()
//        appComponent = DaggerAppComponent.builder()
//            .appModule(AppModule(applicationContext))
//            .netModule(NetModule(BuildConfig.BASE_URL))
//            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
//            .build()
//    }
//
//    override fun createMovieSubcomponent(): MovieSubcomponent {
//        return appComponent.movieSubcomponent().create()
//    }
//
//    override fun createTvSubcomponent(): TvSubcomponent {
//        return appComponent.tvSubcomponent().create()
//    }
//
//    override fun createActorSubcomponent(): ActorSubcomponent {
//        return appComponent.actorSubcomponent().create()
//    }
//}
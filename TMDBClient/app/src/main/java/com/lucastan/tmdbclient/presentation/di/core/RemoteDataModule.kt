package com.lucastan.tmdbclient.presentation.di.core

import com.lucastan.tmdbclient.BuildConfig
import com.lucastan.tmdbclient.data.api.TMDBService
import com.lucastan.tmdbclient.data.repository.actor.datasource.ActorRemoteDataSource
import com.lucastan.tmdbclient.data.repository.actor.datasourceimpl.ActorRemoteDataSourceImpl
import com.lucastan.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.lucastan.tmdbclient.data.repository.movie.datasourceimpl.MovieRemoteDataSourceImpl
import com.lucastan.tmdbclient.data.repository.tv.datasource.TvRemoteDataSource
import com.lucastan.tmdbclient.data.repository.tv.datasourceimpl.TvRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
//class RemoteDataModule(private val apiKey: String) {
class RemoteDataModule() {
    @Singleton
    @Provides
    fun providesMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource {
//        return MovieRemoteDataSourceImpl(tmdbService, apiKey)
        return MovieRemoteDataSourceImpl(tmdbService, BuildConfig.API_KEY)
    }

    @Singleton
    @Provides
    fun providesTvRemoteDataSource(tmdbService: TMDBService): TvRemoteDataSource {
//        return TvRemoteDataSourceImpl(tmdbService, apiKey)
        return TvRemoteDataSourceImpl(tmdbService, BuildConfig.API_KEY)
    }

    @Singleton
    @Provides
    fun providesActorRemoteDataSource(tmdbService: TMDBService): ActorRemoteDataSource {
//        return ActorRemoteDataSourceImpl(tmdbService, apiKey)
        return ActorRemoteDataSourceImpl(tmdbService, BuildConfig.API_KEY)
    }
}
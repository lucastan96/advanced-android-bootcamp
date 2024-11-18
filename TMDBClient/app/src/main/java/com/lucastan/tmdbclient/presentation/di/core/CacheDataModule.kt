package com.lucastan.tmdbclient.presentation.di.core

import com.lucastan.tmdbclient.data.repository.actor.datasource.ActorCacheDataSource
import com.lucastan.tmdbclient.data.repository.actor.datasourceimpl.ActorCacheDataSourceImpl
import com.lucastan.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.lucastan.tmdbclient.data.repository.movie.datasourceimpl.MovieCacheDataSourceImpl
import com.lucastan.tmdbclient.data.repository.tv.datasource.TvCacheDataSource
import com.lucastan.tmdbclient.data.repository.tv.datasourceimpl.TvCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheDataModule {
    @Singleton
    @Provides
    fun providesMovieCacheDataSource(): MovieCacheDataSource {
        return MovieCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun providesTvCacheDataSource(): TvCacheDataSource {
        return TvCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun providesActorCacheDataSource(): ActorCacheDataSource {
        return ActorCacheDataSourceImpl()
    }
}
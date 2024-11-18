package com.lucastan.tmdbclient.presentation.di.core

import com.lucastan.tmdbclient.data.db.ActorDao
import com.lucastan.tmdbclient.data.db.MovieDao
import com.lucastan.tmdbclient.data.db.TvDao
import com.lucastan.tmdbclient.data.repository.actor.datasource.ActorLocalDataSource
import com.lucastan.tmdbclient.data.repository.actor.datasourceimpl.ActorLocalDataSourceImpl
import com.lucastan.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.lucastan.tmdbclient.data.repository.movie.datasourceimpl.MovieLocalDataSourceImpl
import com.lucastan.tmdbclient.data.repository.tv.datasource.TvLocalDataSource
import com.lucastan.tmdbclient.data.repository.tv.datasourceimpl.TvLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun providesMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun providesTvLocalDataSource(tvDao: TvDao): TvLocalDataSource {
        return TvLocalDataSourceImpl(tvDao)
    }

    @Singleton
    @Provides
    fun providesActorLocalDataSource(actorDao: ActorDao): ActorLocalDataSource {
        return ActorLocalDataSourceImpl(actorDao)
    }
}
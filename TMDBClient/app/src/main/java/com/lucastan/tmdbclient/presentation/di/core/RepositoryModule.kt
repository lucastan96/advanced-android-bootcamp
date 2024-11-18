package com.lucastan.tmdbclient.presentation.di.core

import com.lucastan.tmdbclient.data.repository.actor.ActorRepositoryImpl
import com.lucastan.tmdbclient.data.repository.actor.datasource.ActorCacheDataSource
import com.lucastan.tmdbclient.data.repository.actor.datasource.ActorLocalDataSource
import com.lucastan.tmdbclient.data.repository.actor.datasource.ActorRemoteDataSource
import com.lucastan.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.lucastan.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.lucastan.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.lucastan.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.lucastan.tmdbclient.data.repository.tv.TvRepositoryImpl
import com.lucastan.tmdbclient.data.repository.tv.datasource.TvCacheDataSource
import com.lucastan.tmdbclient.data.repository.tv.datasource.TvLocalDataSource
import com.lucastan.tmdbclient.data.repository.tv.datasource.TvRemoteDataSource
import com.lucastan.tmdbclient.domain.repository.ActorRepository
import com.lucastan.tmdbclient.domain.repository.MovieRepository
import com.lucastan.tmdbclient.domain.repository.TvRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun providesMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieRemoteDataSource,
            movieLocalDataSource,
            movieCacheDataSource
        )
    }

    @Singleton
    @Provides
    fun providesTvRepository(
        tvRemoteDataSource: TvRemoteDataSource,
        tvLocalDataSource: TvLocalDataSource,
        tvCacheDataSource: TvCacheDataSource
    ): TvRepository {
        return TvRepositoryImpl(
            tvRemoteDataSource,
            tvLocalDataSource,
            tvCacheDataSource
        )
    }

    @Singleton
    @Provides
    fun providesActorRepository(
        actorRemoteDataSource: ActorRemoteDataSource,
        actorLocalDataSource: ActorLocalDataSource,
        actorCacheDataSource: ActorCacheDataSource
    ): ActorRepository {
        return ActorRepositoryImpl(
            actorRemoteDataSource,
            actorLocalDataSource,
            actorCacheDataSource
        )
    }
}
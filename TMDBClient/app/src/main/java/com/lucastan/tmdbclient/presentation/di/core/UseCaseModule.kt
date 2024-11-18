package com.lucastan.tmdbclient.presentation.di.core

import com.lucastan.tmdbclient.domain.repository.ActorRepository
import com.lucastan.tmdbclient.domain.repository.MovieRepository
import com.lucastan.tmdbclient.domain.repository.TvRepository
import com.lucastan.tmdbclient.domain.usecase.GetActorsUseCase
import com.lucastan.tmdbclient.domain.usecase.GetMoviesUseCase
import com.lucastan.tmdbclient.domain.usecase.GetTvsUseCase
import com.lucastan.tmdbclient.domain.usecase.UpdateActorsUseCase
import com.lucastan.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.lucastan.tmdbclient.domain.usecase.UpdateTvsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun providesGetMoviesUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    fun providesUpdateMoviesUseCase(movieRepository: MovieRepository): UpdateMoviesUseCase {
        return UpdateMoviesUseCase(movieRepository)
    }

    @Provides
    fun providesGetTvsUseCase(tvRepository: TvRepository): GetTvsUseCase {
        return GetTvsUseCase(tvRepository)
    }

    @Provides
    fun providesUpdateTvsUseCase(tvRepository: TvRepository): UpdateTvsUseCase {
        return UpdateTvsUseCase(tvRepository)
    }

    @Provides
    fun providesGetActorsUseCase(actorRepository: ActorRepository): GetActorsUseCase {
        return GetActorsUseCase(actorRepository)
    }

    @Provides
    fun providesUpdateActorsUseCase(actorRepository: ActorRepository): UpdateActorsUseCase {
        return UpdateActorsUseCase(actorRepository)
    }
}
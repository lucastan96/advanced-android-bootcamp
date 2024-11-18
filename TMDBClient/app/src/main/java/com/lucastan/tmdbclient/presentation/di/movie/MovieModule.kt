package com.lucastan.tmdbclient.presentation.di.movie

import com.lucastan.tmdbclient.domain.usecase.GetMoviesUseCase
import com.lucastan.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.lucastan.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class MovieModule {
//    @MovieScope
    @ActivityScoped
    @Provides
    fun providesMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(
            getMoviesUseCase,
            updateMoviesUseCase
        )
    }
}
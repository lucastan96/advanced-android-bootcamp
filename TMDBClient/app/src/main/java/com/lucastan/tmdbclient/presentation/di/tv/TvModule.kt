package com.lucastan.tmdbclient.presentation.di.tv

import com.lucastan.tmdbclient.domain.usecase.GetTvsUseCase
import com.lucastan.tmdbclient.domain.usecase.UpdateTvsUseCase
import com.lucastan.tmdbclient.presentation.tv.TvViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class TvModule {
//    @TvScope
    @ActivityScoped
    @Provides
    fun providesTvViewModelFactory(
        getTvsUseCase: GetTvsUseCase,
        updateTvsUseCase: UpdateTvsUseCase
    ): TvViewModelFactory {
        return TvViewModelFactory(
            getTvsUseCase,
            updateTvsUseCase
        )
    }
}
package com.lucastan.tmdbclient.presentation.di.actor

import com.lucastan.tmdbclient.domain.usecase.GetActorsUseCase
import com.lucastan.tmdbclient.domain.usecase.UpdateActorsUseCase
import com.lucastan.tmdbclient.presentation.actor.ActorViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActorModule {
//    @ActorScope
    @ActivityScoped
    @Provides
    fun providesActorViewModelFactory(
        getActorsUseCase: GetActorsUseCase,
        updateActorsUseCase: UpdateActorsUseCase
    ): ActorViewModelFactory {
        return ActorViewModelFactory(
            getActorsUseCase,
            updateActorsUseCase
        )
    }
}
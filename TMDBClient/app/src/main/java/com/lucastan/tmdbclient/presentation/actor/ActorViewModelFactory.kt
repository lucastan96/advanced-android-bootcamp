package com.lucastan.tmdbclient.presentation.actor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lucastan.tmdbclient.domain.usecase.GetActorsUseCase
import com.lucastan.tmdbclient.domain.usecase.UpdateActorsUseCase

class ActorViewModelFactory(
    private val getActorsUseCase: GetActorsUseCase,
    private val updateActorsUseCase: UpdateActorsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActorViewModel(getActorsUseCase, updateActorsUseCase) as T
    }
}
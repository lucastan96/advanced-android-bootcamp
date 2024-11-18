package com.lucastan.tmdbclient.presentation.actor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lucastan.tmdbclient.domain.usecase.GetActorsUseCase
import com.lucastan.tmdbclient.domain.usecase.UpdateActorsUseCase

class ActorViewModel(
    private val getActorsUseCase: GetActorsUseCase,
    private val updateActorsUseCase: UpdateActorsUseCase
) : ViewModel() {
    fun getActors() = liveData {
        val actorList = getActorsUseCase.execute()
        emit(actorList)
    }

    fun updateActors() = liveData {
        val actorList = updateActorsUseCase.execute()
        emit(actorList)
    }
}
package com.lucastan.tmdbclient.domain.usecase

import com.lucastan.tmdbclient.data.model.actor.Actor
import com.lucastan.tmdbclient.domain.repository.ActorRepository

class UpdateActorsUseCase(private val actorRepository: ActorRepository) {
    suspend fun execute(): List<Actor>? = actorRepository.updateActors()
}
package com.lucastan.tmdbclient.domain.repository

import com.lucastan.tmdbclient.data.model.actor.Actor

interface ActorRepository {
    suspend fun getActors(): List<Actor>?
    suspend fun updateActors(): List<Actor>?
}
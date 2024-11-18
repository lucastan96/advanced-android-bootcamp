package com.lucastan.tmdbclient.data.repository.actor.datasource

import com.lucastan.tmdbclient.data.model.actor.Actor

interface ActorLocalDataSource {
    suspend fun getActorsFromDB(): List<Actor>
    suspend fun saveActorsToDB(actors: List<Actor>)
    suspend fun clearActorsFromDb()
}
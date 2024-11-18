package com.lucastan.tmdbclient.data.repository.actor.datasource

import com.lucastan.tmdbclient.data.model.actor.Actor

interface ActorCacheDataSource {
    suspend fun getActorsFromCache(): List<Actor>
    suspend fun saveActorsToCache(actors: List<Actor>)
}
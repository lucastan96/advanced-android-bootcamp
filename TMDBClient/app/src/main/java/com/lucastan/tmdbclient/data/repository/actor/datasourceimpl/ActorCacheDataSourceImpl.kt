package com.lucastan.tmdbclient.data.repository.actor.datasourceimpl

import com.lucastan.tmdbclient.data.model.actor.Actor
import com.lucastan.tmdbclient.data.repository.actor.datasource.ActorCacheDataSource

class ActorCacheDataSourceImpl : ActorCacheDataSource {
    private var actorList = ArrayList<Actor>()

    override suspend fun getActorsFromCache(): List<Actor> {
        return actorList
    }

    override suspend fun saveActorsToCache(actors: List<Actor>) {
        actorList.clear()
        actorList = ArrayList(actors)
    }
}
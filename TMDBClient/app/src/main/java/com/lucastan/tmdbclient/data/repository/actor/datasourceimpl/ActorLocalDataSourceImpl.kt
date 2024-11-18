package com.lucastan.tmdbclient.data.repository.actor.datasourceimpl

import com.lucastan.tmdbclient.data.db.ActorDao
import com.lucastan.tmdbclient.data.model.actor.Actor
import com.lucastan.tmdbclient.data.repository.actor.datasource.ActorLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActorLocalDataSourceImpl(private val actorDao: ActorDao) : ActorLocalDataSource {
    override suspend fun getActorsFromDB(): List<Actor> {
        return actorDao.getActors()
    }

    override suspend fun saveActorsToDB(actors: List<Actor>) {
        CoroutineScope(Dispatchers.IO).launch {
            actorDao.saveActors(actors)
        }
    }

    override suspend fun clearActorsFromDb() {
        CoroutineScope(Dispatchers.IO).launch {
            actorDao.deleteAllActors()
        }
    }
}
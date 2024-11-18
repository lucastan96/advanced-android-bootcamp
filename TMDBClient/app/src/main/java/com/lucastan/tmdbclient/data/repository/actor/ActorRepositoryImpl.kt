package com.lucastan.tmdbclient.data.repository.actor

import android.util.Log
import com.lucastan.tmdbclient.data.model.actor.Actor
import com.lucastan.tmdbclient.data.repository.actor.datasource.ActorCacheDataSource
import com.lucastan.tmdbclient.data.repository.actor.datasource.ActorLocalDataSource
import com.lucastan.tmdbclient.data.repository.actor.datasource.ActorRemoteDataSource
import com.lucastan.tmdbclient.domain.repository.ActorRepository

class ActorRepositoryImpl(
    private val actorRemoteDataSource: ActorRemoteDataSource,
    private val actorLocalDataSource: ActorLocalDataSource,
    private val actorCacheDataSource: ActorCacheDataSource
) : ActorRepository {
    override suspend fun getActors(): List<Actor> {
        return getActorsFromCache()
    }

    override suspend fun updateActors(): List<Actor> {
        val newActorList = getActorsFromAPI()
        actorLocalDataSource.clearActorsFromDb()
        actorLocalDataSource.saveActorsToDB(newActorList)
        actorCacheDataSource.saveActorsToCache(newActorList)

        return newActorList
    }

    private suspend fun getActorsFromAPI(): List<Actor> {
        lateinit var actorList: List<Actor>
        try {
            val response = actorRemoteDataSource.getActors()
            val body = response.body()
            if (body != null) {
                actorList = body.actors
            }
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }
        return actorList
    }

    private suspend fun getActorsFromDB(): List<Actor> {
        lateinit var actorList: List<Actor>
        try {
            actorList = actorLocalDataSource.getActorsFromDB()
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        if (actorList.isNotEmpty()) {
            return actorList
        } else {
            actorList = getActorsFromAPI()
            actorLocalDataSource.saveActorsToDB(actorList)
        }

        return actorList
    }

    private suspend fun getActorsFromCache(): List<Actor> {
        lateinit var actorList: List<Actor>
        try {
            actorList = actorCacheDataSource.getActorsFromCache()
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        if (actorList.isNotEmpty()) {
            return actorList
        } else {
            actorList = getActorsFromDB()
            actorCacheDataSource.saveActorsToCache(actorList)
        }

        return actorList
    }
}
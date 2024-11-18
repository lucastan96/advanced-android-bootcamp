package com.lucastan.tmdbclient.data.repository.actor.datasource

import com.lucastan.tmdbclient.data.model.actor.ActorList
import retrofit2.Response

interface ActorRemoteDataSource {
    suspend fun getActors(): Response<ActorList>
}
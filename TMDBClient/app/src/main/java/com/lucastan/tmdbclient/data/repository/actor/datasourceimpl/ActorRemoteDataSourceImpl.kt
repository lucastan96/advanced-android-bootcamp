package com.lucastan.tmdbclient.data.repository.actor.datasourceimpl

import com.lucastan.tmdbclient.data.api.TMDBService
import com.lucastan.tmdbclient.data.model.actor.ActorList
import com.lucastan.tmdbclient.data.repository.actor.datasource.ActorRemoteDataSource
import retrofit2.Response

class ActorRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : ActorRemoteDataSource {
    override suspend fun getActors(): Response<ActorList> = tmdbService.getPopularActors(apiKey)
}
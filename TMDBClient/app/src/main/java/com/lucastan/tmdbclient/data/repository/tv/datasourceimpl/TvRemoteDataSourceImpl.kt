package com.lucastan.tmdbclient.data.repository.tv.datasourceimpl

import com.lucastan.tmdbclient.data.api.TMDBService
import com.lucastan.tmdbclient.data.model.tv.TvList
import com.lucastan.tmdbclient.data.repository.tv.datasource.TvRemoteDataSource
import retrofit2.Response

class TvRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : TvRemoteDataSource {
    override suspend fun getTvs(): Response<TvList> = tmdbService.getPopularTvs(apiKey)
}
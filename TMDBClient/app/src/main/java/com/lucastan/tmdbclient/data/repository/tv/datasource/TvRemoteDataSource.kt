package com.lucastan.tmdbclient.data.repository.tv.datasource

import com.lucastan.tmdbclient.data.model.tv.TvList
import retrofit2.Response

interface TvRemoteDataSource {
    suspend fun getTvs(): Response<TvList>
}
package com.lucastan.tmdbclient.data.repository.tv.datasource

import com.lucastan.tmdbclient.data.model.tv.Tv

interface TvCacheDataSource {
    suspend fun getTvsFromCache(): List<Tv>
    suspend fun saveTvsToCache(tvs: List<Tv>)
}
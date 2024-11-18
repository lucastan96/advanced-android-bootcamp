package com.lucastan.tmdbclient.data.repository.tv.datasource

import com.lucastan.tmdbclient.data.model.tv.Tv

interface TvLocalDataSource {
    suspend fun getTvsFromDB(): List<Tv>
    suspend fun saveTvsToDB(tvs: List<Tv>)
    suspend fun clearTvsFromDb()
}
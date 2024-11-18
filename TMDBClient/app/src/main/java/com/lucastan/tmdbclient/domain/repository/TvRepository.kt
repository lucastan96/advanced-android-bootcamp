package com.lucastan.tmdbclient.domain.repository

import com.lucastan.tmdbclient.data.model.tv.Tv

interface TvRepository {
    suspend fun getTvs(): List<Tv>?
    suspend fun updateTvs(): List<Tv>?
}
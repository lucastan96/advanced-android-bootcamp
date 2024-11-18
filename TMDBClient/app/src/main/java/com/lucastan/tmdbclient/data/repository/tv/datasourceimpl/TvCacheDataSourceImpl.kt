package com.lucastan.tmdbclient.data.repository.tv.datasourceimpl

import com.lucastan.tmdbclient.data.model.tv.Tv
import com.lucastan.tmdbclient.data.repository.tv.datasource.TvCacheDataSource

class TvCacheDataSourceImpl : TvCacheDataSource {
    private var tvList = ArrayList<Tv>()

    override suspend fun getTvsFromCache(): List<Tv> {
        return tvList
    }

    override suspend fun saveTvsToCache(tvs: List<Tv>) {
        tvList.clear()
        tvList = ArrayList(tvs)
    }
}
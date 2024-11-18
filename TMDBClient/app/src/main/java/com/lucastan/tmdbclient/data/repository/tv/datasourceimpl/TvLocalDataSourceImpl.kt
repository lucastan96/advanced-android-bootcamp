package com.lucastan.tmdbclient.data.repository.tv.datasourceimpl

import com.lucastan.tmdbclient.data.db.TvDao
import com.lucastan.tmdbclient.data.model.tv.Tv
import com.lucastan.tmdbclient.data.repository.tv.datasource.TvLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvLocalDataSourceImpl(private val tvDao: TvDao) : TvLocalDataSource {
    override suspend fun getTvsFromDB(): List<Tv> {
        return tvDao.getTvs()
    }

    override suspend fun saveTvsToDB(tvs: List<Tv>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvDao.saveTvs(tvs)
        }
    }

    override suspend fun clearTvsFromDb() {
        CoroutineScope(Dispatchers.IO).launch {
            tvDao.deleteAllTvs()
        }
    }
}
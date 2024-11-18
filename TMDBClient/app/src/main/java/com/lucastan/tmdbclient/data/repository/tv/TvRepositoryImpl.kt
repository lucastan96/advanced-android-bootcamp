package com.lucastan.tmdbclient.data.repository.tv

import android.util.Log
import com.lucastan.tmdbclient.data.model.tv.Tv
import com.lucastan.tmdbclient.data.repository.tv.datasource.TvCacheDataSource
import com.lucastan.tmdbclient.data.repository.tv.datasource.TvLocalDataSource
import com.lucastan.tmdbclient.data.repository.tv.datasource.TvRemoteDataSource
import com.lucastan.tmdbclient.domain.repository.TvRepository

class TvRepositoryImpl(
    private val tvRemoteDataSource: TvRemoteDataSource,
    private val tvLocalDataSource: TvLocalDataSource,
    private val tvCacheDataSource: TvCacheDataSource
) : TvRepository {
    override suspend fun getTvs(): List<Tv> {
        return getTvsFromCache()
    }

    override suspend fun updateTvs(): List<Tv> {
        val newTvList = getTvsFromAPI()
        tvLocalDataSource.clearTvsFromDb()
        tvLocalDataSource.saveTvsToDB(newTvList)
        tvCacheDataSource.saveTvsToCache(newTvList)

        return newTvList
    }

    private suspend fun getTvsFromAPI(): List<Tv> {
        lateinit var tvList: List<Tv>
        try {
            val response = tvRemoteDataSource.getTvs()
            val body = response.body()
            if (body != null) {
                tvList = body.tvs
            }
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }
        return tvList
    }

    private suspend fun getTvsFromDB(): List<Tv> {
        lateinit var tvList: List<Tv>
        try {
            tvList = tvLocalDataSource.getTvsFromDB()
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        if (tvList.isNotEmpty()) {
            return tvList
        } else {
            tvList = getTvsFromAPI()
            tvLocalDataSource.saveTvsToDB(tvList)
        }

        return tvList
    }

    private suspend fun getTvsFromCache(): List<Tv> {
        lateinit var tvList: List<Tv>
        try {
            tvList = tvCacheDataSource.getTvsFromCache()
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        if (tvList.isNotEmpty()) {
            return tvList
        } else {
            tvList = getTvsFromDB()
            tvCacheDataSource.saveTvsToCache(tvList)
        }

        return tvList
    }
}
package com.lucastan.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucastan.tmdbclient.data.model.tv.Tv

@Dao
interface TvDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvs(tvs: List<Tv>)

    @Query("DELETE FROM popular_tvs")
    suspend fun deleteAllTvs()

    @Query("SELECT * FROM popular_tvs")
    suspend fun getTvs(): List<Tv>
}
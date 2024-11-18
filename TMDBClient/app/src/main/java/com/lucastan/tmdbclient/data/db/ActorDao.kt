package com.lucastan.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucastan.tmdbclient.data.model.actor.Actor

@Dao
interface ActorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveActors(actors: List<Actor>)

    @Query("DELETE FROM popular_actors")
    suspend fun deleteAllActors()

    @Query("SELECT * FROM popular_actors")
    suspend fun getActors(): List<Actor>
}
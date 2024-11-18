package com.lucastan.tmdbclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucastan.tmdbclient.data.model.actor.Actor
import com.lucastan.tmdbclient.data.model.movie.Movie
import com.lucastan.tmdbclient.data.model.tv.Tv

@Database(entities = [Movie::class, Tv::class, Actor::class], version = 1, exportSchema = false)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun tvDao(): TvDao

    abstract fun actorDao(): ActorDao
}
package com.lucastan.tmdbclient.presentation.di.core

import android.app.Application
import androidx.room.Room
import com.lucastan.tmdbclient.data.db.ActorDao
import com.lucastan.tmdbclient.data.db.MovieDao
import com.lucastan.tmdbclient.data.db.TMDBDatabase
import com.lucastan.tmdbclient.data.db.TvDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
//    fun providesMovieDatabase(context: Context): TMDBDatabase {
    fun providesMovieDatabase(app: Application): TMDBDatabase {
        return Room.databaseBuilder(app, TMDBDatabase::class.java, "tmdbclient").build()
    }

    @Singleton
    @Provides
    fun providesMovieDao(tmdbDatabase: TMDBDatabase): MovieDao {
        return tmdbDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun providesTvDao(tmdbDatabase: TMDBDatabase): TvDao {
        return tmdbDatabase.tvDao()
    }

    @Singleton
    @Provides
    fun providesActorDao(tmdbDatabase: TMDBDatabase): ActorDao {
        return tmdbDatabase.actorDao()
    }
}
package com.lucastan.tmdbclient

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.lucastan.tmdbclient.data.db.MovieDao
import com.lucastan.tmdbclient.data.db.TMDBDatabase
import com.lucastan.tmdbclient.data.model.movie.Movie
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TMDBDatabase
    private lateinit var dao: MovieDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TMDBDatabase::class.java
        ).build()
        dao = database.movieDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveAndGetMoviesTest() = runBlocking {
        val moviesToSave = listOf(
            Movie(1, "Title 1", "Overview 1", "Poster Path 1", "Release Date 1"),
            Movie(2, "Title 2", "Overview 2", "Poster Path 2", "Release Date 2"),
            Movie(3, "Title 3", "Overview 3", "Poster Path 3", "Release Date 3")
        )
        dao.saveMovies(moviesToSave)
        val moviesToGet = dao.getMovies()
        assertThat(moviesToGet).isEqualTo(moviesToSave)
    }

    @Test
    fun deleteMoviesTest() = runBlocking {
        val moviesToSave = listOf(
            Movie(1, "Title 1", "Overview 1", "Poster Path 1", "Release Date 1"),
            Movie(2, "Title 2", "Overview 2", "Poster Path 2", "Release Date 2"),
            Movie(3, "Title 3", "Overview 3", "Poster Path 3", "Release Date 3")
        )
        dao.saveMovies(moviesToSave)
        dao.deleteAllMovies()
        val moviesToGet = dao.getMovies()
        assertThat(moviesToGet).isEmpty()
    }
}
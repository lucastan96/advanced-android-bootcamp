package com.lucastan.tmdbclient.data.repository.movie.datasource

import com.lucastan.tmdbclient.data.model.movie.Movie

interface MovieLocalDataSource {
    suspend fun getMoviesFromDB(): List<Movie>
    suspend fun saveMoviesToDB(movies: List<Movie>)
    suspend fun clearMoviesFromDb()
}
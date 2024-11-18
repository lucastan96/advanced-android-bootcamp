package com.lucastan.tmdbclient.data.repository.movie.datasource

import com.lucastan.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies(): Response<MovieList>
}
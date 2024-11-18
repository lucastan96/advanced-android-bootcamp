package com.lucastan.tmdbclient.data.repository.movie.datasourceimpl

import com.lucastan.tmdbclient.data.api.TMDBService
import com.lucastan.tmdbclient.data.model.movie.MovieList
import com.lucastan.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : MovieRemoteDataSource {
    override suspend fun getMovies(): Response<MovieList> = tmdbService.getPopularMovies(apiKey)
}
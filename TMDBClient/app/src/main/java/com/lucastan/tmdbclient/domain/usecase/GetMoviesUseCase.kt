package com.lucastan.tmdbclient.domain.usecase

import com.lucastan.tmdbclient.data.model.movie.Movie
import com.lucastan.tmdbclient.domain.repository.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.getMovies()
}

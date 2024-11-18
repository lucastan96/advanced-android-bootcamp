package com.lucastan.tmdbclient.data.api

import com.lucastan.tmdbclient.data.model.actor.ActorList
import com.lucastan.tmdbclient.data.model.movie.MovieList
import com.lucastan.tmdbclient.data.model.tv.TvList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieList>

    @GET("tv/popular")
    suspend fun getPopularTvs(@Query("api_key") apiKey: String): Response<TvList>

    @GET("person/popular")
    suspend fun getPopularActors(@Query("api_key") apiKey: String): Response<ActorList>
}
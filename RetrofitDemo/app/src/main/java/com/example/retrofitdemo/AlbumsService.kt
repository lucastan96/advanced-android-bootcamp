package com.example.retrofitdemo

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumsService {
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums")
    suspend fun getAlbumsByUserId(@Query("userId") userId: Int): Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAlbumById(@Path("id") albumId: Int): Response<AlbumsItem>

    @POST("/albums")
    suspend fun addAlbum(@Body album: AlbumsItem): Response<AlbumsItem>
}
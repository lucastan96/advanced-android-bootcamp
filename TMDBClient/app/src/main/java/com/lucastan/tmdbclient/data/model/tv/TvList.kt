package com.lucastan.tmdbclient.data.model.tv


import com.google.gson.annotations.SerializedName

data class TvList(
    @SerializedName("results")
    val tvs: List<Tv>
)
package com.lucastan.tmdbclient.data.model.actor


import com.google.gson.annotations.SerializedName

data class ActorList(
    @SerializedName("results")
    val actors: List<Actor>
)
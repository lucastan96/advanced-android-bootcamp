package com.lucastan.tmdbclient.presentation.actor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucastan.tmdbclient.R
import com.lucastan.tmdbclient.data.model.actor.Actor
import com.lucastan.tmdbclient.databinding.ListItemBinding

class ActorAdapter() : RecyclerView.Adapter<ActorViewHolder>() {
    private val actorList = ArrayList<Actor>()

    fun setList(actors: List<Actor>) {
        actorList.clear()
        actorList.addAll(actors)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return ActorViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actorList[position])
    }
}

class ActorViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(actor: Actor) {
        binding.tvTitle.text = actor.name
        binding.tvDescription.text = actor.popularity.toString()

        val posterUrl = "https://image.tmdb.org/t/p/w500" + actor.profilePath
        Glide.with(binding.imgPoster.context)
            .load(posterUrl)
            .into(binding.imgPoster)
    }
}
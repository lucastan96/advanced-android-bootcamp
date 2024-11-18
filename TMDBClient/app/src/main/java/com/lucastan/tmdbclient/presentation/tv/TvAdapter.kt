package com.lucastan.tmdbclient.presentation.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucastan.tmdbclient.R
import com.lucastan.tmdbclient.data.model.tv.Tv
import com.lucastan.tmdbclient.databinding.ListItemBinding

class TvAdapter() : RecyclerView.Adapter<TvViewHolder>() {
    private val tvList = ArrayList<Tv>()

    fun setList(tvs: List<Tv>) {
        tvList.clear()
        tvList.addAll(tvs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return TvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tvList.size
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(tvList[position])
    }
}

class TvViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(tv: Tv) {
        binding.tvTitle.text = tv.name
        binding.tvDescription.text = tv.overview

        val posterUrl = "https://image.tmdb.org/t/p/w500" + tv.posterPath
        Glide.with(binding.imgPoster.context)
            .load(posterUrl)
            .into(binding.imgPoster)
    }
}
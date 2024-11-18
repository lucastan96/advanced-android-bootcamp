package com.lucastan.tmdbclient.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lucastan.tmdbclient.R
import com.lucastan.tmdbclient.databinding.ActivityHomeBinding
import com.lucastan.tmdbclient.presentation.actor.ActorActivity
import com.lucastan.tmdbclient.presentation.movie.MovieActivity
import com.lucastan.tmdbclient.presentation.tv.TvActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.btnMovies.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
        }

        binding.btnTvs.setOnClickListener {
            val intent = Intent(this, TvActivity::class.java)
            startActivity(intent)
        }

        binding.btnActors.setOnClickListener {
            val intent = Intent(this, ActorActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.lucastan.tmdbclient.presentation.movie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.lucastan.tmdbclient.R
import com.lucastan.tmdbclient.databinding.ActivityMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieActivity : AppCompatActivity(), OnRefreshListener {
    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

//        (application as Injector).createMovieSubcomponent().inject(this)
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        initAppBar()
        initRecyclerView()
    }

    private fun initAppBar() {
        binding.apply {
            toolbar.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter()
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener(this)

        displayPopularMovies()
    }

    private fun displayPopularMovies() {
        binding.swipeRefreshLayout.isRefreshing = true

        val responseLiveData = movieViewModel.getMovies()
        responseLiveData.observe(this) {
            binding.swipeRefreshLayout.isRefreshing = false

            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updatePopularMovies() {
        binding.swipeRefreshLayout.isRefreshing = true

        val responseLiveData = movieViewModel.updateMovies()
        responseLiveData.observe(this) {
            binding.swipeRefreshLayout.isRefreshing = false

            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Failed to update data", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onRefresh() {
        updatePopularMovies()
    }
}
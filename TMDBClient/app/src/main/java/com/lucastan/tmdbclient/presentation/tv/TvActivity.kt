package com.lucastan.tmdbclient.presentation.tv

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.lucastan.tmdbclient.R
import com.lucastan.tmdbclient.databinding.ActivityTvBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TvActivity : AppCompatActivity(), OnRefreshListener {
    @Inject
    lateinit var factory: TvViewModelFactory
    private lateinit var tvViewModel: TvViewModel
    private lateinit var binding: ActivityTvBinding
    private lateinit var adapter: TvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv)

//        (application as Injector).createTvSubcomponent().inject(this)
        tvViewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

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
        adapter = TvAdapter()
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener(this)

        displayPopularTvShows()
    }

    private fun displayPopularTvShows() {
        binding.swipeRefreshLayout.isRefreshing = true

        val responseLiveData = tvViewModel.getTvs()
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

    private fun updatePopularTvShows() {
        binding.swipeRefreshLayout.isRefreshing = true

        val responseLiveData = tvViewModel.updateTvs()
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
        updatePopularTvShows()
    }
}
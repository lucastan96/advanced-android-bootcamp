package com.lucastan.tmdbclient.presentation.actor

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.lucastan.tmdbclient.R
import com.lucastan.tmdbclient.databinding.ActivityActorBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ActorActivity : AppCompatActivity(), OnRefreshListener {
    @Inject
    lateinit var factory: ActorViewModelFactory
    private lateinit var actorViewModel: ActorViewModel
    private lateinit var binding: ActivityActorBinding
    private lateinit var adapter: ActorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_actor)

//        (application as Injector).createActorSubcomponent().inject(this)
        actorViewModel = ViewModelProvider(this, factory)[ActorViewModel::class.java]

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
        adapter = ActorAdapter()
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener(this)

        displayPopularActors()
    }

    private fun displayPopularActors() {
        binding.swipeRefreshLayout.isRefreshing = true

        val responseLiveData = actorViewModel.getActors()
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

    private fun updatePopularActors() {
        binding.swipeRefreshLayout.isRefreshing = true

        val responseLiveData = actorViewModel.updateActors()
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
        updatePopularActors()
    }
}
package com.example.viewmodeldemo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeldemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
//        viewModel.count.observe(this) {
//            binding.countText.text = it.toString()
//        }

//        binding.countText.text = viewModel.getCurrentCount().toString()
//        binding.button.setOnClickListener {
//            binding.countText.text = viewModel.getUpdatedCount().toString()
//        }
    }
}
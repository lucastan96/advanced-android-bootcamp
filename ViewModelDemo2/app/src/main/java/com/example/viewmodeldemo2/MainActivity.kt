package com.example.viewmodeldemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeldemo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private lateinit var viewModelFactory: MainActivityViewModelFactory // Older method
//    private lateinit var viewModel: MainActivityViewModel // Older method
    private val viewModel: MainActivityViewModel by viewModels { MainActivityViewModelFactory(125) } // Newer method

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Older method
//        viewModelFactory = MainActivityViewModelFactory(125)
//        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]

        viewModel.totalData.observe(this) {
            binding.resultTextView.text = it.toString()
        }

//        binding.resultTextView.text = viewModel.getTotal().toString()

        binding.insertButton.setOnClickListener {
            viewModel.setTotal(binding.inputEditText.text.toString().toInt())
//            binding.resultTextView.text = viewModel.getTotal().toString()
        }
    }
}
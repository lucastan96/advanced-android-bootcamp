package com.example.circlecalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.circlecalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CalcViewModel
    private lateinit var factory: CalcViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        factory = CalcViewModelFactory(MyCalc())
        viewModel = ViewModelProvider(this, factory)[CalcViewModel::class.java]

        binding.calcViewModel = viewModel
        binding.lifecycleOwner = this
    }
}
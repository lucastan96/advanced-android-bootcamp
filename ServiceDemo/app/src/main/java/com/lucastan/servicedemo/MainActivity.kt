package com.lucastan.servicedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lucastan.servicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceIntent = Intent(this, BackgroundService::class.java)

        binding.btnStart.setOnClickListener {
            Log.i(BackgroundService.TAG, "Starting service")
            startService(serviceIntent)
        }

        binding.btnStop.setOnClickListener {
            Log.i(BackgroundService.TAG, "Stopping service")
            stopService(serviceIntent)
        }
    }
}
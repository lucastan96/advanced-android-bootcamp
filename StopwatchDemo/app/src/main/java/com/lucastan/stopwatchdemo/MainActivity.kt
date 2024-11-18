package com.lucastan.stopwatchdemo

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucastan.stopwatchdemo.databinding.ActivityMainBinding
import java.util.Locale
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var serviceIntent: Intent

    private var time = 0.0
    private var isStarted = false

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startOrStop()
        }
        binding.btnReset.setOnClickListener {
            resetTimer()
        }

        serviceIntent = Intent(applicationContext, StopwatchService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(
                updateTimer,
                IntentFilter(StopwatchService.UPDATED_TIME),
                RECEIVER_NOT_EXPORTED
            )
        } else {
            registerReceiver(
                updateTimer, IntentFilter(StopwatchService.UPDATED_TIME)
            )
        }
    }

    private fun startOrStop() {
        if (isStarted) {
            stopTimer()
        } else {
            startTimer()
        }
    }

    private fun startTimer() {
        serviceIntent.putExtra(StopwatchService.CURRENT_TIME, time)
        startService(serviceIntent)

        binding.btnStart.text = "Stop"
        isStarted = true
    }

    private fun stopTimer() {
        stopService(serviceIntent)

        binding.btnStart.text = "Start"
        isStarted = false
    }

    private fun resetTimer() {
        stopTimer()

        time = 0.0
        binding.tvTime.text = getFormattedTime(time)
    }

    private val updateTimer: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(StopwatchService.CURRENT_TIME, 0.0)
            binding.tvTime.text = getFormattedTime(time)
        }
    }

    private fun getFormattedTime(time: Double): String {
        val timeInt = time.roundToInt()
        val hours = timeInt % 86400 / 3600
        val minutes = timeInt % 86400 % 3600 / 60
        val seconds = timeInt % 86400 % 3600 % 60

        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
    }
}
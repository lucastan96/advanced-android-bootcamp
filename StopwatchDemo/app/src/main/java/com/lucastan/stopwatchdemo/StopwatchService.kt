package com.lucastan.stopwatchdemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.Timer
import java.util.TimerTask

class StopwatchService : Service() {
    private val timer = Timer()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val time = intent?.getDoubleExtra(CURRENT_TIME, 0.0)
        timer.schedule(StopwatchTimerTask(time!!), 0, 1000)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    companion object {
        const val CURRENT_TIME = "Current Time"
        const val UPDATED_TIME = "Update Time"
    }

    private inner class StopwatchTimerTask(private var time: Double) : TimerTask() {
        override fun run() {
            time++

            val intent = Intent(UPDATED_TIME)
            intent.setPackage(packageName)
            intent.putExtra(CURRENT_TIME, time)
            sendBroadcast(intent)
        }
    }
}
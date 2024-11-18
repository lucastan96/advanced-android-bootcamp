package com.lucastan.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class DownloadWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        try {
            for (i in 0..3000) {
                Log.i("TAG", "Downloading $i")
                Thread.sleep(10)
            }

            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}
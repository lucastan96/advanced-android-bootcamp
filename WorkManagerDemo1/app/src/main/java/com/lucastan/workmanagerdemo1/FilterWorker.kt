package com.lucastan.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class FilterWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        try {
            for (i in 0..300) {
                Log.i("TAG", "Filtering $i")
                Thread.sleep(10)
            }

            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}
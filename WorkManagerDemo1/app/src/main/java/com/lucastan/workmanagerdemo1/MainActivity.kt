package com.lucastan.workmanagerdemo1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY_COUNT = "key_count"
    }

    private lateinit var statusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.start_button)
        startButton.setOnClickListener {
//            setOneTimeWorkRequest()
            setPeriodicWorkRequest()
        }

        statusTextView = findViewById(R.id.status_text_view)
    }

    private fun setOneTimeWorkRequest() {
        val workManager = WorkManager.getInstance(applicationContext)

        val inputData = Data.Builder()
            .putInt(KEY_COUNT, 126)
            .build()

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        val filterRequest = OneTimeWorkRequest.Builder(FilterWorker::class.java).build()
        val compressRequest = OneTimeWorkRequest.Builder(CompressWorker::class.java).build()
        val downloadRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java).build()

//        workManager.enqueue(uploadRequest)
        workManager
            .beginWith(filterRequest)
            .then(compressRequest)
            .then(uploadRequest)
            .enqueue()

        val parallelWorks = mutableListOf<OneTimeWorkRequest>()
        parallelWorks.add(downloadRequest)
        parallelWorks.add(filterRequest)

        workManager
            .beginWith(parallelWorks)
            .then(compressRequest)
            .then(uploadRequest)
            .enqueue()

        workManager.getWorkInfoByIdLiveData(uploadRequest.id).observe(this) {
            if (it.state == WorkInfo.State.ENQUEUED) {
                statusTextView.text = "Waiting..."
            } else if (it.state == WorkInfo.State.RUNNING) {
                statusTextView.text = "Uploading..."
            } else if (it.state == WorkInfo.State.FAILED) {
                statusTextView.text = "Failed"
            } else if (it.state == WorkInfo.State.SUCCEEDED) {
                statusTextView.text = "Completed"

                val data = it.outputData
                val message = data.getString(UploadWorker.KEY_WORKER)
                Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setPeriodicWorkRequest() {
        val periodicWorkRequest = PeriodicWorkRequest.Builder(DownloadWorker::class.java, 16, TimeUnit.MINUTES).build()

        WorkManager
            .getInstance(applicationContext)
            .enqueue(periodicWorkRequest)
    }
}
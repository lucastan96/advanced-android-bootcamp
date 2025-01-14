package com.example.coroutinesdemo1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var btnDownloadUserData: Button
    private lateinit var btnCount: Button
    private lateinit var tvUserMessage: TextView
    private lateinit var tvCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDownloadUserData = findViewById(R.id.btnDownloadUserData)
        btnCount = findViewById(R.id.btnCount)
        tvUserMessage = findViewById(R.id.tvUserMessage)
        tvCount = findViewById(R.id.tvCount)

        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }

        btnDownloadUserData.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                downloadUserData()
//            }

            CoroutineScope(Dispatchers.Main).launch {
//                tvUserMessage.text = UserDataManager().getTotalUserCount().toString()
                tvUserMessage.text = UserDataManager2().getTotalUserCount().toString()
            }
        }
    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            withContext(Dispatchers.Main) {
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }
        }
    }
}
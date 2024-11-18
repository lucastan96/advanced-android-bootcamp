package com.example.asyncawaitdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        CoroutineScope(IO).launch {
//            Log.d("AsyncAwaitDemo", "onCreate: Calculating stock")
//            val stock1 = async { getStock1() }
//            val stock2 = async { getStock2() }
//            val total = stock1.await() + stock2.await()
//            Log.d("AsyncAwaitDemo", "onCreate: Total stock is $total")
//        }

        CoroutineScope(Main).launch {
            Log.d("AsyncAwaitDemo", "onCreate: Calculating stock")
            val stock1 = async(IO) { getStock1() }
            val stock2 = async(IO) { getStock2() }
            val total = stock1.await() + stock2.await()
            Toast.makeText(applicationContext, "Total stock is $total", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun getStock1(): Int {
        delay(10000)
        Log.d("AsyncAwaitDemo", "getStock1: Done")
        return 55000
    }

    private suspend fun getStock2(): Int {
        delay(7000)
        Log.d("AsyncAwaitDemo", "getStock2: Done")
        return 42000
    }
}
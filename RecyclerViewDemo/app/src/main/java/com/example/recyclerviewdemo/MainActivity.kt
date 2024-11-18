package com.example.recyclerviewdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val fruitList = listOf(
        Fruit("Mango", "Indonesia"),
        Fruit("Apple", "United Kingdom"),
        Fruit("Durian", "Malaysia"),
        Fruit("Orange", "Australia"),
        Fruit("Grape", "Australia"),
        Fruit("Papaya", "Malaysia"),
        Fruit("Strawberry", "New Zealand"),
        Fruit("Peach", "China")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = RecyclerViewAdapter(fruitList) { selectedItem: Fruit ->
            listItemClicked(selectedItem)
        }
    }

    private fun listItemClicked(fruit: Fruit) {
        Toast.makeText(
            this,
            "The ${fruit.name} is from ${fruit.supplier}.",
            Toast.LENGTH_SHORT
        ).show()
    }
}
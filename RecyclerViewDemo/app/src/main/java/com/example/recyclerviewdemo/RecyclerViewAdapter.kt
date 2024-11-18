package com.example.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
    private val fruitList: List<Fruit>,
    private val clickListener: (Fruit) -> Unit
) :
    RecyclerView.Adapter<DefaultViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return DefaultViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        holder.bind(fruitList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }
}

class DefaultViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(fruit: Fruit, clickListener: (Fruit) -> Unit) {
        val title: TextView = view.findViewById(R.id.title)
        title.text = fruit.name

        val description: TextView = view.findViewById(R.id.description)
        description.text = fruit.supplier

        view.setOnClickListener {
            clickListener(fruit)
        }
    }
}
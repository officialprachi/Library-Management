package com.example.librarymanagementsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView

import androidx.recyclerview.widget.RecyclerView


class booksAdapter(private val booksData: List<String>) : RecyclerView.Adapter<booksAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.bookslayout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = booksData[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return booksData.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: CardView

        init {
            cardView = itemView.findViewById<CardView>(R.id.cardview1)
        }

        fun bind(item: String?) {
            cardView.id
        }
    }
}


package com.example.librarymanagementsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

import androidx.recyclerview.widget.RecyclerView


class booksAdapter(private val booksData: List<bookscardview>) : RecyclerView.Adapter<booksAdapter.MyViewHolder>()  {
    inner  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardView: CardView = itemView.findViewById(R.id.cardview1)
        val bookImage: ImageView = itemView.findViewById(R.id.book1)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.bookslayout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = booksData[position]
        holder.bookImage.setImageResource(item.imageResourceId)
    }

    override fun getItemCount(): Int {
        return booksData.size
    }


}
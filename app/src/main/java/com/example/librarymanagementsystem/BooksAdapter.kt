package com.example.librarymanagementsystem

import Book
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BooksAdapter(private var booksData: List<Book>) : RecyclerView.Adapter<BooksAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookTitle: TextView = itemView.findViewById(R.id.book_title1)
        val bookImage: ImageView = itemView.findViewById(R.id.book1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.bookslayout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = booksData[position] // Assuming 'booksData' is your data source (list of books)

        holder.bookTitle.text = book.title// Always set the title

        // Check if imageResourceId is valid:
        holder.bookImage.setImageResource(book.imageResourceId)
    }

    override fun getItemCount(): Int {
        return booksData.size
    }

    fun updateData(newData: List<Book>) {
        booksData = newData
        notifyDataSetChanged()
    }

}
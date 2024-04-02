package com.example.librarymanagementsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val booksData: List<bookscardview>) : RecyclerView.Adapter<BookAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookTitle: TextView = itemView.findViewById(R.id.book_title1)
        val cardView: CardView = itemView.findViewById(R.id.cardview1)
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
        if (book.imageResourceId != 0) { // Assuming 0 indicates no image resource or placeholder
            holder.bookImage.setImageResource(book.imageResourceId)
        } else {
            // Set a placeholder image (optional):
            holder.bookImage.setImageResource(R.drawable.book2)
        }
    }

    override fun getItemCount(): Int {
        return booksData.size
    }
}

package com.example.librarymanagementsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SavedBooksAdaptor(private val savedBooks: List<savedataclass>)
    : RecyclerView.Adapter<SavedBooksAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_savedbookfragment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedBooksAdaptor.ViewHolder, position: Int) {
        val book = savedBooks[position]
        holder.bind(book)
    }


    override fun getItemCount(): Int {
        return savedBooks.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookImage: ImageView = itemView.findViewById(R.id.book1)
        private val bookTitle: TextView = itemView.findViewById(R.id.book_title1)

        fun bind(book: savedataclass) {
            // Load book data into views
            bookTitle.text = book.title
            // Load book cover image using a library like Glide or Picasso
            Glide.with(itemView.context).load(book.imageResourceId).into(bookImage)        }
    }
}




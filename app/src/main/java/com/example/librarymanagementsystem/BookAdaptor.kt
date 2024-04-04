package com.example.librarymanagementsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView


class BooksAdapter(private val imageResourceIds: List<String>) : RecyclerView.Adapter<BooksAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookImage: ImageView = itemView.findViewById(R.id.book1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.bookslayout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageResourceId = imageResourceIds[position]
        holder.bookImage.setImageResource(imageResourceId)
    }

    override fun getItemCount(): Int {
        return imageResourceIds.size
    }
}

private fun ImageView.setImageResource(imageResourceId: String) {

}

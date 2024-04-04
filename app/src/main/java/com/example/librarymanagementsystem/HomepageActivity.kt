package com.example.librarymanagementsystem

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomepageActivity : AppCompatActivity() {
        private var recyclerView: RecyclerView? = null
        private var adapter: booksAdapter? = null

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_homepage)

                window.statusBarColor= Color.TRANSPARENT

                recyclerView = findViewById(R.id.recycler_view)
                recyclerView?.layoutManager = LinearLayoutManager(this)
                val data = generateData() // Your data source
                adapter = booksAdapter(data)
                recyclerView?.adapter = adapter

                // Inflate the layout containing the save button
                val inflater = LayoutInflater.from(this)
                val rootView = inflater.inflate(R.layout.bookslayout, null, false)

// Find the save button within the root view of the other layout
                val saveButton = rootView.findViewById<ImageButton>(R.id.save_Btn)
                saveButton.setOnClickListener { onSaveButtonClick() }
        }

        private fun generateData(): List<String> {
                val data: MutableList<String> = ArrayList()
                // Add your data here
                for (i in 1..50) {
                        data.add("Item $i")
                }
                return data
        }

        fun onSaveButtonClick() {
                Toast.makeText(this, "Image saved!", Toast.LENGTH_SHORT).show()
        }
}



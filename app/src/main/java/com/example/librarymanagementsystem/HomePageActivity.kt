package com.example.librarymanagementsystem


import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomePageActivity : AppCompatActivity() {
        private lateinit var recyclerView: RecyclerView
        private lateinit var adapter: BooksAdapter
        private lateinit var save_btn:ImageButton
        private lateinit var savebtn1:ImageButton
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_homepage)

                recyclerView = findViewById(R.id.recycler_view)
                recyclerView.layoutManager = LinearLayoutManager(this)

                val data = generateData() // Your data source
                adapter = BooksAdapter(data)
                recyclerView.adapter = adapter

                // Find the save button within the activity_homepage layout
               // save_btn= findViewById(R.id.save_btn)
                //save_btn?.setOnClickListener { onSaveButtonClick() }
        }

        private fun generateData(): List<String> {
                val data: MutableList<String> = ArrayList()
                // Add your data here
                for (i in 1..50) {
                        data.add("Item $i")
                }
                return data
        }

        private fun onSaveButtonClick() {
                Toast.makeText(this, "Image saved!", Toast.LENGTH_SHORT).show()
        }
}

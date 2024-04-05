package com.example.librarymanagementsystem


import BooksAdapter
import Savedbookfragment
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity(), BooksAdapter.OnSaveClickListener {

    private var recyclerView: RecyclerView? = null
    private lateinit var adapter: BooksAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var data: MutableList<bookscardview>
    private val savedBooks: MutableList<bookscardview> = mutableListOf()
    private lateinit var searchView: SearchView
    private lateinit var database: FirebaseFirestore

    private fun searchBook(searchTerm: String) {
        val booksRef = database.collection("books")
        val query: Query = booksRef.whereEqualTo("title", searchTerm)

        query.get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    for (document in querySnapshot.documents) {
                        val book = document.toObject(bookscardview::class.java)
                        if (book != null) {
                            // Handle found book
                            Toast.makeText(this@MainActivity, "Book found: ${book.title}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    // Book not found
                    Toast.makeText(this@MainActivity, "Book not found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                // Handle failures
                Toast.makeText(this@MainActivity, "Failed to search for book: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSaveClick(position: Int) {
        val book = data[position]
        savedBooks.add(book)
        // Optionally, you can show a toast or perform any other action to indicate the book was saved
        Toast.makeText(
            this@MainActivity,
            "Book saved",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun bookscardview(title: String, imageResourceId: Int): bookscardview {
        TODO("Not yet implemented")
        val title: String
        val imageResourceId: Int
        val available: Boolean
    }

    private fun generateData(): MutableList<bookscardview> {
        val data: MutableList<bookscardview> = ArrayList()
        // Add book objects with unique titles and image resource IDs
        if (!data.contains(bookscardview("My name is not Devdas", R.drawable.book1))) {
            data.add(bookscardview("My name is not Devdas", R.drawable.book1))
        }
        if (!data.contains(bookscardview("Solar Bones", R.drawable.book2))) {
            data.add(bookscardview("Solar Bones", R.drawable.book2))
        }
        if (!data.contains(bookscardview("Atomic Habits", R.drawable.book3))) {
            data.add(bookscardview("Atomic Habits", R.drawable.book3))
        }
        if (!data.contains(bookscardview("Atomic Habits", R.drawable.book4))) {
            data.add(bookscardview("Atomic Habits", R.drawable.book4))
        }

        // Add more books as needed
        return data
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)  // Use the ID of the container
            .commit()
    }
}












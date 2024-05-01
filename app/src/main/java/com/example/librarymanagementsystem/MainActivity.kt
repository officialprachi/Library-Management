package com.example.librarymanagementsystem

import Book
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: BooksAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var booksData: List<Book> // Your static list of books
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor= Color.TRANSPARENT
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Initialize your static list of books
        booksData = getStaticBooksData()


        adapter = BooksAdapter(emptyList())
        recyclerView.adapter = adapter
        // Set up SearchView
        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search query submission (optional)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Filter books based on the search query
                val filteredBooks = filterBooks(newText.orEmpty())
                adapter.updateData(filteredBooks)
                return true
            }
        })

        // Load books from Room database asynchronously
        // Launch a coroutine on the IO dispatcher for database operations
        CoroutineScope(Dispatchers.IO).launch {
            // Retrieve books from the database
            val books = MyApp1.database.bookDao().getAllBooks()

            // Switch to the Main dispatcher to update UI with retrieved data
            withContext(Dispatchers.Main) {
                adapter.updateData(books)
            }

            // Insert a new book into the database
            val newBook = Book(title = "My New Book", imageResourceId = R.drawable.book1)
            MyApp1.database.bookDao().insertBook(newBook)

            // Retrieve updated books after insertion
            val updatedBooks = MyApp1.database.bookDao().getAllBooks()

            // Update UI with updated books on the Main dispatcher
            withContext(Dispatchers.Main) {
                adapter.updateData(updatedBooks)
            }
        }



        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigationView)

        // Initialize Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set up toggle button for opening and closing the navigation drawer
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Handle item clicks in the navigation drawer
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add_book_button -> {
                    // Handle inbox item click
                    true
                    // Navigate to AddBookFragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AddBookFragment())
                        .addToBackStack(null)
                        .commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.saved_book -> {
                    // Handle outbox item click
                    true
                }

                R.id.issuebook -> {
                    // Handle favourites item click
                    true
                }
                R.id.log_out -> {
                    // Handle favourites item click
                    true
                    // Handle logout item click
                    FirebaseAuth.getInstance().signOut() // Sign out the user from Firebase Auth

                    // Navigate to your login activity or perform any post-logout actions
                    // For example, navigate to LoginActivity
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)

                    // Close the drawer
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                // Add more cases for other menu items if needed
                else -> false
            }
        }
    }

    private fun navigateToAddBookFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AddBookFragment())
            .addToBackStack(null)
            .commit()
    }
    private fun getStaticBooksData(): List<Book> {
        // Return your static list of books
        return listOf(
            Book("My name is not Devdas", R.drawable.book1),
            Book("Solar Bones", R.drawable.book2),
            Book("Arduino Programming", R.drawable.book3),
            Book("Data Structure", R.drawable.book4),
            Book("Python", R.drawable.book5),
            Book("C++", R.drawable.book6)
            // Add more books as needed
        )
    }

    private fun filterBooks(query: String): List<Book> {
        // Perform case-insensitive search/filter on book titles
        return booksData.filter { book ->
            book.title.contains(query, ignoreCase = true)
        }
    }

   /* private fun generateData(): MutableList<bookscardview> {
        val data: MutableList<bookscardview> = ArrayList()
        // Add book objects with unique titles and image resource IDs
        if (!data.contains(bookscardview("My name is not Devdas", R.drawable.book1))) {
            data.add(bookscardview("My name is not Devdas", R.drawable.book1))
        }
        if (!data.contains(bookscardview("Solar Bones", R.drawable.book2))) {
            data.add(bookscardview("Solar Bones", R.drawable.book2))
        }
        if (!data.contains(bookscardview("Arduino Programming", R.dra   wable.book3))) {
            data.add(bookscardview("Arduino Programming", R.drawable.book3))
        }
        if (!data.contains(bookscardview("Data Structure", R.drawable.book4))) {
            data.add(bookscardview("Data Structure", R.drawable.book4))
        }
        if (!data.contains(bookscardview("Python", R.drawable.book5))) {
            data.add(bookscardview("Python", R.drawable.book5))
        }
        if (!data.contains(bookscardview("C++ ", R.drawable.book6))) {
            data.add(bookscardview("C++", R.drawable.book6))
        }
        // Add more books as needed
        return data
    }*/

}
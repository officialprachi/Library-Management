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
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.bookscardview
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class Homepage : AppCompatActivity(), BooksAdapter.OnSaveClickListener {

    private var recyclerView: RecyclerView? = null
    private lateinit var adapter: BooksAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var data: MutableList<bookscardview>
    private val savedBooks: MutableList<bookscardview> = mutableListOf()
    private lateinit var searchView: SearchView
    private lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        data = generateData()
        adapter = BooksAdapter(data,this)
        recyclerView?.adapter = adapter

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
                R.id.inbox_item -> {
                    // Handle inbox item click
                    true
                }

                R.id.outbox_item -> {
                    // Handle outbox item click
                    replaceFragment(Savedbookfragment())
                    true
                }

                R.id.favourites_item -> {
                    // Handle favourites item click
                    true
                }
                // Add more cases for other menu items if needed
                else -> false
            }
        }

        searchView = findViewById(R.id.search)
        database = FirebaseFirestore.getInstance()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    searchBook(newText)
                }
                return false
            }
        })
    }

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
                            Toast.makeText(this@Homepage, "Book found: ${book.title}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    // Book not found
                    Toast.makeText(this@Homepage, "Book not found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                // Handle failures
                Toast.makeText(this@Homepage, "Failed to search for book: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSaveClick(position: Int) {
        val book = data[position]
        savedBooks.add(book)
        // Optionally, you can show a toast or perform any other action to indicate the book was saved
        Toast.makeText(
            this@Homepage,
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


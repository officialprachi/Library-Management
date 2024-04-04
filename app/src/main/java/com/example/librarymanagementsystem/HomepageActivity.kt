package com.example.librarymanagementsystem

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView


class HomepageActivity : AppCompatActivity() {

        private var recyclerView: RecyclerView? = null
        private lateinit var adapter: BookAdapter
        private lateinit var drawerLayout: DrawerLayout
        private lateinit var navigationView: NavigationView

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_homepage)

                // Initialize RecyclerView
                recyclerView = findViewById(R.id.recycler_view)
                recyclerView?.layoutManager = LinearLayoutManager(this)
                val data = generateData()
                adapter = BookAdapter(data)
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
        }

        private fun generateData(): MutableList<bookscardview> {
                val data: MutableList<bookscardview> = ArrayList()
                // Add book objects with image resource IDs and titles
                data.add(bookscardview("My Name is Not Devdas", R.drawable.book1))
                data.add(bookscardview("Solar Bones", R.drawable.book2))
                data.add(bookscardview("Data Structure", R.drawable.book3))
                data.add(bookscardview("Arduino Programming", R.drawable.book4))
                data.add(bookscardview("Python", R.drawable.book5))
                data.add(bookscardview("C++ ", R.drawable.book6))

                // Add more books as needed
                return data
        }
}




package com.example.librarymanagementsystem

import BookDatabase
import android.app.Application
import androidx.room.Room

class MyApp1 : Application() {

    companion object {
        lateinit var database: BookDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        try {
            // Initialize Room database
            database = Room.databaseBuilder(
                applicationContext,
                BookDatabase::class.java,
                "books_db"
            ).build()

            // Optional: Add a callback to log database creation success
            database.openHelper.readableDatabase // Forces database initialization
            println("Room database initialized successfully.")

        } catch (e: Exception) {
            println("Error initializing Room database: ${e.message}")
            e.printStackTrace()
        }
    }
}

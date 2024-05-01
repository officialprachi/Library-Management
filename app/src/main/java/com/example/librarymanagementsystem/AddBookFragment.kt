package com.example.librarymanagementsystem

import Book
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddBookFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_book, container, false)
        // Setup UI components and handle button click to add a new book
        val addButton = view.findViewById<Button>(R.id.add_book_button)
        addButton.setOnClickListener {
            // Get book details from EditTexts
            val title = view.findViewById<EditText>(R.id.edit_text_title).text.toString()
            val imageResourceId = R.drawable.ic_launcher_foreground // Set default image or use user input

            // Insert new book into database
            val newBook = Book(title, imageResourceId)
            CoroutineScope(Dispatchers.IO).launch {
                MyApp1.database.bookDao().insertBook(newBook)
            }

            // Optionally, you can update RecyclerView with the new book
            // (adapter.updateData() can be called here)

            // Close the fragment
            parentFragmentManager.popBackStack()
        }
        return view
    }
}

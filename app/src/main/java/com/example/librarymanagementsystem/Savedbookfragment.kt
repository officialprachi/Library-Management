import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.SavedBooksAdaptor
import com.example.librarymanagementsystem.savedataclass

class Savedbookfragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SavedBooksAdaptor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_savedbookfragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_saved_books)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = SavedBooksAdaptor(getSavedBooks()) // Implement getSavedBooks() to retrieve saved books
        recyclerView.adapter = adapter
        return view
    }

    // Function to retrieve saved books from your data source
    private fun getSavedBooks(): List<savedataclass> {
        // Implement this method to retrieve saved books from your data source
        val savedBooks = mutableListOf<savedataclass>()

        // Example: Add dummy saved books for demonstration
        savedBooks.add(savedataclass("Book Title 1", "book1" ))
        savedBooks.add(savedataclass("Book Title 2", "book2" ))
        savedBooks.add(savedataclass("Book Title 3", "book3"))

        return savedBooks
    }
}

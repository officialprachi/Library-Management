import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.bookscardview
import com.google.firebase.firestore.FirebaseFirestore

class BooksAdapter(
    private val books: MutableList<bookscardview>,
    private val onSaveClickListener: OnSaveClickListener
) : RecyclerView.Adapter<BooksAdapter.MyViewHolder>() {

    interface OnSaveClickListener {
        fun onSaveClick(position: Int)
        abstract fun bookscardview(title: String, imageResourceId: Int): bookscardview
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookTitle: TextView = itemView.findViewById(R.id.book_title1)
        private val saveButton: ImageButton = itemView.findViewById(R.id.save_book1)
        private val issueButton: Button = itemView.findViewById(R.id.issue_book1)
        private val bookImage: ImageView = itemView.findViewById(R.id.book1)

        init {
            issueButton.setOnClickListener {
                val book = books[adapterPosition]
                issueBook(book.title)
            }

            saveButton.setOnClickListener {
                onSaveClickListener.onSaveClick(adapterPosition)
            }
        }

        fun bind(book: bookscardview) {
            bookTitle.text = book.title
            bookImage.setImageResource(book.imageResourceId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.bookslayout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    private fun issueBook(title: String) {
        val db = FirebaseFirestore.getInstance()
        val booksRef = db.collection("books")

        booksRef.whereEqualTo("title", title).get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    for (document in documents) {
                        val book = document.toObject(bookscardview::class.java)
                        if (book.available) {
                            // Update book status to issued
                            booksRef.document(document.id)
                                .update("available", false, "issuedAt", System.currentTimeMillis())
                                .addOnSuccessListener {
                                    println("Book issued successfully.")
                                }
                                .addOnFailureListener { e ->
                                    println("Error issuing book: $e")
                                }
                            return@addOnSuccessListener
                        }
                    }
                    println("The book is not available for issuance.")
                } else {
                    println("No such book available.")
                }
            }
            .addOnFailureListener { e ->
                println("Error issuing book: $e")
            }
    }
}

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<Book>

    @Insert
    suspend fun insertBook(book: Book)


}

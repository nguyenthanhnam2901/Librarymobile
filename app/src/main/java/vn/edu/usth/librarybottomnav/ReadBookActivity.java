package vn.edu.usth.librarybottomnav;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReadBookActivity extends AppCompatActivity {

    private TextView titleTextView, authorTextView, categoryTextView, contentTextView;
    private int bookId;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book); // Use your own layout

        titleTextView = findViewById(R.id.titleTextView);
        authorTextView = findViewById(R.id.authorTextView);
        categoryTextView = findViewById(R.id.categoryTextView);
        contentTextView = findViewById(R.id.contentTextView);

        // Get the bookId from the intent
        Intent intent = getIntent();
        bookId = intent.getIntExtra("TB_book_id", -1);

        if (bookId != -1) {
            dbHelper = new MyDatabaseHelper(this);
            Book book = dbHelper.getBookById(bookId);

            if (book != null) {
                // Set the book data to the views
                titleTextView.setText(book.getTitle());
                authorTextView.setText(book.getAuthor());
                categoryTextView.setText(book.getCategory());
                contentTextView.setText(book.getContent());
            } else {
                Toast.makeText(this, "Book not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid Book ID", Toast.LENGTH_SHORT).show();
        }
    }
}

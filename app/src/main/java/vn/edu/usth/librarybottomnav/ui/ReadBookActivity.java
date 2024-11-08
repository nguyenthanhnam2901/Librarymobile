package vn.edu.usth.librarybottomnav.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import vn.edu.usth.librarybottomnav.MyDatabaseHelper;
import vn.edu.usth.librarybottomnav.R;

public class ReadBookActivity extends AppCompatActivity {

    private TextView titleTextView, authorTextView, categoryTextView, contentTextView;
    private MyDatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        // Initialize the views
        titleTextView = findViewById(R.id.textViewTitle);
        authorTextView = findViewById(R.id.textViewAuthor);
        categoryTextView = findViewById(R.id.textViewCategory);
        contentTextView = findViewById(R.id.textViewContent);

        // Initialize database helper
        dbHelper = new MyDatabaseHelper(this);

        // Get the book ID passed from the previous activity
        int bookId = getIntent().getIntExtra("book_id", -1);

        // Check if the book ID is valid
        if (bookId != -1) {
            loadBookDetails(bookId);
        } else {
            Toast.makeText(this, "Invalid Book ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadBookDetails(int bookId) {
        Cursor cursor = dbHelper.getBookDetailsById(bookId);  // Query database with bookId
        if (cursor != null && cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TB_book_title));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TB_book_content));
            String author = dbHelper.getAuthorName(cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TB_book_author_id)));
            String category = dbHelper.getCategoryName(cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TB_book_category_id)));

            titleTextView.setText(title);
            authorTextView.setText(author);
            categoryTextView.setText(category);
            contentTextView.setText(content);

            cursor.close();
        } else {
            Toast.makeText(this, "Book not found", Toast.LENGTH_SHORT).show();
        }
    }




}

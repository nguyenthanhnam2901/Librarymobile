package vn.edu.usth.librarybottomnav.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.librarybottomnav.MyDatabaseHelper;
import vn.edu.usth.librarybottomnav.R;

public class BookDetail extends AppCompatActivity {
    private ImageView img;
    private TextView tvTitle, tvAuthor, tvDescription, tvCategory, tvContent;
    private MyDatabaseHelper dbHelper;
    private static final String TAG = "BookDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);

        // Initialize views
        img = findViewById(R.id.img);
        tvTitle = findViewById(R.id.tv1);
        tvAuthor = findViewById(R.id.tv2);
        tvCategory = findViewById(R.id.tv3);
        tvDescription = findViewById(R.id.tv4);
        tvContent = findViewById(R.id.tvContent);

        dbHelper = new MyDatabaseHelper(this);

        // Retrieve intent data
        Intent intent = getIntent();
        int bookId = intent.getIntExtra("id", -1); // Ensure the key "id" matches the ChildAdapter

        // Log the received ID to check if it matches
        System.out.println("DEBUG: Received Book ID in BookDetail: " + bookId);

        if (bookId != -1) {
            fetchBookDetails(bookId);
        } else {
            Toast.makeText(this, "Invalid Book ID", Toast.LENGTH_SHORT).show();
        }


    }

    private void fetchBookDetails(int bookId) {
        if (bookId == -1) {
            Toast.makeText(this, "Invalid Book ID", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            // Query the book table using the bookId parameter
            cursor = db.rawQuery("SELECT * FROM book WHERE id = ?", new String[]{String.valueOf(bookId)});

            if (cursor != null && cursor.moveToFirst()) {
                // Extract data
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TB_book_title));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TB_book_content));
                int authorId = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TB_book_author_id));
                int categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TB_book_category_id));

                String authorName = dbHelper.getAuthorName(authorId); // Fetch author name
                String categoryName = dbHelper.getCategoryName(categoryId); // Fetch category name

                // Set views with data
                tvTitle.setText(title);
                tvAuthor.setText(authorName);
                tvCategory.setText(categoryName);
                tvDescription.setText(description);

                // Assuming the book has an image resource in the database
                img.setImageResource(R.drawable.book1);

                // Fetch and display book content
                String content = dbHelper.getBookContent(bookId);
                tvContent.setText(content);

            } else {
                Toast.makeText(this, "Book details not found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to load book details", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
}

package vn.edu.usth.librarybottomnav.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.librarybottomnav.MyDatabaseHelper;
import vn.edu.usth.librarybottomnav.R;

public class FollowBookActivity extends AppCompatActivity {

    private EditText bookIdEditText;
    private Button followButton;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_book); // Assuming you have an XML layout for this activity

        // Initialize views
        bookIdEditText = findViewById(R.id.book_id_edit_text);
        followButton = findViewById(R.id.follow_button);

        dbHelper = new MyDatabaseHelper(this);

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followBook();
            }
        });
    }

    private void followBook() {
        String bookIdStr = bookIdEditText.getText().toString();
        if (bookIdStr.isEmpty()) {
            Toast.makeText(this, "Please enter a book ID", Toast.LENGTH_SHORT).show();
            return;
        }

        int bookId = Integer.parseInt(bookIdStr);

        // Check if the book exists
        if (isBookExists(bookId)) {
            // Assuming you already have a method to get the current logged-in user's ID
            int userId = getCurrentUserId();

            // Add book to follows table
            long result = addBookToFollows(userId, bookId);
            if (result == -1) {
                Toast.makeText(this, "Failed to follow book", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Successfully followed the book", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Book ID not found", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isBookExists(int bookId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + MyDatabaseHelper.TB_book + " WHERE " + MyDatabaseHelper.TB_book_id + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(bookId)});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    private int getCurrentUserId() {
        // Retrieve the current user ID from your app's session or login logic
        // For now, returning a placeholder value. Replace with actual implementation.
        return 1; // Placeholder value
    }

    private long addBookToFollows(int userId, int bookId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.TB_follows_user_follower_id, userId);
        values.put(MyDatabaseHelper.TB_follows_following_book_id, bookId);

        return db.insert(MyDatabaseHelper.TB_follows, null, values);
    }
}

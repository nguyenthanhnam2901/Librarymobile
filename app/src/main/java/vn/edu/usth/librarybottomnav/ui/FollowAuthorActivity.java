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

public class FollowAuthorActivity extends AppCompatActivity {

    private EditText authorNameEditText;
    private Button followButton;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_author); // Assuming you have an XML layout for this activity

        // Initialize views
        authorNameEditText = findViewById(R.id.author_id_edit_text);
        followButton = findViewById(R.id.follow_button);

        dbHelper = new MyDatabaseHelper(this);

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followAuthor();
            }
        });
    }

    private void followAuthor() {
        String authorName = authorNameEditText.getText().toString();
        if (authorName.isEmpty()) {
            Toast.makeText(this, "Please enter an author's name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the author exists
        if (isAuthorExists(authorName)) {
            // Assuming you already have a method to get the current logged-in user's ID
            int userId = getCurrentUserId();

            // Add author to follows table
            long result = addAuthorToFollows(userId);
            if (result == -1) {
                Toast.makeText(this, "Failed to follow author", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Successfully followed the author", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Author not found", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAuthorExists(String authorName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + MyDatabaseHelper.TB_author + " WHERE " + MyDatabaseHelper.TB_author_id + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{authorName});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    private int getCurrentUserId() {
        // Retrieve the current user ID from your app's session or login logic
        // For now, returning a placeholder value. Replace with actual implementation.
        return 1; // Placeholder value
    }

    private long addAuthorToFollows(int userId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.TB_follows_following_user_id, userId);


        return db.insert(MyDatabaseHelper.TB_follows, null, values);
    }
}

package vn.edu.usth.librarybottomnav.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.librarybottomnav.R;
import vn.edu.usth.librarybottomnav.MyDatabaseHelper;

public class AddBookActivity extends AppCompatActivity {

    EditText titleEditText, contentEditText, categoryEditText, authorEditText;
    Button saveButton;
    MyDatabaseHelper db;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        // Initialize views
        titleEditText = findViewById(R.id.editTextTitle);
        contentEditText = findViewById(R.id.editTextContent);
        categoryEditText = findViewById(R.id.editTextCategory);
        authorEditText = findViewById(R.id.editTextAuthor);
        saveButton = findViewById(R.id.saveButton);

        // Initialize the database helper
        db = new MyDatabaseHelper(this);

        // Set onClickListener for the save button
        saveButton.setOnClickListener(view -> {
            String title = titleEditText.getText().toString();
            String content = contentEditText.getText().toString();
            String category = categoryEditText.getText().toString();
            String author = authorEditText.getText().toString();

            if (title.isEmpty() || content.isEmpty() || category.isEmpty() || author.isEmpty()) {
                Toast.makeText(AddBookActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                long result = db.insertBook(title, content, category, author);
                if (result != -1) {
                    Toast.makeText(AddBookActivity.this, "Book added successfully", Toast.LENGTH_SHORT).show();
                    finish();  // Close the activity and return to the previous one
                } else {
                    Toast.makeText(AddBookActivity.this, "Failed to add book", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

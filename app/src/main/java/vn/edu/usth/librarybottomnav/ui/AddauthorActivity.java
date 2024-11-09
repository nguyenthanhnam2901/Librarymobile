package vn.edu.usth.librarybottomnav.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.librarybottomnav.MyDatabaseHelper;
import vn.edu.usth.librarybottomnav.R;

public class AddauthorActivity extends AppCompatActivity {

    EditText titleEditAuthor;
    Button saveButton1;
    MyDatabaseHelper db;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addauthor);

        // Initialize views
        titleEditAuthor = findViewById(R.id.editTextAuthor);
        saveButton1 = findViewById(R.id.saveButton1);

        // Initialize the database helper
        db = new MyDatabaseHelper(this);

        // Set onClickListener for the save button
        saveButton1.setOnClickListener(view -> {
            String title = titleEditAuthor.getText().toString();


            if (title.isEmpty()) {
                Toast.makeText(AddauthorActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                long result = db.insertAuthor(title);
                if (result != -1) {
                    Toast.makeText(AddauthorActivity.this, "Author added successfully", Toast.LENGTH_SHORT).show();
                    finish();  // Close the activity and return to the previous one
                } else {
                    Toast.makeText(AddauthorActivity.this, "Failed to add Author", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

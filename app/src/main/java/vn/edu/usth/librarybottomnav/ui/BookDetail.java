package vn.edu.usth.librarybottomnav.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.librarybottomnav.R;
import vn.edu.usth.librarybottomnav.ReadBookActivity;

public class BookDetail extends AppCompatActivity {
    private ImageView img;
    private TextView tvTitle, tvAuthor, tvDescription, tvCategory;
    private Button buttonRead, buttonFollow, buttonPlanToRead;

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

        buttonRead = findViewById(R.id.button1);
        buttonFollow = findViewById(R.id.button2);
        buttonPlanToRead = findViewById(R.id.button3);

        // Retrieve intent data
        Intent intent = getIntent();


//        String category = intent.getExtras().getString("category");
//        String description = intent.getExtras().getString("description");
//        String author = intent.getExtras().getString("author");
//        String title = intent.getExtras().getString("title");
//        int image = intent.getExtras().getInt("image");
//        img.setImageResource(image);
//        tvTitle.setText(title);
//        tvAuthor.setText(author);
//        tvCategory.setText(description);
//        tvDescription.setText(category);


        Bundle extras = intent.getExtras();
        if (extras != null) {

            String title = extras.getString("title", "Unknown Title");
            String author = extras.getString("author", "Unknown Author");
            String category = extras.getString("category", "Uncategorized");
            String description = extras.getString("description", "No description available");
            int imageResId = extras.getInt("image", R.drawable.book1);

            tvTitle.setText(title);
            tvAuthor.setText(author);
            tvCategory.setText(category);
            tvDescription.setText(description);
            img.setImageResource(imageResId);
        }


        // Set button click listeners
        buttonRead.setOnClickListener(v -> {
            Intent readBookIntent = new Intent(BookDetail.this, ReadBookActivity.class);
            readBookIntent.putExtra("bookId","id");
            startActivity(readBookIntent);
        });

        buttonFollow.setOnClickListener(v ->
                Toast.makeText(BookDetail.this, "Button Follow clicked!", Toast.LENGTH_SHORT).show()
        );
        buttonPlanToRead.setOnClickListener(v ->
                Toast.makeText(BookDetail.this, "Button Plan to read clicked!", Toast.LENGTH_SHORT).show()
        );
    }
}

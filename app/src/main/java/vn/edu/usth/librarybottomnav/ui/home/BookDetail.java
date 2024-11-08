package vn.edu.usth.librarybottomnav.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.librarybottomnav.R;
import vn.edu.usth.librarybottomnav.ui.ReadBookActivity;

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
        tvDescription = findViewById(R.id.tv3);
        tvCategory = findViewById(R.id.tv4);
        buttonRead = findViewById(R.id.button1);
        buttonFollow = findViewById(R.id.button2);
        buttonPlanToRead = findViewById(R.id.button3);

        // Retrieve intent data
        Intent intent = getIntent();
        if (intent != null) {
            tvTitle.setText(intent.getStringExtra("title"));
            tvAuthor.setText(intent.getStringExtra("author"));
            tvCategory.setText(intent.getStringExtra("category"));
            int imageResId = intent.getIntExtra("image", R.drawable.book1);
            img.setImageResource(imageResId);
        }

        // Set button click listeners
        buttonRead.setOnClickListener(v -> {
            Intent readBookIntent = new Intent(BookDetail.this, ReadBookActivity.class);
            readBookIntent.putExtra("book_id", "id");
            startActivity(readBookIntent);
        });


        buttonFollow.setOnClickListener(v -> Toast.makeText(this, "Follow clicked!", Toast.LENGTH_SHORT).show());
        buttonPlanToRead.setOnClickListener(v -> Toast.makeText(this, "Plan to read clicked!", Toast.LENGTH_SHORT).show());
    }
}

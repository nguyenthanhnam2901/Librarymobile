package vn.edu.usth.librarybottomnav.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.librarybottomnav.R;

public class BookDetail extends AppCompatActivity {
    ImageView img;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.book_detail);

        img =findViewById(R.id.img);
        tv1 =findViewById(R.id.tv1);
        tv2 =findViewById(R.id.tv2);
        tv3 =findViewById(R.id.tv3);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        Intent intent = getIntent();
        String description = intent.getExtras().getString("description");
        String author = intent.getExtras().getString("author");
        String title = intent.getExtras().getString("title");

        int image = intent.getExtras().getInt("image");

        img.setImageResource(image);

        tv1.setText(title);
        tv2.setText(author);
        tv3.setText(description);

        button1.setOnClickListener(v ->
                Toast.makeText(BookDetail.this, "Button Read clicked!", Toast.LENGTH_SHORT).show()
        );

        button2.setOnClickListener(v ->
                Toast.makeText(BookDetail.this, "Button Follow clicked!", Toast.LENGTH_SHORT).show()
        );

        button3.setOnClickListener(v ->
                Toast.makeText(BookDetail.this, "Button Plan to read clicked!", Toast.LENGTH_SHORT).show()
        );

    }
}
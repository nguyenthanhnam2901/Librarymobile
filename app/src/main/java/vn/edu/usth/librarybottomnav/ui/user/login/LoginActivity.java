package vn.edu.usth.librarybottomnav.ui.user.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.librarybottomnav.MainActivity;
import vn.edu.usth.librarybottomnav.R;
import vn.edu.usth.librarybottomnav.MyDatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    TextView signupText;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize the views
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signupText = findViewById(R.id.signupText);

        // Initialize the database helper
        db = new MyDatabaseHelper(this);

        // Set onClickListener for the login button
        loginButton.setOnClickListener(view -> {
            String strUsername = username.getText().toString();
            String strPassword = password.getText().toString();

            if (db.isUserValid(strUsername, strPassword)) {
                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                // Save username to SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", strUsername);
                editor.apply();

                // Navigate to the MainActivity after successful login
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish(); // Optional: Close LoginActivity
            } else {
                Toast.makeText(LoginActivity.this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
            }
        });

        // Set onClickListener for the signup text
        signupText.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, Register.class);
            startActivity(intent);
        });
    }
}

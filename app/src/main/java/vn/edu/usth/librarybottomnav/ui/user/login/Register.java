package vn.edu.usth.librarybottomnav.ui.user.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.librarybottomnav.MainActivity;
import vn.edu.usth.librarybottomnav.R;
import vn.edu.usth.librarybottomnav.MyDatabaseHelper;

public class Register extends AppCompatActivity {

    EditText username, email, password;
    Button registerButton;
    TextView loginText;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize the database helper first
        db = new MyDatabaseHelper(this);

        // Now you can call listAllUsers after initializing db
        db.listAllUsers();

        // Initialize views
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.buttonSignUp);
        loginText = findViewById(R.id.loginText);  // Ensure this ID exists in activity_register.xml

        registerButton.setOnClickListener(view -> {
            String strUsername = username.getText().toString();
            String strEmail = email.getText().toString();
            String strPassword = password.getText().toString();

            if (strUsername.isEmpty() || strEmail.isEmpty() || strPassword.isEmpty()) {
                Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (db.isUsernameExists(strUsername)) {
                // Check if username exists
                Toast.makeText(Register.this, "Username already exists", Toast.LENGTH_SHORT).show();
            } else {
                // If username doesn't exist, insert user
                long result = db.insertUser(strEmail, strUsername, strPassword);
                if (result != -1) {
                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, LoginActivity.class));
                } else {
                    Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for loginText
        loginText.setOnClickListener(view -> {
            Intent intent = new Intent(Register.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}

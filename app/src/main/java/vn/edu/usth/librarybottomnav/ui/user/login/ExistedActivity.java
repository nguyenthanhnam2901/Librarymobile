package vn.edu.usth.librarybottomnav.ui.user.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.librarybottomnav.MainActivity;
import vn.edu.usth.librarybottomnav.R;
import vn.edu.usth.librarybottomnav.ui.user.login.LoginActivity;

public class ExistedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existed);

        // Initialize the logout button
        Button logoutButton = findViewById(R.id.logoutButton);

        // Set an onClickListener for the logout button
        logoutButton.setOnClickListener(view -> {
            // Log out the user by clearing the SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear(); // Remove all user data
            editor.apply(); // Apply changes

            // Show a toast to confirm logout
            Toast.makeText(ExistedActivity.this, "You have logged out.", Toast.LENGTH_SHORT).show();

            // Redirect to LoginActivity
            Intent intent = new Intent(ExistedActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close this activity
        });
    }
}

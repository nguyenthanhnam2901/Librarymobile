package vn.edu.usth.librarybottomnav.ui.user.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.librarybottomnav.MainActivity;
import vn.edu.usth.librarybottomnav.R;
import vn.edu.usth.librarybottomnav.ui.user.UserFragment;

public class ExistedActivity extends AppCompatActivity {

    private TextView userNameTextView, userEmailTextView;
    private EditText userDetailEditText;
    private Button updateUserDetailButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existed);

        // Initialize views
        userNameTextView = findViewById(R.id.user_name);
        userEmailTextView = findViewById(R.id.user_email);
        userDetailEditText = findViewById(R.id.user_detail_edit);
        updateUserDetailButton = findViewById(R.id.update_user_detail_button);
        logoutButton = findViewById(R.id.logoutButton);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Guest");
        String email = sharedPreferences.getString("email", "No email provided");
        String userDetail = sharedPreferences.getString("user_detail", "");

        // Set initial values
        userNameTextView.setText(username);
        userEmailTextView.setText(email);
        userDetailEditText.setText(userDetail);

        // Update user_detail in SharedPreferences when Update button is clicked
        updateUserDetailButton.setOnClickListener(view -> {
            String updatedUserDetail = userDetailEditText.getText().toString();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("user_detail", updatedUserDetail);
            editor.apply(); // Save changes

            // Notify the user
            Toast.makeText(ExistedActivity.this, "User detail updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ExistedActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Log out the user by clearing SharedPreferences and redirecting to MainActivity
        logoutButton.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Toast.makeText(ExistedActivity.this, "You have logged out.", Toast.LENGTH_SHORT).show();

            // Redirect to MainActivity
            Intent intent = new Intent(ExistedActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

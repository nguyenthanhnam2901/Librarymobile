package vn.edu.usth.librarybottomnav.ui.user.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import vn.edu.usth.librarybottomnav.R;

public class SettingActivity extends AppCompatActivity {

    private Switch switcher;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        switcher = findViewById(R.id.switcher);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        boolean nightMode = sharedPreferences.getBoolean("night", false);

        // Set the switch to match the saved preference
        switcher.setChecked(nightMode);

        // Toggle night mode setting when switch is clicked
        switcher.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                saveNightModeState(true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                saveNightModeState(false);
            }
        });
    }

    private void saveNightModeState(boolean nightMode) {
        editor = sharedPreferences.edit();
        editor.putBoolean("night", nightMode);
        editor.apply();
    }
}

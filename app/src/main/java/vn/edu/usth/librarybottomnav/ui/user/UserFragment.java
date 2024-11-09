package vn.edu.usth.librarybottomnav.ui.user;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import vn.edu.usth.librarybottomnav.databinding.FragmentUserBinding;
import vn.edu.usth.librarybottomnav.ui.user.login.LoginActivity;
import vn.edu.usth.librarybottomnav.ui.user.setting.SettingActivity;
import vn.edu.usth.librarybottomnav.ui.user.login.ExistedActivity;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    TextView user_name, user_detail;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Retrieve the username from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Guest"); // Default to "Guest" if not logged in

        // Set the username in user_name TextView
        binding.userName.setText(username);

        // Check if the user is logged in
        boolean isLoggedIn = !username.equals("Guest");

        // If the user is logged in, change the "account" button to "ExistedActivity"
        if (isLoggedIn) {
            binding.account.setText("Account infos");

            // Set the onClickListener for "Existed Account"
            binding.account.setOnClickListener(view -> {
                Intent intent = new Intent(getActivity(), ExistedActivity.class);
                startActivity(intent);
            });

        } else {
            // If the user is not logged in, keep "Login" button and set its listener
            binding.account.setText("Login");

            binding.account.setOnClickListener(view -> {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
            });

        }

        // Existing click listeners for other buttons
        binding.notification.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Notification clicked", Toast.LENGTH_SHORT).show();
        });

        binding.summitWork.setOnClickListener(view -> {
            if (isLoggedIn) {
                Intent intent = new Intent(getContext(), vn.edu.usth.librarybottomnav.ui.AddBookActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "Please log in first", Toast.LENGTH_SHORT).show();
            }
        });

        binding.yourWork.setOnClickListener(view -> {
            if (isLoggedIn) {
                Toast.makeText(getContext(), "Your Work clicked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please log in first", Toast.LENGTH_SHORT).show();
            }
        });

        binding.followingAuthor.setOnClickListener(view -> {
            if (isLoggedIn) {
                Toast.makeText(getContext(), "Following Author clicked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please log in first", Toast.LENGTH_SHORT).show();
            }
        });

        binding.follower.setOnClickListener(view -> {
            if (isLoggedIn) {
                Toast.makeText(getContext(), "Follower clicked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please log in first", Toast.LENGTH_SHORT).show();
            }
        });

        binding.history.setOnClickListener(view -> {
            if (isLoggedIn) {
                Toast.makeText(getContext(), "History clicked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please log in first", Toast.LENGTH_SHORT).show();
            }
        });

        binding.info.setOnClickListener(view -> {
            Toast.makeText(getContext(), "About this App clicked", Toast.LENGTH_SHORT).show();
        });

        binding.helpandfeedback.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Help and Feedback clicked", Toast.LENGTH_SHORT).show();
        });

        binding.setting.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(intent);
        });

        binding.muteSetting.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Mute setting clicked", Toast.LENGTH_SHORT).show();
        });

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

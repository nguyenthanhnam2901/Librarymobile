package vn.edu.usth.librarybottomnav.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import vn.edu.usth.librarybottomnav.databinding.FragmentUserBinding;
import vn.edu.usth.librarybottomnav.ui.user.login.LoginActivity;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.account.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);

            getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            startActivity(intent);
        });




        binding.notification.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Notification clicked", Toast.LENGTH_SHORT).show();
        });

        binding.summitWork.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), vn.edu.usth.librarybottomnav.ui.AddBookActivity.class);
            startActivity(intent);
        });

        binding.yourWork.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Your Work clicked", Toast.LENGTH_SHORT).show();
        });

        binding.followingAuthor.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Following Author clicked", Toast.LENGTH_SHORT).show();
        });


        binding.follower.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Follower clicked", Toast.LENGTH_SHORT).show();
        });

        binding.history.setOnClickListener(view -> {
            Toast.makeText(getContext(), "History clicked", Toast.LENGTH_SHORT).show();
        });

        // Add listeners for the additional TextViews if needed
        binding.info.setOnClickListener(view -> {
            Toast.makeText(getContext(), "About this App clicked", Toast.LENGTH_SHORT).show();
        });

        binding.helpandfeedback.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Help and Feedback clicked", Toast.LENGTH_SHORT).show();
        });

        binding.setting.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Setting clicked", Toast.LENGTH_SHORT).show();
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
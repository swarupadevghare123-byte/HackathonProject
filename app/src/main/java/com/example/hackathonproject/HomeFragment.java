package com.example.hackathonproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.btnMenu).setOnClickListener(v -> {
            if (getActivity() instanceof HomeActivity) {
                ((HomeActivity) getActivity()).openDrawer();
            }
        });

        LottieAnimationView lvThanks = view.findViewById(R.id.lvThanks);
        view.findViewById(R.id.btnRegisterNow).setOnClickListener(v -> {
            if (lvThanks != null) {
                lvThanks.setVisibility(View.VISIBLE);
                lvThanks.playAnimation();
                Toast.makeText(getContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
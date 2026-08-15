package com.example.hackathonproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.card.MaterialCardView;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // मेनू बटण क्लिक लॉजिक (Safety check सहित)
        View btnMenu = view.findViewById(R.id.btnMenu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> {
                if (getActivity() instanceof HomeActivity) {
                    ((HomeActivity) getActivity()).openDrawer();
                }
            });
        }

        // रेजिस्ट्रेशन आणि ॲनिमेशन लॉजिक
        LottieAnimationView lvThanks = view.findViewById(R.id.lvThanks);
        View btnRegisterNow = view.findViewById(R.id.btnRegisterNow);
        if (btnRegisterNow != null) {
            btnRegisterNow.setOnClickListener(v -> {
                if (lvThanks != null) {
                    lvThanks.setVisibility(View.VISIBLE);
                    lvThanks.playAnimation();
                }
                Toast.makeText(getContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
            });
        }

        // हेल्थ कॅम्प कार्डवर क्लिक केल्यावर CampDetailsActivity उघडण्यासाठी (Safety check सहित)
        View cardViewCamp = view.findViewById(R.id.cardViewCamp);
        if (cardViewCamp != null) {
            cardViewCamp.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), CampDetailsActivity.class);
                startActivity(intent);
            });
        }

        // लॅब टेस्ट्स कार्डवर क्लिक केल्यावर LabTestsActivity उघडण्यासाठी
        MaterialCardView cardLabTests = view.findViewById(R.id.cardLabTests);
        if (cardLabTests != null) {
            cardLabTests.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), LabTestsActivity.class);
                startActivity(intent);
            });
        }

        return view;
    }
}

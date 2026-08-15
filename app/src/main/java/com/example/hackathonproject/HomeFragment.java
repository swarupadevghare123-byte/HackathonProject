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
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Menu button logic
        View btnMenu = view.findViewById(R.id.btnMenu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> {
                if (getActivity() instanceof HomeActivity) {
                    ((HomeActivity) getActivity()).openDrawer();
                }
            });
        }

        // Notification button logic
        View btnNotification = view.findViewById(R.id.btnNotification);
        if (btnNotification != null) {
            btnNotification.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), NotificationsActivity.class);
                startActivity(intent);
            });
        }

        // Registration and Animation logic
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

        // Quick Access - Lab Tests
        View cardLabTests = view.findViewById(R.id.cardLabTests);
        if (cardLabTests != null) {
            cardLabTests.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), LabTestsActivity.class);
                startActivity(intent);
            });
        }

        View cardFindDoctors = view.findViewById(R.id.cardFindDoctors);
        if (cardFindDoctors != null) {
            cardFindDoctors.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), Find_DocterActivity.class);
                startActivity(intent);
            });
        }

        View cardReminders = view.findViewById(R.id.cardReminders);
        if (cardReminders != null) {
            cardReminders.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), HealthRemindersActivity.class);
                startActivity(intent);
            });
        }

        View cardHealthTips = view.findViewById(R.id.cardHealthTips);
        if (cardHealthTips != null) {
            cardHealthTips.setOnClickListener(v -> {
                Toast.makeText(getActivity(), "Stay hydrated and exercise daily!", Toast.LENGTH_LONG).show();
            });
        }

        // Category Containers
        View categoryCamps = view.findViewById(R.id.categoryCamps);
        if (categoryCamps != null) {
            categoryCamps.setOnClickListener(v -> navigateToTab(R.id.nav_camps));
        }

        View categoryServices = view.findViewById(R.id.categoryServices);
        if (categoryServices != null) {
            categoryServices.setOnClickListener(v -> navigateToTab(R.id.nav_services));
        }

        View categoryNearMe = view.findViewById(R.id.categoryNearMe);
        if (categoryNearMe != null) {
            categoryNearMe.setOnClickListener(v -> navigateToTab(R.id.nav_camps));
        }

        View categoryEmergency = view.findViewById(R.id.categoryEmergency);
        if (categoryEmergency != null) {
            categoryEmergency.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), EmergencyActivity.class);
                startActivity(intent);
            });
        }

        // View All Camps
        View tvViewAll = view.findViewById(R.id.tvViewAll);
        if (tvViewAll != null) {
            tvViewAll.setOnClickListener(v -> navigateToTab(R.id.nav_camps));
        }

        return view;
    }

    private void navigateToTab(int menuId) {
        if (getActivity() instanceof HomeActivity) {
            BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottom_navigation);
            if (bottomNav != null) {
                bottomNav.setSelectedItemId(menuId);
            }
        }
    }
}

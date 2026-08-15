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

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Click listeners for My Health section
        View bookings = view.findViewById(R.id.optionBookings);
        if (bookings != null) {
            bookings.setOnClickListener(v -> {
                if (getActivity() instanceof HomeActivity) {
                    BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottom_navigation);
                    if (bottomNav != null) {
                        bottomNav.setSelectedItemId(R.id.nav_bookings);
                    }
                }
            });
        }

        View reminders = view.findViewById(R.id.optionReminders);
        if (reminders != null) {
            reminders.setOnClickListener(v -> startActivity(new Intent(getActivity(), HealthRemindersActivity.class)));
        }

        View records = view.findViewById(R.id.optionRecords);
        if (records != null) {
            records.setOnClickListener(v -> startActivity(new Intent(getActivity(), HealthRecordsActivity.class)));
        }

        View reports = view.findViewById(R.id.optionReports);
        if (reports != null) {
            reports.setOnClickListener(v -> startActivity(new Intent(getActivity(), MyReportsActivity.class)));
        }

        // Click listeners for Account section
        View editProfile = view.findViewById(R.id.optionEditProfile);
        if (editProfile != null) {
            editProfile.setOnClickListener(v -> startActivity(new Intent(getActivity(), EditProfileActivity.class)));
        }

        View address = view.findViewById(R.id.optionAddress);
        if (address != null) {
            address.setOnClickListener(v -> startActivity(new Intent(getActivity(), AddressActivity.class)));
        }

        View notifications = view.findViewById(R.id.optionNotifications);
        if (notifications != null) {
            notifications.setOnClickListener(v -> startActivity(new Intent(getActivity(), NotificationSettingsActivity.class)));
        }

        View help = view.findViewById(R.id.optionHelp);
        if (help != null) {
            help.setOnClickListener(v -> startActivity(new Intent(getActivity(), HelpSupportActivity.class)));
        }

        // Logout logic
        View btnLogout = view.findViewById(R.id.btnLogout);
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                Toast.makeText(getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            });
        }

        return view;
    }
}

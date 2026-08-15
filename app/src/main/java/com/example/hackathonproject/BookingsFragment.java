package com.example.hackathonproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class BookingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookings, container, false);

        // Setup mock bookings
        setupBooking(view.findViewById(R.id.booking1), "General Health Camp", "18 Aug 2024 | 10:00 AM", "Confirmed");
        setupBooking(view.findViewById(R.id.booking2), "Dental Checkup Camp", "24 Aug 2024 | 11:00 AM", "Pending");

        return view;
    }

    private void setupBooking(View view, String title, String dateTime, String status) {
        if (view != null) {
            TextView tvTitle = view.findViewById(R.id.tvCampTitle);
            TextView tvDate = view.findViewById(R.id.tvCampDate);
            TextView tvStatus = view.findViewById(R.id.tvDistance); // Using distance field for status in mock
            if (tvTitle != null) tvTitle.setText(title);
            if (tvDate != null) tvDate.setText(dateTime);
            if (tvStatus != null) {
                tvStatus.setText(status);
                int color = status.equals("Confirmed") ? R.color.status_success : R.color.status_info;
                tvStatus.setTextColor(ContextCompat.getColor(requireContext(), color));
            }
        }
    }
}

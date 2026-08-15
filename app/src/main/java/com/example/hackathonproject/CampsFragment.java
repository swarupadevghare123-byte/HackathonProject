package com.example.hackathonproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CampsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camps, container, false);

        View camp1 = view.findViewById(R.id.camp1);
        if (camp1 != null) {
            setupCamp(camp1, "Free Diabetes Checkup Camp", "20 Aug 2024 | 9:00 AM - 1:00 PM", "Sahyadri Hospital, Pune");
            camp1.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), CampDetailsActivity.class);
                startActivity(intent);
            });
        }

        return view;
    }

    private void setupCamp(View view, String title, String date, String location) {
        if (view != null) {
            TextView tvTitle = view.findViewById(R.id.tvCampTitle);
            TextView tvDate = view.findViewById(R.id.tvCampDate);
            TextView tvLocation = view.findViewById(R.id.tvCampLocation);
            if (tvTitle != null) tvTitle.setText(title);
            if (tvDate != null) tvDate.setText(date);
            if (tvLocation != null) tvLocation.setText(location);
        }
    }
}

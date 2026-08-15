package com.example.hackathonproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HealthRecordsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_records);

        ImageView btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Setup mock categories
        setupCategory(findViewById(R.id.cat1), "Prescriptions", "0 files", R.drawable.medical_service);
        setupCategory(findViewById(R.id.cat2), "Lab Reports", "0 files", R.drawable.lab_test);
    }

    private void setupCategory(View view, String title, String count, int iconRes) {
        if (view != null) {
            TextView tvTitle = view.findViewById(R.id.catTitle);
            TextView tvCount = view.findViewById(R.id.catCount);
            ImageView ivIcon = view.findViewById(R.id.catIcon);
            if (tvTitle != null) tvTitle.setText(title);
            if (tvCount != null) tvCount.setText(count);
            if (ivIcon != null) ivIcon.setImageResource(iconRes);
        }
    }
}

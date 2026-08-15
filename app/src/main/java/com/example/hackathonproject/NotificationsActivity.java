package com.example.hackathonproject;


import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationsActivity extends AppCompatActivity {

    TextView backButton;
    TextView markAllRead;

    LinearLayout medicineNotification;
    LinearLayout appointmentNotification;
    LinearLayout healthNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notifications);

        // Find views
        backButton = findViewById(R.id.backButton);
        markAllRead = findViewById(R.id.markAllRead);

        medicineNotification =
                findViewById(R.id.medicineNotification);

        appointmentNotification =
                findViewById(R.id.appointmentNotification);

        healthNotification =
                findViewById(R.id.healthNotification);


        // Back button
        backButton.setOnClickListener(v -> {
            finish();
        });


        // Mark all notifications as read
        markAllRead.setOnClickListener(v -> {

            medicineNotification.setAlpha(0.5f);
            appointmentNotification.setAlpha(0.5f);
            healthNotification.setAlpha(0.5f);

            markAllRead.setText("Read ✓");
        });


        // Medicine notification
        medicineNotification.setOnClickListener(v -> {

            medicineNotification.setAlpha(0.5f);

        });


        // Appointment notification
        appointmentNotification.setOnClickListener(v -> {

            appointmentNotification.setAlpha(0.5f);

        });


        // Health notification
        healthNotification.setOnClickListener(v -> {

            healthNotification.setAlpha(0.5f);

        });
    }
}
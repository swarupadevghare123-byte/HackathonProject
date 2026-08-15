package com.example.hackathonproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EmergencyActivity extends AppCompatActivity {

    TextView backButton;
    TextView sosButton;

    ImageView callMom;
    ImageView callDad;
    ImageView callFriend;

    View findHospitalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_emergency);

        // Find views
        backButton = findViewById(R.id.backButton);
        sosButton = findViewById(R.id.sosButton);

        callMom = findViewById(R.id.callMom);
        callDad = findViewById(R.id.callDad);
        callFriend = findViewById(R.id.callFriend);

        findHospitalButton = findViewById(R.id.findHospitalButton);

        // -------------------------
        // BACK BUTTON
        // -------------------------

        backButton.setOnClickListener(v -> {
            finish();
        });


        // -------------------------
        // SOS ANIMATION
        // -------------------------

        Animation sosAnimation =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.sos_pulse
                );

        sosButton.startAnimation(sosAnimation);


        // -------------------------
        // SOS BUTTON
        // -------------------------

        sosButton.setOnClickListener(v -> {

            Toast.makeText(
                    EmergencyActivity.this,
                    "Emergency SOS",
                    Toast.LENGTH_SHORT
            ).show();

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:112"));

            startActivity(intent);
        });


        // -------------------------
        // MOM CALL
        // -------------------------

        callMom.setOnClickListener(v -> {
            callNumber("+919876543210");
        });


        // -------------------------
        // DAD CALL
        // -------------------------

        callDad.setOnClickListener(v -> {
            callNumber("+919123456789");
        });


        // -------------------------
        // BEST FRIEND CALL
        // -------------------------

        callFriend.setOnClickListener(v -> {
            callNumber("+919987876655");
        });


        // -------------------------
        // FIND NEARBY HOSPITAL
        // -------------------------

        findHospitalButton.setOnClickListener(v -> {

            try {

                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=hospitals+near+me")
                );

                startActivity(intent);

            } catch (Exception e) {

                Toast.makeText(
                        EmergencyActivity.this,
                        "Map application not available",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }


    // -------------------------
    // CALL CONTACT
    // -------------------------

    private void callNumber(String number) {

        Intent intent = new Intent(Intent.ACTION_DIAL);

        intent.setData(
                Uri.parse("tel:" + number)
        );

        startActivity(intent);
    }
}
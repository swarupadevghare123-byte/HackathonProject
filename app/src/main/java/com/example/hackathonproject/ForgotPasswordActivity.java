package com.example.hackathonproject;

import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_password);

        // Find logo
        imgLogo = findViewById(R.id.imgLogo);

        // Start zoom animation
        startLogoAnimation();
    }


    // =========================================
    // LOGO ZOOM IN + ZOOM OUT ANIMATION
    // =========================================

    private void startLogoAnimation() {

        imgLogo.animate()
                .scaleX(1.12f)
                .scaleY(1.12f)
                .setDuration(1200)
                .setInterpolator(
                        new AccelerateDecelerateInterpolator()
                )
                .withEndAction(() -> {

                    imgLogo.animate()
                            .scaleX(1.0f)
                            .scaleY(1.0f)
                            .setDuration(1200)
                            .setInterpolator(
                                    new AccelerateDecelerateInterpolator()
                            )
                            .withEndAction(() -> {

                                // Repeat animation
                                startLogoAnimation();

                            })
                            .start();

                })
                .start();
    }
}
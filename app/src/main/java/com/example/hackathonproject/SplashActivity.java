package com.example.hackathonproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private ImageView outerRing;
    private ImageView innerRing;
    private ImageView logo;
    private int i;

    private TextView appName;
    private TextView subtitle;
    private TextView tagline;

    private LinearLayout dividerLayout;

    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );

        setContentView(R.layout.activity_splash);


        outerRing = findViewById(R.id.outerRing);
        innerRing = findViewById(R.id.innerRing);
        logo = findViewById(R.id.logo);

        appName = findViewById(R.id.appName);
        subtitle = findViewById(R.id.subtitle);
        dividerLayout = findViewById(R.id.dividerLayout);
        tagline = findViewById(R.id.tagline);



        startRingAnimation();
        startLogoAnimation();
        startTextAnimation();


        handler = new Handler();

        handler.postDelayed(() -> {

            Intent intent = new Intent(
                    SplashActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);

            overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
            );

            finish();

        }, 4000);
    }


    private void startRingAnimation() {

        outerRing.startAnimation(
                android.view.animation.AnimationUtils
                        .loadAnimation(
                                this,
                                R.anim.rotate_outer
                        )
        );


        // INNER RING
        innerRing.startAnimation(
                android.view.animation.AnimationUtils
                        .loadAnimation(
                                this,
                                R.anim.rotate_inner
                        )
        );
    }


    private void startLogoAnimation() {

        logo.setAlpha(0f);

        logo.setScaleX(0.65f);
        logo.setScaleY(0.65f);

        logo.animate()

                .alpha(1f)

                .scaleX(1f)
                .scaleY(1f)

                .setDuration(900)

                .setInterpolator(
                        new OvershootInterpolator(1.15f)
                )

                .start();
    }


    private void startTextAnimation() {


        appName.setAlpha(0f);

        appName.setTranslationY(18f);

        appName.animate()

                .alpha(1f)

                .translationY(0f)

                .setStartDelay(550)

                .setDuration(650)

                .setInterpolator(
                        new DecelerateInterpolator()
                )

                .start();

        subtitle.setAlpha(0f);

        subtitle.setTranslationY(12f);

        subtitle.animate()

                .alpha(1f)

                .translationY(0f)

                .setStartDelay(900)

                .setDuration(600)

                .setInterpolator(
                        new DecelerateInterpolator()
                )

                .start();

        dividerLayout.setAlpha(0f);

        dividerLayout.setScaleX(0.5f);

        dividerLayout.animate()

                .alpha(1f)

                .scaleX(1f)

                .setStartDelay(1150)

                .setDuration(550)

                .setInterpolator(
                        new DecelerateInterpolator()
                )

                .start();

        tagline.setAlpha(0f);

        tagline.setTranslationY(10f);

        tagline.animate()
                .alpha(1f)
                .translationY(0f)
                .setStartDelay(1350)
                .setDuration(600)
                .setInterpolator(
                        new DecelerateInterpolator()
                )

                .start();
    }

    @Override
    protected void onDestroy() {

        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }

        super.onDestroy();
    }
}
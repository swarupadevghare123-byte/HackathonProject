package com.example.hackathonproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }


        LinearLayout mainLayout = findViewById(R.id.mainContentLayout);
        if (mainLayout != null) {
            Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
            mainLayout.startAnimation(slideUp);
        }

        TextView tvSignUp = findViewById(R.id.tvSignUp);
        if (tvSignUp != null) {
            tvSignUp.setOnClickListener(v -> {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            });
        }

        Button btnLogin = findViewById(R.id.btnLogin);
        if (btnLogin != null) {
            btnLogin.setOnClickListener(v -> {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            });
        }
    }
}

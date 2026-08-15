package com.example.hackathonproject;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etRegistrationName, etRegistrationMob, etRegistrationEmail, etRegistrationUsername, etRegistrationPassword, etRegistrationconpass;
    private LottieAnimationView lvThanks, ivLogoAnim;
    private MovingGradientButton btnRegistrationForm;
    private TextView tvLoginLink;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        etRegistrationName = findViewById(R.id.etRegistrationName);
        etRegistrationMob = findViewById(R.id.etRegistrationMob);
        etRegistrationEmail = findViewById(R.id.etRegistrationEmail);
        etRegistrationUsername = findViewById(R.id.etRegistrationUsername);
        etRegistrationPassword = findViewById(R.id.etRegistrationPassword);
        etRegistrationconpass = findViewById(R.id.etRegistrationconpass);
        btnRegistrationForm = findViewById(R.id.btnLogin);
        lvThanks = findViewById(R.id.lvThanks);
        ivLogoAnim = findViewById(R.id.ivLogo);
        tvLoginLink = findViewById(R.id.tvLoginLink);
        btnBack = findViewById(R.id.btnBack);

        // Apply entrance animation
        LinearLayout llContainer = findViewById(R.id.llContainer);
        if (llContainer != null) {
            Animation fadeInUp = AnimationUtils.loadAnimation(this, R.anim.fade_in_up);
            llContainer.startAnimation(fadeInUp);
        }

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        if (btnRegistrationForm != null) {
            btnRegistrationForm.setOnClickListener(v -> performRegistration());
        }

        if (tvLoginLink != null) {
            tvLoginLink.setOnClickListener(v -> finish());
        }

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }
    }

    private void performRegistration() {
        String name = etRegistrationName.getText().toString();
        String mob = etRegistrationMob.getText().toString();
        String email = etRegistrationEmail.getText().toString();
        String user = etRegistrationUsername.getText().toString();
        String pass = etRegistrationPassword.getText().toString();
        String conPass = etRegistrationconpass.getText().toString();

        if (name.isEmpty()) {
            etRegistrationName.setError("Please Enter name");
        } else if (mob.isEmpty()) {
            etRegistrationMob.setError("Please Enter Mobile Number");
        } else if (mob.length() != 10) {
            etRegistrationMob.setError("Enter Mobile Number Length must be 10");
        } else if (email.isEmpty()) {
            etRegistrationEmail.setError("Please Enter Email Id");
        } else if (!email.contains("@") || !email.contains(".com")) {
            etRegistrationEmail.setError("Email Id must contains @ and .com");
        } else if (user.isEmpty()) {
            etRegistrationUsername.setError("Please Enter Username");
        } else if (user.length() < 8) {
            etRegistrationUsername.setError("Username must be Atleast 8 Characters");
        } else if (!user.matches(".*[A-Z].*")) {
            etRegistrationUsername.setError("Username Must contains 1 Upper case letter");
        } else if (!user.matches(".*[a-z].*")) {
            etRegistrationUsername.setError("Username Must contains 1 Lower case letter");
        } else if (!user.matches(".*[0-9].*")) {
            etRegistrationUsername.setError("Username Must contains 1 Number");
        } else if (!user.matches(".*[@#$%^&*<>?'!~.+=,-].*")) {
            etRegistrationUsername.setError("Username Must contains 1 Special symbol");
        } else if (pass.isEmpty()) {
            etRegistrationPassword.setError("Please Enter Password");
        } else if (pass.length() < 8) {
            etRegistrationPassword.setError("Password must be Atleast 8 Characters");
        } else if (!pass.matches(".*[A-Z].*")) {
            etRegistrationPassword.setError("Password Must contains 1 Upper case letter");
        } else if (!pass.matches(".*[a-z].*")) {
            etRegistrationPassword.setError("Password Must contains 1 Lower case letter");
        } else if (!pass.matches(".*[0-9].*")) {
            etRegistrationPassword.setError("Password Must contains 1 Number");
        } else if (!pass.matches(".*[@#$%^&*<>?'!~.+=-].*")) {
            etRegistrationPassword.setError("Password Must contains 1 Special symbol");
        } else if (conPass.isEmpty()) {
            etRegistrationconpass.setError("Please Enter confirm Password");
        } else if (!java.util.Objects.equals(pass, conPass)) {
            etRegistrationconpass.setError("Please Enter correct Password");
        } else {
            // Start Success Animation
            if (ivLogoAnim != null) ivLogoAnim.setVisibility(View.GONE);
            if (lvThanks != null) {
                lvThanks.setVisibility(VISIBLE);
                lvThanks.playAnimation();
            }

            // Perform Intent to Home (HomeActivity) after animation
            new Handler().postDelayed(() -> {
                startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
                finish();
            }, 2000);
        }
    }
}

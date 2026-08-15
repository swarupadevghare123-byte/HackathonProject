package com.example.hackathonproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.example.hackathonproject.Common.Urls;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private EditText etLoginUsername, etLoginPassword;
    private CheckBox cbLogin;
    private Button btnLogin;
    private TextView tvregister, tvLoginHeader, tvforgetPass;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private ProgressDialog progress;
    private LottieAnimationView lavview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        tvLoginHeader = findViewById(R.id.tvLoginHeader);

        String role = getIntent().getStringExtra("ROLE");
        if (role == null) {
            role = preferences.getString("ROLE", "student");
        }

        if (role.equals("company")) {
            setTitle("Company Login");
            if (tvLoginHeader != null) tvLoginHeader.setText("Company Login");
        } else {
            setTitle("Student Login");
            if (tvLoginHeader != null) tvLoginHeader.setText("Student Login");
        }

        if (preferences.getBoolean("isLogin", false)) {
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }

        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        cbLogin = findViewById(R.id.cblogin);
        btnLogin = findViewById(R.id.btnLogin);
        tvregister = findViewById(R.id.tvregister);
        tvforgetPass = findViewById(R.id.tvforgetPass);
        lavview = findViewById(R.id.lavview);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Apply entrance animation
        LinearLayout mainLayout = findViewById(R.id.mainContentLayout);
        if (mainLayout != null) {
            Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
            mainLayout.startAnimation(slideUp);
        }

        cbLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etLoginUsername.getText().toString();
                String pass = etLoginPassword.getText().toString();

                if (user.isEmpty()) {
                    etLoginUsername.setError("Please Enter your Username");
                } else if (user.length() < 8) {
                    etLoginUsername.setError("Username must be more than 8");
                } else if (!user.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!*_]).*")) {
                    etLoginUsername.setError("Username must contains Atleast one Upper case letter,one Lower case letter, one Number and one Special symbol");
                } else if (pass.isEmpty()) {
                    etLoginPassword.setError("Please Enter Password");
                } else if (pass.length() < 8) {
                    etLoginPassword.setError("Password must be more than 8");
                } else if (!pass.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!*_]).*")) {
                    etLoginPassword.setError("Password must contains Atleast one Upper case letter,one Lower case letter, one Number and one Special symbol");
                } else {
                    lavview.setVisibility(View.VISIBLE);
                    lavview.playAnimation();

                    progress = new ProgressDialog(LoginActivity.this);
                    progress.setTitle("Login");
                    progress.setMessage("Please wait some time");
                    progress.setCanceledOnTouchOutside(true);
                    progress.show();

                    LoginUser();
                }
            }
        });

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        tvforgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void LoginUser() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Username", etLoginUsername.getText().toString());
        params.put("password", etLoginPassword.getText().toString());
        client.post(Urls.LoginUser, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                progress.dismiss();
                try {
                    String status = response.getString("success");
                    String message = response.getString("message");
                    if (status.equals("1")) {
                        Toast.makeText(LoginActivity.this, "Login Successfully Done", Toast.LENGTH_SHORT).show();
                        editor.putString("Username", etLoginUsername.getText().toString());
                        editor.putBoolean("isLogin", true);
                        editor.apply();
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progress.dismiss();
                Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

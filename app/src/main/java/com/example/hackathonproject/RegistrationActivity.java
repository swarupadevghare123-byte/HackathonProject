package com.example.hackathonproject;

import static android.view.View.VISIBLE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

public class RegistrationActivity extends AppCompatActivity {
    private EditText etRegistrationName, etRegistrationMob, etRegistrationEmail,
            etRegistrationUsername, etRegistrationPassword, etRegistrationconpass;
    private Button btnRegistrationForm;
    private ProgressDialog progress;
    private LottieAnimationView lvThanks, ivLogoAnim;
    private CheckBox cbPass;
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

        btnRegistrationForm = findViewById(R.id.btnregistrationform);
        cbPass = findViewById(R.id.cbPass);
        lvThanks = findViewById(R.id.lvThanks);
        ivLogoAnim = findViewById(R.id.ivLogo);
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

        cbPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etRegistrationPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etRegistrationconpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    etRegistrationPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etRegistrationconpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnRegistrationForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etRegistrationName.getText().toString().isEmpty()) {
                    etRegistrationName.setError("Please Enter name");
                } else if (etRegistrationMob.getText().toString().isEmpty()) {
                    etRegistrationMob.setError("Please Enter Mobile Number");
                } else if (etRegistrationMob.getText().toString().length() != 10) {
                    etRegistrationMob.setError("Enter Mobile Number Length must be 10");
                } else if (etRegistrationEmail.getText().toString().isEmpty()) {
                    etRegistrationEmail.setError("Please Enter Email Id");
                } else if (!etRegistrationEmail.getText().toString().contains("@") || !etRegistrationEmail.getText().toString().contains(".com")) {
                    etRegistrationEmail.setError("Email Id must contains @ and .com");
                } else if (etRegistrationUsername.getText().toString().isEmpty()) {
                    etRegistrationUsername.setError("Please Enter Username");
                } else if (etRegistrationUsername.getText().toString().length() < 8) {
                    etRegistrationUsername.setError("Username must be Atleast 8 Characters");
                } else if (!etRegistrationUsername.getText().toString().matches(".*[A-Z].*")) {
                    etRegistrationUsername.setError("Username Must contains 1 Upper case letter");
                } else if (!etRegistrationUsername.getText().toString().matches(".*[a-z].*")) {
                    etRegistrationUsername.setError("Username Must contains 1 Lower case letter");
                } else if (!etRegistrationUsername.getText().toString().matches(".*[0-9].*")) {
                    etRegistrationUsername.setError("Username Must contains 1 Number");
                } else if (!etRegistrationUsername.getText().toString().matches(".*[@#$%^&*<>?'!~.+=,-].*")) {
                    etRegistrationUsername.setError("Username Must contains 1 Special symbol");
                } else if (etRegistrationPassword.getText().toString().isEmpty()) {
                    etRegistrationPassword.setError("Please Enter Password");
                } else if (etRegistrationPassword.getText().toString().length() < 8) {
                    etRegistrationPassword.setError("Password must be Atleast 8 Characters");
                } else if (!etRegistrationPassword.getText().toString().matches(".*[A-Z].*")) {
                    etRegistrationPassword.setError("Password Must contains 1 Upper case letter");
                } else if (!etRegistrationPassword.getText().toString().matches(".*[a-z].*")) {
                    etRegistrationPassword.setError("Password Must contains 1 Lower case letter");
                } else if (!etRegistrationPassword.getText().toString().matches(".*[0-9].*")) {
                    etRegistrationPassword.setError("Password Must contains 1 Number");
                } else if (!etRegistrationPassword.getText().toString().matches(".*[@#$%^&*<>?'!~.+=-].*")) {
                    etRegistrationPassword.setError("Password Must contains 1 Special symbol");
                } else if (etRegistrationconpass.getText().toString().isEmpty()) {
                    etRegistrationconpass.setError("Please Enter confirm Password");
                } else if (!etRegistrationPassword.getText().toString().equals(etRegistrationconpass.getText().toString())) {
                    etRegistrationconpass.setError("Please Enter correct Password");
                } else {
                    if (ivLogoAnim != null) ivLogoAnim.setVisibility(View.GONE);
                    if (lvThanks != null) {
                        lvThanks.setVisibility(VISIBLE);
                        lvThanks.playAnimation();
                    }

                    // Save data to SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userName", etRegistrationName.getText().toString());

                    editor.apply();

                    progress = new ProgressDialog(RegistrationActivity.this);
                    progress.setTitle("Registration");
                    progress.setMessage("Please wait some time");
                    progress.setCanceledOnTouchOutside(false);
                    progress.show();

                    RegisterUser();
                }
            }
        });

        Button btnGoToLogin = findViewById(R.id.btnGoToLogin);
        if (btnGoToLogin != null) {
            btnGoToLogin.setOnClickListener(v -> finish());
        }

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }
    }

    private void RegisterUser() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("name", etRegistrationName.getText().toString());
        params.put("phone", etRegistrationMob.getText().toString());
        params.put("email", etRegistrationEmail.getText().toString());

        params.put("Username", etRegistrationUsername.getText().toString());
        params.put("password", etRegistrationPassword.getText().toString());

        client.post(Urls.RegisterUser, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                progress.dismiss();
                try {
                    String status = response.getString("success");
                    String message = response.getString("message");
                    if (status.equals("1")) {
                        Toast.makeText(RegistrationActivity.this, "Registration Successfully Done..!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(RegistrationActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progress.dismiss();
                Toast.makeText(RegistrationActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

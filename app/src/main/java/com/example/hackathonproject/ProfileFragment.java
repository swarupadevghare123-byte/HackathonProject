package com.example.hackathonproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hackathonproject.Common.Urls;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ProfileFragment extends Fragment {
    private TextView tvName, tvMob, tvEmail, tvUser, tvEmailPersonal, tvNamePersonal;
    private SharedPreferences preferences;
    private ProgressDialog progress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        tvName = view.findViewById(R.id.tvName);
        tvMob = view.findViewById(R.id.tvMob);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvUser = view.findViewById(R.id.tvUser);
        tvEmailPersonal = view.findViewById(R.id.tvEmailPersonal);
        tvNamePersonal = view.findViewById(R.id.tvNamePersonal);

        progress = new ProgressDialog(getActivity());
        progress.setTitle("Profile");
        progress.setMessage("Loading details...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        getMyDetails();

        // Click listeners for My Health section
        View bookings = view.findViewById(R.id.optionBookings);
        if (bookings != null) {
            bookings.setOnClickListener(v -> {
                if (getActivity() instanceof HomeActivity) {
                    BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottom_navigation);
                    if (bottomNav != null) {
                        bottomNav.setSelectedItemId(R.id.nav_bookings);
                    }
                }
            });
        }

        View reminders = view.findViewById(R.id.optionReminders);
        if (reminders != null) {
            reminders.setOnClickListener(v -> startActivity(new Intent(getActivity(), HealthRemindersActivity.class)));
        }

        View records = view.findViewById(R.id.optionRecords);
        if (records != null) {
            records.setOnClickListener(v -> startActivity(new Intent(getActivity(), HealthRecordsActivity.class)));
        }

        View reports = view.findViewById(R.id.optionReports);
        if (reports != null) {
            reports.setOnClickListener(v -> startActivity(new Intent(getActivity(), MyReportsActivity.class)));
        }

        // Click listeners for Account section
        View editProfile = view.findViewById(R.id.optionEditProfile);
        if (editProfile != null) {
            editProfile.setOnClickListener(v -> startActivity(new Intent(getActivity(), EditProfileActivity.class)));
        }

        View address = view.findViewById(R.id.optionAddress);
        if (address != null) {
            address.setOnClickListener(v -> startActivity(new Intent(getActivity(), AddressActivity.class)));
        }

        View notifications = view.findViewById(R.id.optionNotifications);
        if (notifications != null) {
            notifications.setOnClickListener(v -> startActivity(new Intent(getActivity(), NotificationSettingsActivity.class)));
        }

        View help = view.findViewById(R.id.optionHelp);
        if (help != null) {
            help.setOnClickListener(v -> startActivity(new Intent(getActivity(), HelpSupportActivity.class)));
        }

        // Logout logic
        View btnLogout = view.findViewById(R.id.btnLogout);
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                android.content.SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(getActivity());
                android.content.SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLogin", false);
                editor.remove("Username");
                editor.apply();

                Toast.makeText(getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            });
        }

        return view;
    }

    private void getMyDetails() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Username", preferences.getString("Username", ""));
        client.post(Urls.getMyDetails, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                progress.dismiss();
                try {
                    JSONArray jsonArray = response.getJSONArray("getDetails");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String strName = jsonObject.getString("name");
                        String strMob = jsonObject.getString("phone");
                        String strEmail = jsonObject.getString("email");
                        String strUsername = jsonObject.getString("Username");

                        tvName.setText(strName);
                        tvEmail.setText(strEmail);

                        tvNamePersonal.setText("Name: " + strName);
                        tvMob.setText("Mobile: " + strMob);
                        tvEmailPersonal.setText("Email: " + strEmail);
                        tvUser.setText("Username: " + strUsername);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progress.dismiss();
                Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.hackathonproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class CheckupServicesFragment extends Fragment {

    public CheckupServicesFragment() {
        // Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_checkup_services,
                container,
                false
        );

        // GENERAL CHECKUP
        View general =
                view.findViewById(R.id.cardGeneralCheckup);

        if (general != null) {
            general.setOnClickListener(v ->
                    showMessage(
                            view,
                            "General Health Checkup selected"
                    )
            );
        }

        View heart =
                view.findViewById(R.id.cardHeartCheckup);

        if (heart != null) {
            heart.setOnClickListener(v ->
                    showMessage(
                            view,
                            "Heart Checkup selected"
                    )
            );
        }

        // BLOOD TEST
        View blood =
                view.findViewById(R.id.cardBloodTest);

        if (blood != null) {
            blood.setOnClickListener(v ->
                    showMessage(
                            view,
                            "Blood Test selected"
                    )
            );
        }

        // EYE CHECKUP
        View eye =
                view.findViewById(R.id.cardEyeCheckup);

        if (eye != null) {
            eye.setOnClickListener(v ->
                    showMessage(
                            view,
                            "Eye Checkup selected"
                    )
            );
        }

        // DOCTOR CONSULTATION
        View doctor =
                view.findViewById(R.id.cardDoctorConsultation);

        if (doctor != null) {
            doctor.setOnClickListener(v ->
                    showMessage(
                            view,
                            "Doctor Consultation selected"
                    )
            );
        }

        // GET HELP
        View help =
                view.findViewById(R.id.btnGetHelp);

        if (help != null) {
            help.setOnClickListener(v ->
                    showMessage(
                            view,
                            "Health guidance selected"
                    )
            );
        }

        return view;
    }

    private void showMessage(
            View view,
            String message) {

        Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_SHORT
        ).setAction(
                "OK",
                v -> {}
        ).show();
    }
}
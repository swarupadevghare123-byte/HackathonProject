package com.example.hackathonproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Find_DocterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_docter);

        ImageView btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Setup mock doctors
        setupDoctor(findViewById(R.id.doc1), "Dr. Anjali Sharma", "Cardiologist", "₹500");
        setupDoctor(findViewById(R.id.doc2), "Dr. Rahul Verma", "Pediatrician", "₹400");
        setupDoctor(findViewById(R.id.doc3), "Dr. Sneha Patil", "Dermatologist", "₹600");
    }

    private void setupDoctor(View view, String name, String spec, String fee) {
        if (view != null) {
            TextView tvName = view.findViewById(R.id.tvDoctorName);
            TextView tvSpec = view.findViewById(R.id.tvSpecialization);
            TextView tvFee = view.findViewById(R.id.tvFee);
            View btnBook = view.findViewById(R.id.btnBook);

            if (tvName != null) tvName.setText(name);
            if (tvSpec != null) tvSpec.setText(spec);
            if (tvFee != null) tvFee.setText("Consultation: " + fee);

            if (btnBook != null) {
                btnBook.setOnClickListener(v ->
                        Toast.makeText(this, "Booking requested for " + name, Toast.LENGTH_SHORT).show());
            }
        }
    }
}

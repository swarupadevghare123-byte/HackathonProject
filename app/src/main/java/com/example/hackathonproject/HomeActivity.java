package com.example.hackathonproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);

        if (drawerLayout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(drawerLayout, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // अ‍ॅप सुरू झाल्यावर fragment_home (HomeFragment) लोड करण्यासाठी:
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

        // === Yithe BottomNavigationView cha code add kara ===
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Home icon default select thevayla (optional)
        bottomNavigationView.setSelectedItemId(R.id.nav_home); // Tula menu madhil home chi ID ithe davi lagel

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            // Jar user ne Camp icon var click kele tar
            if (itemId == R.id.nav_camps) { // (Menu file madhli camp chi ID 'nav_camp' asel ani nsel tar tiche exact id ithe takavi)
                Intent intent = new Intent(HomeActivity.this, CampDetailsActivity.class); // Ithe tumchya camp activityche nav dya
                startActivity(intent);
                return true;
            }
            else if (itemId == R.id.nav_home) {
                // Jar home var asel tar already fragment load ahech
                return true;
            }

            return false;
        });
    }

    public void openDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}
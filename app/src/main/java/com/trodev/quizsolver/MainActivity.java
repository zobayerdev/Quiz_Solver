package com.trodev.quizsolver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    /*import all package*/
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);


        // #######################
        // Drawer Layout implement
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // #################################################################
        // navigation view work process
        navigationView.setNavigationItemSelectedListener(this::onOptionsItemSelected);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        loadHomeFragment();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.bottom_menu_home) {
                    loadHomeFragment();
                } else if (itemId == R.id.bottom_menu_govt) {
                    loadGovtFragment();
                } else if (itemId == R.id.bottom_menu_notification) {
                    loadNotificationFragment();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Click", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }


    private void loadHomeFragment() {
        setTitle("Dashboard");
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, homeFragment, "homeFragment");
        fragmentTransaction.commit();
    }

    private void loadGovtFragment() {
        setTitle("Govt Job Circular");
        GovtFragment govtFragment = new GovtFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, govtFragment, "govtFragment");
        fragmentTransaction.commit();
    }

    private void loadNotificationFragment() {
        setTitle("Notification");
        NotificationFragment notificationFragment = new NotificationFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, notificationFragment, "notificationFragment");
        fragmentTransaction.commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {

            case R.id.nav_profile:
                Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_leaderboard:
                Toast.makeText(this, "Leaderboard", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_pdf:
                Toast.makeText(this, "PDF All", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_reminder:
                Toast.makeText(this, "Exam Reminder", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_exam_schedule:
                Toast.makeText(this, "Exam Schedule", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_video:
                Toast.makeText(this, "Reference Video", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_job_reminder:
                Toast.makeText(this, "Govt job Notification", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    // In this code, android lifecycle exit on 2 times back-pressed.
    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}
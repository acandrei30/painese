package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PainLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_level);

        // Minimal button listener
        findViewById(R.id.minimalButtonLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Minimal button click
                navigateToNextActivity("Minimal");
            }
        });

        // Light button listener
        findViewById(R.id.lightButtonLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Light button click
                navigateToNextActivity("Light");
            }
        });

        // Moderate button listener
        findViewById(R.id.moderateButtonLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Moderate button click
                navigateToNextActivity("Moderate");
            }
        });

        // Intense button listener
        findViewById(R.id.intenseButtonLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Intense button click
                navigateToNextActivity("Intense");
            }
        });

        // Severe button listener
        findViewById(R.id.severeButtonLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Severe button click
                navigateToNextActivity("Severe");
            }
        });

    }

    private void navigateToNextActivity(String painLevel) {
        // Example logic: Navigate to another activity with the pain level information
        Intent intent = new Intent(PainLevelActivity.this, PainLocationActivity.class); // Replace 'NextActivity' with the actual name of your next activity
        intent.putExtra("PAIN_LEVEL", painLevel);
        startActivity(intent);
    }
}

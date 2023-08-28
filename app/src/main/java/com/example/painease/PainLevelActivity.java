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

        findViewById(R.id.minimalButtonLayout).setOnClickListener(v -> navigateToNextActivity("Minimal"));
        findViewById(R.id.lightButtonLayout).setOnClickListener(v -> navigateToNextActivity("Light"));
        findViewById(R.id.moderateButtonLayout).setOnClickListener(v -> navigateToNextActivity("Moderate"));
        findViewById(R.id.intenseButtonLayout).setOnClickListener(v -> navigateToNextActivity("Intense"));
        findViewById(R.id.severeButtonLayout).setOnClickListener(v -> navigateToNextActivity("Severe"));
    }

    private void navigateToNextActivity(String painLevel) {
        Intent intent = new Intent(PainLevelActivity.this, MainActivity.class); // Replace with your Male/Female selection activity name.
        intent.putExtra("PAIN_LEVEL", painLevel);
        intent.putExtra("painType", getIntent().getStringExtra("painType"));
        startActivity(intent);
    }
}

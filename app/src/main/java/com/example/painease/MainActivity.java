package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button maleButton, femaleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maleButton = findViewById(R.id.maleButton);
        femaleButton = findViewById(R.id.femaleButton);

        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBasedOnPainType("male");
            }
        });

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBasedOnPainType("female");
            }
        });
    }

    private void navigateBasedOnPainType(String gender) {
        Intent intent;

        // Get the pain type passed from the PainLevelActivity
        String painType = getIntent().getStringExtra("painType");

        if (painType.equals("Emotional")) {
            intent = new Intent(MainActivity.this, EmotionalPhysicalAssociation.class);
        } else {
            if (gender.equals("male")) {
                intent = new Intent(MainActivity.this, PainLocationMaleActivity.class);
            } else {
                intent = new Intent(MainActivity.this, PainLocationActivity.class);
            }
        }

        // Pass along the pain level and the gender
        intent.putExtra("PAIN_LEVEL", getIntent().getStringExtra("PAIN_LEVEL"));
        intent.putExtra("GENDER", gender);
        startActivity(intent);
    }
}

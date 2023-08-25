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
                navigateToPainLocation();
            }
        });

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPainLocation();
            }
        });
    }

    private void navigateToPainLocation() {
        Intent intent = new Intent(MainActivity.this, PainLevelActivity.class);
        startActivity(intent);
    }
}

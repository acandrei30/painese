package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class EmotionalPhysicalAssociation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotional_physical_association);

        Button okButton = findViewById(R.id.okButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPainLocation();
            }
        });
    }

    private void navigateToPainLocation() {
        String gender = getIntent().getStringExtra("GENDER");
        Intent intent;

        if (gender.equals("male")) {
            intent = new Intent(EmotionalPhysicalAssociation.this, PainLocationActivityMale.class);
        } else {
            intent = new Intent(EmotionalPhysicalAssociation.this, PainLocationActivity.class);
        }

        // If there are more data to be passed along, add them here
        intent.putExtra("PAIN_LEVEL", getIntent().getStringExtra("PAIN_LEVEL"));
        intent.putExtra("GENDER", gender);
        startActivity(intent);
    }
}

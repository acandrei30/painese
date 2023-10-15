package com.example.painease;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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

        // Resize icons inside buttons
        resizeButtonDrawable(maleButton, R.drawable.male);
        resizeButtonDrawable(femaleButton, R.drawable.female);

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

    private void resizeButtonDrawable(Button button, int drawableResId) {
        // Get the drawable
        Drawable drawable = getResources().getDrawable(drawableResId);

        // Resize the drawable
        int newWidth = drawable.getIntrinsicWidth() / 2; // Change the divisor to resize as needed
        int newHeight = drawable.getIntrinsicHeight() / 2; // Change the divisor to resize as needed
        drawable.setBounds(0, 0, newWidth, newHeight);

        // Set the drawable back to the button
        button.setCompoundDrawables(drawable, null, null, null);
    }

    private void navigateBasedOnPainType(String gender) {
        Intent intent;

        // Get the pain type passed from the PainLevelActivity
        String painType = getIntent().getStringExtra("painType");

        if (painType.equals("Emotional pain") || painType.equals("Anxiety") || painType.equals("Stress") || painType.equals("Anger")) {
            intent = new Intent(MainActivity.this, EmotionalPhysicalAssociation.class);
        } else {
            if (gender.equals("male")) {
                intent = new Intent(MainActivity.this, PainLocationActivityMale.class);
            } else {
                intent = new Intent(MainActivity.this, PainLocationActivity.class);
            }
        }

        // Transfer all extras from the current intent to the new intent
        if (getIntent().getExtras() != null) {
            intent.putExtras(getIntent().getExtras());
        }

        // Now, add/override the gender extra
        intent.putExtra("GENDER", gender);

        startActivity(intent);
    }
}

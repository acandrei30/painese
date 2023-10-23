package com.example.painease;

import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;
import androidx.appcompat.app.AppCompatActivity;

public class EmotionalPhysicalAssociation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotional_physical_association);

        ImageView progressIconImageView = findViewById(R.id.progressIconImageView);
        TextView instructionTextView = findViewById(R.id.instruction);
        LottieAnimationView lottiePainAnimationView = findViewById(R.id.lottiePainAnimationView); // Added this line to fetch the Lottie Animation View

        String painType = getIntent().getStringExtra("painType"); // Retrieve pain type
        if (painType != null) {
            switch (painType) {
                case "Anxiety":
                    instructionTextView.setText("Close your eyes and focus inwardly. Where in your body do you sense the weight of your anxiety?");
                    break;
                case "Stress":
                    instructionTextView.setText("Close your eyes and focus inwardly. Where in your body do you sense the weight of your stress?");
                    break;
                case "Anger":
                    instructionTextView.setText("Close your eyes and focus inwardly. Where in your body do you sense the weight of your anger?");
                    break;
                default:
                    // Default case (Emotional pain or other types)
                    instructionTextView.setText("Close your eyes and focus inwardly. Where in your body do you sense the weight of your emotional pain?");
                    break;
            }
        }

        // Set the appropriate Lottie animation based on gender
        String gender = getIntent().getStringExtra("GENDER");
        if ("male".equals(gender)) {
            lottiePainAnimationView.setAnimation("man_meditation.json");
        } else {
            lottiePainAnimationView.setAnimation("woman_meditation.json");
        }

        progressIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCallitpainActivity();
            }
        });
    }

    private void navigateToCallitpainActivity() {
        String gender = getIntent().getStringExtra("GENDER");
        Intent intent = new Intent(EmotionalPhysicalAssociation.this, CallitpainActivity.class);

        // If there are more data to be passed along, add them here
        intent.putExtra("PAIN_LEVEL", getIntent().getStringExtra("PAIN_LEVEL"));
        intent.putExtra("GENDER", gender);
        startActivity(intent);
    }
}

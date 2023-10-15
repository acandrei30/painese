package com.example.painease;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class EmotionalPhysicalAssociation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotional_physical_association);

        ImageView progressIconImageView = findViewById(R.id.progressIconImageView);
        TextView instructionTextView = findViewById(R.id.instruction); // Added this line

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
        progressIconImageView.setOnClickListener(new View.OnClickListener() {
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
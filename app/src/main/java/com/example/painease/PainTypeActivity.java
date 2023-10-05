package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;

public class PainTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_type);

        LottieAnimationView animationView = findViewById(R.id.lottieAnimationView);
        animationView.playAnimation();

        Button buttonPhysical = findViewById(R.id.buttonPhysical);
        Button buttonEmotional = findViewById(R.id.buttonEmotional);

        // Set onClickListener with lambda expression for buttonPhysical
        buttonPhysical.setOnClickListener(v -> {
            Intent painLevelIntent = new Intent(PainTypeActivity.this, PainLevelActivity.class);
            painLevelIntent.putExtra("painType", "Physical");
            startActivity(painLevelIntent);
        });

        // Set onClickListener with lambda expression for buttonEmotional
        buttonEmotional.setOnClickListener(v -> {
            Intent painLevelIntent = new Intent(PainTypeActivity.this, PainLevelActivity.class);
            painLevelIntent.putExtra("painType", "Emotional");
            startActivity(painLevelIntent);
        });

        TextView startSessionWithoutInstructions = findViewById(R.id.startSessionWithoutInstructions);

        // Set onClickListener with lambda expression for startSessionWithoutInstructions
        startSessionWithoutInstructions.setOnClickListener(v -> {
            Intent intent = new Intent(PainTypeActivity.this, VoiceSelectionActivityDirect.class);
            startActivity(intent);
        });
    }
}

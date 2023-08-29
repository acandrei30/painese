package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;

public class PainTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_type);

        // Initialize and play the Lottie animation
        LottieAnimationView animationView = findViewById(R.id.lottieAnimationView);
        animationView.playAnimation();

        Button buttonPhysical = findViewById(R.id.buttonPhysical);
        Button buttonEmotional = findViewById(R.id.buttonEmotional);

        // On selecting "Physical" pain
        buttonPhysical.setOnClickListener(v -> {
            Intent painLevelIntent = new Intent(PainTypeActivity.this, PainLevelActivity.class);
            painLevelIntent.putExtra("painType", "Physical");
            startActivity(painLevelIntent);
        });

        // On selecting "Emotional" pain
        buttonEmotional.setOnClickListener(v -> {
            Intent painLevelIntent = new Intent(PainTypeActivity.this, PainLevelActivity.class);
            painLevelIntent.putExtra("painType", "Emotional");
            startActivity(painLevelIntent);
        });
    }
}

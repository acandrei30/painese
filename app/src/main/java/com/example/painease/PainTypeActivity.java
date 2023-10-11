package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
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

        // Configure the buttons with subtitles and their respective actions
        configureGeneralButton((Button) findViewById(R.id.buttonPhysical), "Physical pain\nA bodily sensation like 'headache'");
        configureGeneralButton((Button) findViewById(R.id.buttonEmotional), "Emotional pain\nInternal emotional distress");
        configureGeneralButton((Button) findViewById(R.id.buttonAnxiety), "Anxiety\nWorrying, unease, or nervousness");
        configureGeneralButton((Button) findViewById(R.id.buttonStress), "Stress\nGeneral strain and tension");
        configureGeneralButton((Button) findViewById(R.id.buttonAnger), "Anger\nIntense emotional response");

        // Retaining the existing skip instructions functionality
        TextView startSessionWithoutInstructions = findViewById(R.id.startSessionWithoutInstructions);
        startSessionWithoutInstructions.setOnClickListener(v -> {
            Intent intent = new Intent(PainTypeActivity.this, VoiceSelectionActivityDirect.class);
            startActivity(intent);
        });
    }

    private void configureGeneralButton(Button button, String text) {
        String title = text.substring(0, text.indexOf('\n'));
        String subtitle = text.substring(text.indexOf('\n') + 1);

        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        ssb.setSpan(new TextAppearanceSpan(this, R.style.ButtonTitleTextAppearance), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new TextAppearanceSpan(this, R.style.ButtonSubtitleTextAppearance), title.length(), text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        button.setText(ssb, TextView.BufferType.SPANNABLE);

        if (title.equals("Emotional pain")) {
            button.setOnClickListener(v -> {
                Intent painLevelIntent = new Intent(PainTypeActivity.this, PainLevelActivity.class);
                painLevelIntent.putExtra("painType", "Emotional pain");
                startActivity(painLevelIntent);
            });
        } else if (title.equals("Physical pain") || title.equals("Anxiety") || title.equals("Stress") || title.equals("Anger")) {
            button.setOnClickListener(v -> {
                Intent painLevelIntent = new Intent(PainTypeActivity.this, PainLevelActivity.class);
                painLevelIntent.putExtra("painType", title);
                startActivity(painLevelIntent);
            });
        } else {
            button.setOnClickListener(v -> {
                Intent intent = new Intent(PainTypeActivity.this, MainActivity.class);
                intent.putExtra("PAIN_TYPE", title);
                startActivity(intent);
            });
        }
    }
}





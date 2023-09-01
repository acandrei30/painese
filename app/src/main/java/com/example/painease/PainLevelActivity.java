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

public class PainLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_level);

        configureButton((Button) findViewById(R.id.minimalButton), "Minimal\nI barely notice it");
        configureButton((Button) findViewById(R.id.lightButton), "Light\nI can feel it lightly");
        configureButton((Button) findViewById(R.id.moderateButton), "Moderate\nIt's uncomfortable");
        configureButton((Button) findViewById(R.id.intenseButton), "Intense\nIt's difficult to manage");
        configureButton((Button) findViewById(R.id.severeButton), "Severe\nI am bedridden");

    }

    private void configureButton(Button button, String text) {
        String title = text.substring(0, text.indexOf('\n'));
        String subtitle = text.substring(text.indexOf('\n') + 1);

        SpannableStringBuilder ssb = new SpannableStringBuilder(text);

        // Apply the title text appearance
        ssb.setSpan(new TextAppearanceSpan(this, R.style.ButtonTitleTextAppearance), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Apply the subtitle text appearance
        ssb.setSpan(new TextAppearanceSpan(this, R.style.ButtonSubtitleTextAppearance), title.length(), text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        button.setText(ssb, TextView.BufferType.SPANNABLE);

        button.setOnClickListener(v -> navigateToNextActivity(title));
    }

    private void navigateToNextActivity(String painLevel) {
        Intent intent = new Intent(PainLevelActivity.this, MainActivity.class);
        intent.putExtra("PAIN_LEVEL", painLevel);
        intent.putExtra("painType", getIntent().getStringExtra("painType"));
        startActivity(intent);
    }
}

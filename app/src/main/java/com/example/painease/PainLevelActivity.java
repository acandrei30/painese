package com.example.painease;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PainLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_level);

        // Get the pain type from the intent
        String painType = getIntent().getStringExtra("painType");

        // Update the title based on pain type
        TextView titleText = findViewById(R.id.titleText);
        if (painType != null && !painType.isEmpty()) {
            titleText.setText("What is your current\n " + painType.toLowerCase() + " level?");
        }

        // Resize the icons for the buttons
        resizeButtonIcon((Button) findViewById(R.id.minimalButton), R.drawable.minimal);
        resizeButtonIcon((Button) findViewById(R.id.lightButton), R.drawable.light);
        resizeButtonIcon((Button) findViewById(R.id.moderateButton), R.drawable.moderate);
        resizeButtonIcon((Button) findViewById(R.id.intenseButton), R.drawable.intense);
        resizeButtonIcon((Button) findViewById(R.id.severeButton), R.drawable.severe);

        configureButton((Button) findViewById(R.id.minimalButton), "Minimal\nI barely notice it");
        configureButton((Button) findViewById(R.id.lightButton), "Light\nI can feel it lightly");
        configureButton((Button) findViewById(R.id.moderateButton), "Moderate\nIt's uncomfortable");
        configureButton((Button) findViewById(R.id.intenseButton), "Intense\nIt's difficult to manage");
        configureButton((Button) findViewById(R.id.severeButton), "Severe\nI cannot handle it");
    }

    private void resizeButtonIcon(Button button, int drawableResId) {
        Drawable drawable = getResources().getDrawable(drawableResId);

        int newWidth = (int) (drawable.getIntrinsicWidth() * (1.8 / 3.0)); // resizing to 66.67% of original size
        int newHeight = (int) (drawable.getIntrinsicHeight() * (1.8 / 3.0)); // resizing to 66.67% of original size
        drawable.setBounds(0, 0, newWidth, newHeight);

        button.setCompoundDrawables(drawable, null, null, null);
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

package com.example.painease;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PhoneVibratingActivity extends AppCompatActivity {

    private TextView countdownTextView;
    private CountDownTimer countDownTimer;
    private String selectedVoice; // Declare the variable here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_vibrating);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        selectedVoice = getIntent().getStringExtra("selectedVoice"); // Retrieve the voice selection

        countdownTextView = new TextView(this); // Create a new TextView programmatically
        countdownTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        countdownTextView.setText("7"); // Set initial value to 7 seconds
        countdownTextView.setTextSize(70); // Set font size (you can adjust as needed)
        countdownTextView.setTextColor(getResources().getColor(android.R.color.white)); // Set text color

        LinearLayout layout = findViewById(R.id.mainLinearLayout); // Assuming you have an id for the main LinearLayout
        layout.addView(countdownTextView);

        // Vibrate phone for 2 seconds immediately after the page is loaded
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(2000); // vibrate for 2 seconds
        }

        // Initialize countdown timer for 7 seconds
        countDownTimer = new CountDownTimer(7000, 1000) {
            public void onTick(long millisUntilFinished) {
                countdownTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                Intent intent = new Intent(PhoneVibratingActivity.this, StartSessionActivity.class);
                intent.putExtra("selectedVoice", selectedVoice); // Forwarding the voice name
                startActivity(intent);
            }

        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}

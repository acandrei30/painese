package com.example.painease;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class PhoneVibratingActivity extends AppCompatActivity {

    private ImageView toStartSessionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_vibrating);

        toStartSessionButton = findViewById(R.id.toStartSessionButton);

        // Vibrate phone for 2 seconds immediately after the page is loaded
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(2000); // vibrate for 2 seconds
        }

        // Listener for the ImageView to navigate to the StartSessionActivity
        toStartSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneVibratingActivity.this, StartSessionActivity.class);
                startActivity(intent);
            }
        });
    }
}

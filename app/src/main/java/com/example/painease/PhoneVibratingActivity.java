package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class PhoneVibratingActivity extends AppCompatActivity {

    private ImageView toStartSessionButton; // Changed from Button to ImageView
    private ImageView phoneVibratingImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_vibrating);

        toStartSessionButton = findViewById(R.id.toStartSessionButton);
        phoneVibratingImageView = findViewById(R.id.phoneVibratingImageView);

        // Load the GIF using Glide
        Glide.with(this)
                .asGif()
                .load(R.drawable.phone_vibrating)
                .into(phoneVibratingImageView);

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

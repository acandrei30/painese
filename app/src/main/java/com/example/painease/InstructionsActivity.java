package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class InstructionsActivity extends AppCompatActivity {

    private Button startSessionButton;
    private ImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);  // This links to your XML layout for this activity

        startSessionButton = findViewById(R.id.start_session_button);
        gifImageView = findViewById(R.id.gifImageView); // Get reference to the ImageView for the GIF

        // Load the GIF using Glide
        Glide.with(this)
                .asGif()
                .load(R.drawable.head)
                .into(gifImageView);

        startSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstructionsActivity.this, StartSessionActivity.class);
                startActivity(intent);
            }
        });
    }
}

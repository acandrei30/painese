package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class HeadphonesActivity extends AppCompatActivity {

    private ImageView nextIconImageView; // Replacing Button with ImageView
    private ImageView headphonesImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headphones);

        nextIconImageView = findViewById(R.id.nextIconImageView); // Updated ID
        headphonesImageView = findViewById(R.id.headphonesImageView);

        // Load the GIF using Glide
        Glide.with(this)
                .asGif()
                .load(R.drawable.headphones)
                .into(headphonesImageView);

        // Setting click listener on the ImageView
        nextIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeadphonesActivity.this, InstructionsActivity.class);
                startActivity(intent);
            }
        });
    }
}

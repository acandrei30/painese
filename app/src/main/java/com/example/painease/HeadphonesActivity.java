package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class HeadphonesActivity extends AppCompatActivity {

    private ImageView nextIconImageView;
    private ImageView headphonesImageView;
    private String selectedPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headphones);

        nextIconImageView = findViewById(R.id.nextIconImageView);

        // Receive the selected body part data from the PainLocationActivity
        selectedPart = getIntent().getStringExtra("selectedPart");

        nextIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeadphonesActivity.this, InstructionsActivity.class);

                // Pass the selected body part data to InstructionsActivity
                intent.putExtra("selectedPart", selectedPart);

                startActivity(intent);
            }
        });
    }
}

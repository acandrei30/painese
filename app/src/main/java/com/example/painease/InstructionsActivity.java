package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class InstructionsActivity extends AppCompatActivity {

    private ImageView toPhoneVibratingButton; // Changed from Button to ImageView
    private ImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        gifImageView = findViewById(R.id.gifImageView);
        toPhoneVibratingButton = findViewById(R.id.toPhoneVibratingButton);

        String selectedPart = getIntent().getStringExtra("selectedPart");
        if (selectedPart == null) {
            selectedPart = "default";
        }

        int gifResource;
        switch (selectedPart) {
            case "head":
                gifResource = R.drawable.head_instruction;
                break;
            case "throat":
                gifResource = R.drawable.throat_instruction;
                break;
            default:
                gifResource = R.drawable.head_instruction;
                break;
        }

        Glide.with(this)
                .asGif()
                .load(gifResource)
                .into(gifImageView);

        // Listener for the ImageView to navigate to the PhoneVibratingActivity
        toPhoneVibratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstructionsActivity.this, PhoneVibratingActivity.class);
                startActivity(intent);
            }
        });
    }
}

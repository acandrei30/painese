package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;

public class InstructionsActivity extends AppCompatActivity {

    private ImageView toPhoneVibratingButton;
    private ImageView pngImageView;
    private LottieAnimationView lottiePainAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        pngImageView = findViewById(R.id.pngImageView);
        toPhoneVibratingButton = findViewById(R.id.toPhoneVibratingButton);
        lottiePainAnimationView = findViewById(R.id.lottiePainAnimationView); // Initialize the Lottie View for pain2.json

        String selectedPart = getIntent().getStringExtra("selectedPart");
        if (selectedPart == null) {
            selectedPart = "default";
        }

        int imageResource;
        switch (selectedPart) {
            case "head":
                imageResource = R.drawable.female_front_head;
                break;
            case "throat":
                imageResource = R.drawable.female_front_throat;
                break;
            case "chest":
                imageResource = R.drawable.female_front_chest;
                break;
            case "rightarm":
                imageResource = R.drawable.female_front_right_arm;
                break;
            case "leftarm":
                imageResource = R.drawable.female_front_left_arm;
                break;
            case "stomach":
                imageResource = R.drawable.female_close_stomach;
                break;
            case "core":
                imageResource = R.drawable.female_front_core;
                break;
            case "leftwrist":
                imageResource = R.drawable.female_front_left_wrist;
                break;
            case "rightwrist":
                imageResource = R.drawable.female_close_right_wrist;
                break;
            case "rightleg":
                imageResource = R.drawable.female_legs;
                break;
            case "leftleg":
                imageResource = R.drawable.female_legs;
                break;
            case "rightankle":
                imageResource = R.drawable.female_close_right_ankle;
                break;
            case "leftankle":
                imageResource = R.drawable.female_close_left_ankle;
                break;
            // Back

            case "backhead":
                imageResource = R.drawable.female_close_back_head;
                break;
            case "backupper":
                imageResource = R.drawable.female_upper_back;
                break;
            case "backmid":
                imageResource = R.drawable.female_close_mid_back;
                break;
            case "backlower":
                imageResource = R.drawable.female_close_lower_back;
                break;
            case "bum":
                imageResource = R.drawable.female_close_lower_back;
                break;
            case "backleftleg":
                imageResource = R.drawable.female_back_legs;
                break;
            case "backrightleg":
                imageResource = R.drawable.female_back_legs;
                break;
            case "leftheel":
                imageResource = R.drawable.female_close_left_heel;
                break;
            case "rightheel":
                imageResource = R.drawable.female_close_right_heel;
                break;

            // Male front

            case "malehead":
                imageResource = R.drawable.male_close_head;
                break;
            case "malethroat":
                imageResource = R.drawable.male_close_throat;
                break;
            case "malerightarm":
                imageResource = R.drawable.male_close_right_arm;
                break;
            case "malechest":
                imageResource = R.drawable.male_close_chest;
                break;
            case "maleleftarm":
                imageResource = R.drawable.male_close_chest;
                break;
            case "malestomach":
                imageResource = R.drawable.male_close_stomach;
                break;
            case "malerightwrist":
                imageResource = R.drawable.male_close_right_wrist;
                break;
            case "malecore":
                imageResource = R.drawable.male_close_core;
                break;
            case "maleleftwrist":
                imageResource = R.drawable.male_close_left_wrist;
                break;
            case "maleleftleg":
                imageResource = R.drawable.male_close_legs;
                break;
            case "malerightleg":
                imageResource = R.drawable.male_close_legs;
                break;
            case "malerightankle":
                imageResource = R.drawable.male_close_right_ankle;
                break;
            case "maleleftankle":
                imageResource = R.drawable.male_close_left_ankle;
                break;

            // Back

            case "malebackhead":
                imageResource = R.drawable.male_close_head_back;
                break;
            case "malebackupper":
                imageResource = R.drawable.male_close_upper_back;
                break;
            case "malebackmid":
                imageResource = R.drawable.male_close_mid_back;
                break;
            case "malebum":
                imageResource = R.drawable.male_close_bum;
                break;
            case "malebackleftleg":
                imageResource = R.drawable.male_close_legs_back;
                break;
            case "malebacklower":
                imageResource = R.drawable.male_close_lower_back;
                break;
            case "malebackrightleg":
                imageResource = R.drawable.male_close_legs_back;
                break;
            case "maleleftheel":
                imageResource = R.drawable.male_close_left_heel;
                break;
            case "malerighttheel":
                imageResource = R.drawable.male_close_right_heel;
                break;

            default:
                imageResource = R.drawable.instruction_test; // This is your default image.
                break;
        }

        pngImageView.setImageResource(imageResource);

        // Listener for the ImageView to navigate to the PhoneVibratingActivity
        toPhoneVibratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstructionsActivity.this, PhoneVibratingActivity.class);
                startActivity(intent);
            }
        });

        // Configure the Lottie animation for pain2.json
        lottiePainAnimationView.setAlpha(0.5f); // 70% transparency (0.3 opaque)
        lottiePainAnimationView.pauseAnimation(); // Ensure the animation doesn't play right away

        // Add 5-second delay and then start the Lottie animation for pain2.json
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lottiePainAnimationView.playAnimation();
            }
        }, 2000); // 5-second delay
    }
}

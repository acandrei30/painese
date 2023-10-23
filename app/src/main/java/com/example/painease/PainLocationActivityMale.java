package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class PainLocationActivityMale extends AppCompatActivity {

    private ImageView bodyBase, bodyBackBase,
            headOverlay, throatOverlay,
            chestOverlay, stomachOverlay,
            coreOverlay, leftarmOverlay,
            rightarmOverlay, leftwristOverlay,
            rightwristOverlay, leftlegOverlay,
            rightlegOverlay, leftankleOverlay,
            rightankleOverlay, headbackOverlay,
            upperbackOverlay, midbackOverlay,
            lowerbackOverlay, leftlegbackOverlay,
            rightlegbackOverlay, leftheelOverlay,
            righttheelOverlay, bumOverlay, progressIcon;


    private ImageButton switchViewButton;
    private String selectedPartName = "head";
    private boolean isBodyPartSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pain_location_male_page);

        initializeViews();

        progressIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PainLocationActivity", "Sending selected part to HeadphonesActivity: " + selectedPartName);
                Intent intent = new Intent(PainLocationActivityMale.this, HeadphonesActivity.class);
                intent.putExtra("selectedPart", selectedPartName);
                startActivity(intent);

            }
        });

        switchViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchBodyView();
            }
        });
        bodyBase.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    handleTouchOnBody(event.getX(), event.getY());
                }
                return true;
            }
        });
        bodyBackBase.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    handleTouchOnBody(event.getX(), event.getY());
                }
                return true;
            }
        });
    }

    private void initializeViews() {
        bodyBase = findViewById(R.id.bodyImageViewBaseMale);
        bodyBackBase = findViewById(R.id.bodyBackImageViewBaseMale);
        headOverlay = findViewById(R.id.headOverlayMale);
        throatOverlay = findViewById(R.id.throatOverlayMale);
        chestOverlay = findViewById(R.id.chestOverlayMale);
        stomachOverlay = findViewById(R.id.stomachOverlayMale);
        coreOverlay = findViewById(R.id.coreOverlayMale);
        leftarmOverlay = findViewById(R.id.leftarmOverlayMale);
        rightarmOverlay = findViewById(R.id.rightarmOverlayMale);
        leftwristOverlay = findViewById(R.id.leftwristOverlayMale);
        rightwristOverlay = findViewById(R.id.rightwristOverlayMale);
        leftlegOverlay = findViewById(R.id.leftlegOverlayMale);
        rightlegOverlay = findViewById(R.id.rightlegOverlayMale);
        leftankleOverlay = findViewById(R.id.leftankleOverlayMale);
        rightankleOverlay = findViewById(R.id.rightankleOverlayMale);
        headbackOverlay = findViewById(R.id.headbackOverlayMale);
        upperbackOverlay = findViewById(R.id.upperbackOverlayMale);
        midbackOverlay = findViewById(R.id.midbackOverlayMale);
        bumOverlay = findViewById(R.id.bumOverlayMale);
        lowerbackOverlay = findViewById(R.id.lowerbackOverlayMale);
        leftlegbackOverlay = findViewById(R.id.leftlegbackOverlayMale);
        rightlegbackOverlay = findViewById(R.id.rightlegbackOverlayMale);
        leftheelOverlay = findViewById(R.id.leftheelOverlayMale);
        righttheelOverlay = findViewById(R.id.rightheelOverlayMale);


        progressIcon = findViewById(R.id.doneSelectingImageView);
        switchViewButton = findViewById(R.id.switchViewButton);
    }

    private void switchBodyView() {
        if (bodyBase.getVisibility() == View.VISIBLE) {
            bodyBase.setVisibility(View.INVISIBLE);
            bodyBackBase.setVisibility(View.VISIBLE);
            clearAllOverlays();
        } else {
            bodyBase.setVisibility(View.VISIBLE);
            bodyBackBase.setVisibility(View.INVISIBLE);
            clearAllOverlays();
        }
    }

    private void clearAllOverlays() {
        headOverlay.setVisibility(View.INVISIBLE);
        throatOverlay.setVisibility(View.INVISIBLE);
        headOverlay.setVisibility(View.INVISIBLE);
        throatOverlay.setVisibility(View.INVISIBLE);
        chestOverlay.setVisibility(View.INVISIBLE);
        stomachOverlay.setVisibility(View.INVISIBLE);
        coreOverlay.setVisibility(View.INVISIBLE);
        leftarmOverlay.setVisibility(View.INVISIBLE);
        rightarmOverlay.setVisibility(View.INVISIBLE);
        leftwristOverlay.setVisibility(View.INVISIBLE);
        rightwristOverlay.setVisibility(View.INVISIBLE);
        leftlegOverlay.setVisibility(View.INVISIBLE);
        rightlegOverlay.setVisibility(View.INVISIBLE);
        leftankleOverlay.setVisibility(View.INVISIBLE);
        rightankleOverlay.setVisibility(View.INVISIBLE);
        headbackOverlay.setVisibility(View.INVISIBLE);
        upperbackOverlay.setVisibility(View.INVISIBLE);
        midbackOverlay.setVisibility(View.INVISIBLE);
        lowerbackOverlay.setVisibility(View.INVISIBLE);
        leftlegbackOverlay.setVisibility(View.INVISIBLE);
        rightlegbackOverlay.setVisibility(View.INVISIBLE);
        leftheelOverlay.setVisibility(View.INVISIBLE);
        righttheelOverlay.setVisibility(View.INVISIBLE);
        bumOverlay.setVisibility(View.INVISIBLE);

        progressIcon.setVisibility(View.INVISIBLE);
    }

    private void handleTouchOnBody(float x, float y) {
        int width = bodyBase.getWidth();
        int height = bodyBase.getHeight();

        if (isBodyPartSelected) {
            clearAllOverlays();
            switchViewButton.setVisibility(View.VISIBLE);
            progressIcon.setVisibility(View.INVISIBLE);
            isBodyPartSelected = false;
            return;
        }

        clearAllOverlays();

        if (bodyBase.getVisibility() == View.VISIBLE) {
            // Touch Handling for Front Body View

/// For Upper Body
            if (y < height * 0.6) {
                if (y < height * 0.125) {
                    headOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "malehead";
                    Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                } else if (y < height * 0.25) {
                    throatOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "malethroat";
                    Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                } else if (y < height * 0.375) {
                    if (x < width * 0.35) {
                        rightarmOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "malerightarm";
                        Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                    } else if (x >= width * 0.35 && x < width * 0.65) {
                        chestOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "malechest";
                        Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                    } else {
                        leftarmOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "maleleftarm";
                        Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                    }
                } else if (y < height * 0.475 && x >= width * 0.4 && x < width * 0.6) { // Centered stomach section
                    stomachOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "malestomach";
                    Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                } else if (y < height * 0.6) {
                    if (x < width * 0.4) {
                        rightwristOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "malerightwrist";
                        Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                    } else if (x >= width * 0.4 && x < width * 0.6) {
                        coreOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "malecore";
                        Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                    } else {
                        leftwristOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "maleleftwrist";
                        Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                    }
                }
            }

// For Lower Body
            else {
                if (y < height * 0.7) { // Adjusted the threshold for the legs
                    if (x < width * 0.5) {
                        leftlegOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "maleleftleg";
                        Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                    } else {
                        rightlegOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "malerightleg";
                        Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                    }
                } else {
                    if (x < width * 0.5) {
                        rightankleOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "malerightankle";
                        Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                    } else {
                        leftankleOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "maleleftankle";
                        Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                    }
                }
            }


        } else {
            // Touch Handling for Back Male Body View

            // For Upper Back Body
            if (y < height * 0.5) {
                if (y < height * 0.125) {
                    headbackOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "malebackhead";
                    Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                } else if (y < height * 0.25) {
                    upperbackOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "malebackupper";
                    Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                } else if (y < height * 0.375) {
                    midbackOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "malebackmid";
                    Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                } else {
                    lowerbackOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "malebacklower";
                    Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                }
            }
            // For Lower Back Body
            else if (y < height * 0.75) {
                if (y < height * 0.625) {
                    bumOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "malebum";
                    Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                } else if (x < width * 0.5) {
                    leftlegbackOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "malebackleftleg";
                    Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                } else {
                    rightlegbackOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "malebackrightleg";
                    Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                }
            } else {
                if (x < width * 0.5) {
                    leftheelOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "maleleftheel";
                    Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                } else {
                    righttheelOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "malerighttheel";
                    Log.d("PainLocationActivityMale", "Selected body part: " + selectedPartName);
                }
            }
        }
        progressIcon.setVisibility(View.VISIBLE);
        switchViewButton.setVisibility(View.INVISIBLE);
        isBodyPartSelected = true;
    }
}
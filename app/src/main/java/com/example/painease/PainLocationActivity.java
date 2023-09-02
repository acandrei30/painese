package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class PainLocationActivity extends AppCompatActivity {

    private ImageView bodyBase, bodyBackBase, headOverlay, throatOverlay, chestOverlay,
            stomachOverlay, leftarmOverlay, rightarmOverlay, leftwristOverlay,
            rightwristOverlay, coreOverlay, rightlegOverlay, rightankleOverlay,
            leftlegOverlay, leftankleOverlay, progressIcon,
            backheadOverlay, backupperOverlay, backmidOverlay, backlowerOverlay,
            bumOverlay, backleftlegOverlay, backrightlegOverlay, leftheelOverlay,
            rightheelOverlay;

    private ImageButton switchViewButton;
    private String selectedPartName = "head";
    private boolean isBodyPartSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pain_location_page);

        initializeViews();

        progressIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PainLocationActivity", "Sending selected part to HeadphonesActivity: " + selectedPartName);
                Intent intent = new Intent(PainLocationActivity.this, HeadphonesActivity.class);
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
        bodyBase = findViewById(R.id.bodyImageViewBase);
        bodyBackBase = findViewById(R.id.bodyBackImageViewBase);
        headOverlay = findViewById(R.id.headOverlay);
        throatOverlay = findViewById(R.id.throatOverlay);
        chestOverlay = findViewById(R.id.chestOverlay);
        stomachOverlay = findViewById(R.id.stomachOverlay);
        leftarmOverlay = findViewById(R.id.leftarmOverlay);
        rightarmOverlay = findViewById(R.id.rightarmOverlay);
        leftwristOverlay = findViewById(R.id.leftwristOverlay);
        rightwristOverlay = findViewById(R.id.rightwristOverlay);
        coreOverlay = findViewById(R.id.coreOverlay);
        rightlegOverlay = findViewById(R.id.rightlegOverlay);
        rightankleOverlay = findViewById(R.id.rightankleOverlay);
        leftlegOverlay = findViewById(R.id.leftlegOverlay);
        leftankleOverlay = findViewById(R.id.leftankleOverlay);
        backheadOverlay = findViewById(R.id.backheadOverlay);
        backupperOverlay = findViewById(R.id.backupperOverlay);
        backmidOverlay = findViewById(R.id.backmidOverlay);
        backlowerOverlay = findViewById(R.id.backlowerOverlay);
        bumOverlay = findViewById(R.id.bumOverlay);
        backleftlegOverlay = findViewById(R.id.backleftlegOverlay);
        backrightlegOverlay = findViewById(R.id.backrightlegOverlay);
        leftheelOverlay = findViewById(R.id.leftheelOverlay);
        rightheelOverlay = findViewById(R.id.rightheelOverlay);


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
        chestOverlay.setVisibility(View.INVISIBLE);
        stomachOverlay.setVisibility(View.INVISIBLE);
        leftarmOverlay.setVisibility(View.INVISIBLE);
        rightarmOverlay.setVisibility(View.INVISIBLE);
        leftwristOverlay.setVisibility(View.INVISIBLE);
        rightwristOverlay.setVisibility(View.INVISIBLE);
        coreOverlay.setVisibility(View.INVISIBLE);
        rightlegOverlay.setVisibility(View.INVISIBLE);
        rightankleOverlay.setVisibility(View.INVISIBLE);
        leftlegOverlay.setVisibility(View.INVISIBLE);
        leftankleOverlay.setVisibility(View.INVISIBLE);
        backheadOverlay.setVisibility(View.INVISIBLE);
        backupperOverlay.setVisibility(View.INVISIBLE);
        backmidOverlay.setVisibility(View.INVISIBLE);
        backlowerOverlay.setVisibility(View.INVISIBLE);
        bumOverlay.setVisibility(View.INVISIBLE);
        backleftlegOverlay.setVisibility(View.INVISIBLE);
        backrightlegOverlay.setVisibility(View.INVISIBLE);
        leftheelOverlay.setVisibility(View.INVISIBLE);
        rightheelOverlay.setVisibility(View.INVISIBLE);

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

            // For Upper Body
            if (y < height * 0.5) {
                if (y < height * 0.125) {
                    headOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "head";
                    Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                } else if (y < height * 0.25) {
                    throatOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "throat";
                    Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                } else if (y < height * 0.325) {
                    if (x < width * 0.3) {
                        rightarmOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "rightarm";
                        Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                    } else if (x < width * 0.7) {
                        chestOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "chest";
                        Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                    } else {
                        leftarmOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "leftarm";
                        Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                    }
                } else if (y < height * 0.425) {
                    stomachOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "stomach";
                    Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                } else {
                    if (x < width * 0.35) {
                        rightwristOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "rightwrist";
                        Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                    } else if (x < width * 0.65) {
                        coreOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "core";
                        Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                    } else {
                        leftwristOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "leftwrist";
                        Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                    }
                }
            }
            // For Lower Body
            else {
                if (y < height * 0.75) {
                    if (x < width * 0.5) {
                        rightlegOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "rightleg";
                        Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                    } else {
                        leftlegOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "leftleg";
                        Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                    }
                } else {
                    if (x < width * 0.5) {
                        rightankleOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "rightankle";
                        Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                    } else {
                        leftankleOverlay.setVisibility(View.VISIBLE);
                        selectedPartName = "leftankle";
                        Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                    }
                }
            }

        } else {
            // Touch Handling for Back Body View

            // For Upper Back Body
            if (y < height * 0.5) {
                if (y < height * 0.125) {
                    backheadOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "backhead";
                    Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                } else if (y < height * 0.25) {
                    backupperOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "backupper";
                    Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                } else if (y < height * 0.375) {
                    backmidOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "backmid";
                    Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                } else {
                    backlowerOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "backlower";
                    Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                }
            }
            // For Lower Back Body
            else if (y < height * 0.75) {
                if (y < height * 0.625) { // This condition is added to handle the bum area.
                    bumOverlay.setVisibility(View.VISIBLE);
                    selectedPartName = "bum";
                    Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                } else if (x < width * 0.5) {
                    backleftlegOverlay.setVisibility(View.VISIBLE); // Made correction here
                    selectedPartName = "backleftleg";
                    Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                } else {
                    backrightlegOverlay.setVisibility(View.VISIBLE); // Made correction here
                    selectedPartName = "backrightleg";
                    Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                }
            } else {
                if (x < width * 0.5) {
                    leftheelOverlay.setVisibility(View.VISIBLE); // Corrected the heel logic
                    selectedPartName = "leftheel";
                    Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                } else {
                    rightheelOverlay.setVisibility(View.VISIBLE); // Corrected the heel logic
                    selectedPartName = "rightheel";
                    Log.d("PainLocationActivity", "Selected body part: " + selectedPartName);
                }
            }
        }
        progressIcon.setVisibility(View.VISIBLE);
        switchViewButton.setVisibility(View.INVISIBLE);
        isBodyPartSelected = true;
    }
}
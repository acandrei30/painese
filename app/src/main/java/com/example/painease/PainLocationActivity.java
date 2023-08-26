package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PainLocationActivity extends AppCompatActivity {

    private ImageView bodyBase;
    private ImageView bodyBackBase;
    private ImageView headOverlay;
    private ImageView throatOverlay;
    private ImageView chestOverlay;
    private ImageView stomachOverlay;
    private ImageView leftarmOverlay;
    private ImageView rightarmOverlay;
    private ImageView leftwristOverlay;
    private ImageView rightwristOverlay;
    private ImageView coreOverlay;
    private ImageView rightlegOverlay;
    private ImageView rightankleOverlay;
    private ImageView leftlegOverlay;
    private ImageView leftankleOverlay;
    private ImageButton switchViewButton;
    private Button doneSelectingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pain_location_page);

        initializeViews();

        doneSelectingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PainLocationActivity.this, InstructionsActivity.class);
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
        switchViewButton = findViewById(R.id.switchViewButton);
        doneSelectingButton = findViewById(R.id.doneSelectingButton);
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
        doneSelectingButton.setVisibility(View.INVISIBLE);
    }
    private void handleTouchOnBody(float x, float y) {
        int width = bodyBase.getWidth();
        int height = bodyBase.getHeight();

        clearAllOverlays();

        // For Upper Body
        if (y < height * 0.5) {
            if (y < height * 0.125) { // Top 12.5% is the head
                headOverlay.setVisibility(View.VISIBLE);
            } else if (y < height * 0.25) { // Next 12.5% is the throat
                throatOverlay.setVisibility(View.VISIBLE);
            } else if (y < height * 0.325) { // For chest, arms based on horizontal position
                if (x < width * 0.3) {
                    rightarmOverlay.setVisibility(View.VISIBLE);
                } else if (x < width * 0.7) {
                    chestOverlay.setVisibility(View.VISIBLE);
                } else {
                    leftarmOverlay.setVisibility(View.VISIBLE);
                }
            } else if (y < height * 0.425) { // Stomach area
                stomachOverlay.setVisibility(View.VISIBLE);
            } else { // For wrists and core
                if (x < width * 0.35) {
                    rightwristOverlay.setVisibility(View.VISIBLE);
                } else if (x < width * 0.65) {
                    coreOverlay.setVisibility(View.VISIBLE);
                } else {
                    leftwristOverlay.setVisibility(View.VISIBLE);
                }
            }
        }
        // For Lower Body
        else {
            if (y < height * 0.75) { // Legs
                if (x < width * 0.5) {
                    rightlegOverlay.setVisibility(View.VISIBLE);
                } else {
                    leftlegOverlay.setVisibility(View.VISIBLE);
                }
            } else { // Ankles
                if (x < width * 0.5) {
                    rightankleOverlay.setVisibility(View.VISIBLE);
                } else {
                    leftankleOverlay.setVisibility(View.VISIBLE);
                }
            }
        }

        doneSelectingButton.setVisibility(View.VISIBLE);
    }



}

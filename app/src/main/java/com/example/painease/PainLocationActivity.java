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
    private ImageView torsoOverlay;
    private ImageButton switchViewButton;
    private Button doneSelectingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pain_location_page);

        bodyBase = findViewById(R.id.bodyImageViewBase);
        bodyBackBase = findViewById(R.id.bodyBackImageViewBase);
        headOverlay = findViewById(R.id.headOverlay);
        torsoOverlay = findViewById(R.id.torsoOverlay);
        switchViewButton = findViewById(R.id.switchViewButton);

        doneSelectingButton = findViewById(R.id.doneSelectingButton);
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
        });

        bodyBase.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    float y = event.getY();
                    clearAllOverlays();
                    setOverlayImage(y);
                }
                return true;
            }
        });

        // TODO: Add touch listener for bodyBackBase to detect touches for the back
    }

    private void clearAllOverlays() {
        headOverlay.setVisibility(View.INVISIBLE);
        torsoOverlay.setVisibility(View.INVISIBLE);
        doneSelectingButton.setVisibility(View.INVISIBLE);
    }

    private void setOverlayImage(float y) {
        int height = bodyBase.getHeight();
        if (y < height * 0.25) {
            headOverlay.setVisibility(View.VISIBLE);
            doneSelectingButton.setVisibility(View.VISIBLE);
        } else if (y < height * 0.5) {
            torsoOverlay.setVisibility(View.VISIBLE);
            doneSelectingButton.setVisibility(View.VISIBLE);
        }
    }
}

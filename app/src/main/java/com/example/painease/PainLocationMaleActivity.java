package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
// ... [Your other imports]

public class PainLocationMaleActivity extends AppCompatActivity {

    private ImageView bodyBase, bodyBackBase, headOverlay, throatOverlay;
    private ImageButton switchViewButton;
    private String selectedPartName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pain_location_male_page);

        initializeViews();

        switchViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("progress_icon_1".equals((String) v.getTag())) {
                    Intent intent = new Intent(PainLocationMaleActivity.this, HeadphonesActivity.class);
                    intent.putExtra("selectedPart", selectedPartName);
                    startActivity(intent);
                } else {
                    switchBodyView();
                }
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
        bodyBase = findViewById(R.id.bodyImageViewBaseMale);
        bodyBackBase = findViewById(R.id.bodyBackImageViewBaseMale);
        headOverlay = findViewById(R.id.headOverlayMale);
        throatOverlay = findViewById(R.id.throatOverlayMale);
        switchViewButton = findViewById(R.id.switchViewButtonMale);
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
        switchViewButton.setImageResource(R.drawable.turn_arrow_male);
        switchViewButton.setTag(null);
    }

    private void handleTouchOnBody(float x, float y) {
        int width = bodyBase.getWidth();
        int height = bodyBase.getHeight();

        String prevSelectedPart = selectedPartName;

        clearAllOverlays();

        if (y < height * 0.125) {
            if (!"head".equals(prevSelectedPart)) {
                headOverlay.setVisibility(View.VISIBLE);
                selectedPartName = "head";
                switchViewButton.setImageResource(R.drawable.progress_icon_1);
                switchViewButton.setTag("progress_icon_1");
            } else {
                selectedPartName = "";
            }
        } else if (y < height * 0.25) {
            if (!"throat".equals(prevSelectedPart)) {
                throatOverlay.setVisibility(View.VISIBLE);
                selectedPartName = "throat";
                switchViewButton.setImageResource(R.drawable.progress_icon_1);
                switchViewButton.setTag("progress_icon_1");
            } else {
                selectedPartName = "";
            }
        }
        // Additional touch points for other body parts can be added here
    }
}

package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class CallitpainActivity extends AppCompatActivity {

    ImageView progressIconImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callitpain);

        progressIconImageView = findViewById(R.id.progressIconImageView);

        progressIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBasedOnGender();
            }
        });
    }

    private void navigateBasedOnGender() {
        String gender = getIntent().getStringExtra("GENDER");
        Intent intent;

        if (gender != null && gender.equals("male")) {
            intent = new Intent(CallitpainActivity.this, PainLocationActivityMale.class);
        } else {
            intent = new Intent(CallitpainActivity.this, PainLocationActivity.class);
        }

        // Transfer all extras from the current intent to the new intent
        if (getIntent().getExtras() != null) {
            intent.putExtras(getIntent().getExtras());
        }

        startActivity(intent);
    }
}

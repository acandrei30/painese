package com.example.painease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
public class FeedbackFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);

        Button sendButton = findViewById(R.id.sendButton);
        EditText feedbackEditText = findViewById(R.id.feedbackEditText);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the feedback text from the EditText
                String feedbackText = feedbackEditText.getText().toString();

                // Create an Intent to send an email using the ACTION_SENDTO action
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:acandreipp@gmail.com"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from PainEase App");
                emailIntent.putExtra(Intent.EXTRA_TEXT, feedbackText);

                try {
                    startActivity(emailIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Handle the case where no email app is available
                    Toast.makeText(FeedbackFormActivity.this, "No email app found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.example.painease;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class VoiceSelectionActivityDirect extends AppCompatActivity {

    private Button annaButton;
    private Button paulButton;
    private Button claireButton;
    private Button confirmChoiceButton;
    private MediaPlayer mediaPlayer;
    private String selectedPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_selection);

        annaButton = findViewById(R.id.annaButton);
        paulButton = findViewById(R.id.paulButton);
        claireButton = findViewById(R.id.claireButton);
        confirmChoiceButton = findViewById(R.id.confirmChoiceButton);

        selectedPart = getIntent().getStringExtra("selectedPart");

        setupVoiceButton(annaButton, R.raw.anna_sample, "Anna");
        setupVoiceButton(paulButton, R.raw.paul_sample, "Paul");
        setupVoiceButton(claireButton, R.raw.claire_sample, "Claire");

        confirmChoiceButton.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            navigateToInstructionsActivity();
        });
    }

    private void setupVoiceButton(Button button, int rawId, String voiceName) {
        button.setOnClickListener(v -> {
            if(button.isSelected()) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                button.setSelected(false);
                button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.resized_small_play_icon, 0, 0, 0);
                resetAllButtons();
            } else {
                resetAllButtons();
                playSample(rawId, button);
                button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.resized_small_pause_icon, 0, 0, 0);
                button.setSelected(true);
                showConfirmButtonWithText(voiceName);
            }
        });
    }

    private void playSample(int rawId, Button sourceButton) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(this, rawId);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(mp -> {
            sourceButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.resized_small_play_icon, 0, 0, 0);
            sourceButton.setSelected(false); // Ensure the button returns to its default appearance
        });
    }

    private void resetAllButtons() {
        annaButton.setSelected(false);
        paulButton.setSelected(false);
        claireButton.setSelected(false);

        annaButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.resized_small_play_icon, 0, 0, 0);
        paulButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.resized_small_play_icon, 0, 0, 0);
        claireButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.resized_small_play_icon, 0, 0, 0);
    }

    private void showConfirmButtonWithText(String voiceName) {
        confirmChoiceButton.setText("Select " + voiceName);
        confirmChoiceButton.setVisibility(View.VISIBLE);
    }

    private void navigateToInstructionsActivity() {
        Intent intent = new Intent(VoiceSelectionActivityDirect.this, InstructionsActivity.class);
        intent.putExtra("selectedPart", selectedPart);
        intent.putExtra("selectedVoice", confirmChoiceButton.getText().toString().replace("Select ", ""));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

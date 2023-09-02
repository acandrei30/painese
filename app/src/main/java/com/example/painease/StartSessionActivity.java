package com.example.painease;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartSessionActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;
    private CountDownTimer countDownTimer;
    private TextView timerTextView;
    private ImageButton playPauseButton;
    private ImageButton vibrationButton;
    private TextView vibrationStatus;
    private boolean isPaused = false;
    private boolean isVibrating = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_session);

        timerTextView = findViewById(R.id.timerTextView);

        // Setup media player
        mediaPlayer = MediaPlayer.create(this, R.raw.meditation_audio);
        mediaPlayer.setLooping(true);

        // Vibration
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // ImageButton controls
        playPauseButton = findViewById(R.id.playPauseButton);
        ImageButton stopButton = findViewById(R.id.stopButton);
        ImageButton restartButton = findViewById(R.id.restartButton);
        vibrationButton = findViewById(R.id.vibrationButton);
        vibrationStatus = findViewById(R.id.vibrationStatus);

        playPauseButton.setOnClickListener(v -> {
            if (isPaused) {
                resumeSession();
            } else {
                pauseSession();
            }
        });

        stopButton.setOnClickListener(v -> stopSession());
        restartButton.setOnClickListener(v -> restartSession());

        vibrationButton.setOnClickListener(v -> {
            if (isVibrating) {
                stopVibration();
            } else {
                startVibration();
            }
        });

        // Start the session by default
        startSession();
    }

    private void startSession() {
        mediaPlayer.start();

        long[] pattern = {0, 1000};  // Continuous vibration

        if (vibrator != null && isVibrating) {
            vibrator.vibrate(pattern, 0);
        }

        countDownTimer = new CountDownTimer(15 * 60 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.format("%02d:%02d", millisUntilFinished / (60 * 1000), (millisUntilFinished / 1000) % 60));
            }
            public void onFinish() {
                stopSession();
            }
        }.start();

        playPauseButton.setImageResource(R.drawable.pause);
    }

    private void pauseSession() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        if (vibrator != null) {
            vibrator.cancel();
        }
        countDownTimer.cancel();
        isPaused = true;
        playPauseButton.setImageResource(R.drawable.play);
    }

    private void resumeSession() {
        mediaPlayer.start();

        long[] pattern = {0, 1000};  // Continuous vibration

        if (vibrator != null && isVibrating) {
            vibrator.vibrate(pattern, 0);
        }
        countDownTimer.start();
        isPaused = false;
        playPauseButton.setImageResource(R.drawable.pause);
    }

    private void restartSession() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, R.raw.meditation_audio);
            mediaPlayer.setLooping(true);
        }
        if (vibrator != null) {
            vibrator.cancel();
        }
        countDownTimer.cancel();
        timerTextView.setText("15:00");
        startSession();
    }

    private void stopSession() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if (vibrator != null) {
            vibrator.cancel();
        }
        countDownTimer.cancel();
        timerTextView.setText("15:00");
        Intent feedbackIntent = new Intent(StartSessionActivity.this, FeedbackActivity.class);
        startActivity(feedbackIntent);
        finish();
    }

    private void startVibration() {
        long[] pattern = {0, 1000};  // Continuous vibration

        if (vibrator != null) {
            vibrator.vibrate(pattern, 0);
        }
        vibrationStatus.setText("Vibration");
        isVibrating = true;
    }

    private void stopVibration() {
        if (vibrator != null) {
            vibrator.cancel();
        }
        vibrationStatus.setText("Vibration");
        isVibrating = false;
    }
}

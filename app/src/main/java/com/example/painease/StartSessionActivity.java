package com.example.painease;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartSessionActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;
    private CountDownTimer countDownTimer;
    private TextView timerTextView;
    private ImageButton playPauseButton;
    private boolean isPaused = false;

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

        playPauseButton.setOnClickListener(v -> {
            if (isPaused) {
                resumeSession();
            } else {
                pauseSession();
            }
        });

        stopButton.setOnClickListener(v -> stopSession());
        restartButton.setOnClickListener(v -> restartSession());

        // Volume control
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        // Start the session by default
        startSession();
    }

    private void startSession() {
        mediaPlayer.start();

        if (vibrator != null) {
            long[] pattern = {0, 5714, 5714};  // Vibrate at 174Hz
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

        playPauseButton.setImageResource(R.drawable.pause);  // Update to pause icon as music starts playing
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
        playPauseButton.setImageResource(R.drawable.play);  // Update to play icon
    }

    private void resumeSession() {
        mediaPlayer.start();
        if (vibrator != null) {
            long[] pattern = {0, 5714, 5714};
            vibrator.vibrate(pattern, 0);
        }
        countDownTimer.start();
        isPaused = false;
        playPauseButton.setImageResource(R.drawable.pause);  // Update to pause icon
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
}

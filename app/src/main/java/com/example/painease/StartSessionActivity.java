package com.example.painease;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.pm.ActivityInfo;

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

    private Handler vibrationHandler1 = new Handler();
    private Handler vibrationHandler2 = new Handler();
    private Handler vibrationHandler3 = new Handler();
    private Handler vibrationHandler4 = new Handler();
    private Handler vibrationHandler5 = new Handler();

    private Runnable vibrationRunnable1;
    private Runnable vibrationRunnable2;
    private Runnable vibrationRunnable3;
    private Runnable vibrationRunnable4;
    private Runnable vibrationRunnable5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_session);
      // Lock the activity in portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
                startVibration(0);
            }
        });

        startSession();
    }

    private void startSession() {
        mediaPlayer.start();
        countDownTimer = new CountDownTimer(15 * 60 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.format("%02d:%02d", millisUntilFinished / (60 * 1000), (millisUntilFinished / 1000) % 60));
            }

            public void onFinish() {
                stopSession();
            }
        }.start();

        playPauseButton.setImageResource(R.drawable.pause);

        vibrationRunnable1 = () -> startVibration(1);
        vibrationRunnable2 = () -> startVibration(2);
        vibrationRunnable3 = () -> startVibration(1); // 4:41 to 6:04 using pattern 1
        vibrationRunnable4 = () -> startVibration(3); // 7:18 to 8:57 using pattern 3
        vibrationRunnable5 = () -> startVibration(1); // 10:18 to 11:41 using pattern 1

        vibrationHandler1.postDelayed(vibrationRunnable1, 3200); // existing session
        vibrationHandler2.postDelayed(vibrationRunnable2, 134000); // existing session
        vibrationHandler3.postDelayed(vibrationRunnable3, 281000); // 4:41 minute in milliseconds
        vibrationHandler4.postDelayed(vibrationRunnable4, 438000); // 7:18 minute in milliseconds
        vibrationHandler5.postDelayed(vibrationRunnable5, 618000); // 10:18 minute in milliseconds
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
        long[] pattern = {0, 1150, 350};

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
        vibrationHandler1.removeCallbacks(vibrationRunnable1);
        vibrationHandler2.removeCallbacks(vibrationRunnable2);
        vibrationHandler3.removeCallbacks(vibrationRunnable3);
        vibrationHandler4.removeCallbacks(vibrationRunnable4);
        vibrationHandler5.removeCallbacks(vibrationRunnable5);
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
        vibrationHandler1.removeCallbacks(vibrationRunnable1);
        vibrationHandler2.removeCallbacks(vibrationRunnable2);
        vibrationHandler3.removeCallbacks(vibrationRunnable3);
        vibrationHandler4.removeCallbacks(vibrationRunnable4);
        vibrationHandler5.removeCallbacks(vibrationRunnable5);
        timerTextView.setText("15:00");
        Intent feedbackIntent = new Intent(StartSessionActivity.this, FeedbackActivity.class);
        startActivity(feedbackIntent);
        finish();
    }

    private void startVibration(int sessionNumber) {
        long[] pattern1 = {0, 1150, 350};
        long[] pattern2 = {0, 600, 2500};
        long[] pattern3 = {0, Long.MAX_VALUE}; // Continuous vibration

        switch (sessionNumber) {
            case 1:
                if (vibrator != null) {
                    vibrator.vibrate(pattern1, 0);
                }
                new Handler().postDelayed(() -> stopVibration(), 52000);
                break;
            case 2:
                if (vibrator != null) {
                    vibrator.vibrate(pattern2, 0);
                }
                new Handler().postDelayed(() -> stopVibration(), 189000);
                break;
            case 3:
                if (vibrator != null) {
                    vibrator.vibrate(pattern3, 0);
                }
                new Handler().postDelayed(() -> stopVibration(), 99000); // From 7:18 to 8:57 is 1 min 39 seconds or 99000 milliseconds.
                break;
            default:
                if (vibrator != null) {
                    vibrator.vibrate(pattern1, 0);
                }
                break;
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

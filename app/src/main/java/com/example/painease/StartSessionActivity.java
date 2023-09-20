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
    private MediaPlayer voiceMediaPlayer;
    private Vibrator vibrator;
    private CountDownTimer countDownTimer;
    private TextView timerTextView;
    private ImageButton playPauseButton;
    private ImageButton voiceButton;
    private ImageButton vibrationButton;
    private TextView vibrationStatus;
    private boolean isPaused = false;
    private boolean isVibrating = true;
    private boolean isVoiceOn = true;

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
        String selectedVoice = getIntent().getStringExtra("selectedVoice");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_session);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        timerTextView = findViewById(R.id.timerTextView);
        playPauseButton = findViewById(R.id.playPauseButton);
        voiceButton = findViewById(R.id.voiceToggleButton);
        vibrationButton = findViewById(R.id.vibrationButton);
        vibrationStatus = findViewById(R.id.vibrationStatus);

        mediaPlayer = MediaPlayer.create(this, R.raw.meditation_audio);

        // Setting the voice based on user's choice
        int voiceResId;
        switch (selectedVoice) {
            case "Paul":
                voiceResId = R.raw.paul_full;
                break;
            case "Claire":
                voiceResId = R.raw.claire_full;
                break;
            case "Anna":
            default:
                voiceResId = R.raw.anna_full;
                break;
        }
        voiceMediaPlayer = MediaPlayer.create(this, voiceResId);

        mediaPlayer.setLooping(true);
        voiceMediaPlayer.setLooping(true);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        ImageButton stopButton = findViewById(R.id.stopButton);
        ImageButton restartButton = findViewById(R.id.restartButton);
        playPauseButton.setOnClickListener(v -> {
            if (isPaused) {
                resumeSession();
            } else {
                pauseSession();
            }
        });

        voiceButton.setOnClickListener(v -> {
            if (isVoiceOn) {
                voiceMediaPlayer.pause();
                voiceButton.setImageResource(R.drawable.voiceoff);
                isVoiceOn = false;
            } else {
                voiceMediaPlayer.start();
                voiceButton.setImageResource(R.drawable.voiceon);
                isVoiceOn = true;
            }
        });

        vibrationButton.setOnClickListener(v -> {
            if (isVibrating) {
                stopVibration();
                vibrationButton.setImageResource(R.drawable.vibrationoff);
            } else {
                startVibration(0);
                vibrationButton.setImageResource(R.drawable.vibration);
                isVibrating = true;  // Toggle the vibration status
            }
        });

        stopButton.setOnClickListener(v -> stopSession());
        restartButton.setOnClickListener(v -> restartSession());

        startSession();
    }

    private void startSession() {
        mediaPlayer.start();
        voiceMediaPlayer.start();
        isVibrating = true;  // Set the vibration status to true when the session starts

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
        vibrationRunnable3 = () -> startVibration(1);
        vibrationRunnable4 = () -> startVibration(3);
        vibrationRunnable5 = () -> startVibration(1);

        vibrationHandler1.postDelayed(vibrationRunnable1, 3200);
        vibrationHandler2.postDelayed(vibrationRunnable2, 134000);
        vibrationHandler3.postDelayed(vibrationRunnable3, 281000);
        vibrationHandler4.postDelayed(vibrationRunnable4, 438000);
        vibrationHandler5.postDelayed(vibrationRunnable5, 618000);
    }

    private void pauseSession() {
        mediaPlayer.pause();
        voiceMediaPlayer.pause();
        if (vibrator != null) {
            vibrator.cancel();
        }
        countDownTimer.cancel();
        isPaused = true;
        playPauseButton.setImageResource(R.drawable.play);
    }

    private void resumeSession() {
        mediaPlayer.start();
        if (isVoiceOn) {
            voiceMediaPlayer.start();
        }
        if (vibrator != null && isVibrating) {
            long[] pattern = {0, 1150, 350};
            vibrator.vibrate(pattern, 0);
        }
        countDownTimer.start();
        isPaused = false;
        playPauseButton.setImageResource(R.drawable.pause);
    }

    private void restartSession() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(this, R.raw.meditation_audio);
        mediaPlayer.setLooping(true);

        voiceMediaPlayer.stop();
        voiceMediaPlayer.release();
        voiceMediaPlayer = MediaPlayer.create(this, R.raw.anna_full);
        voiceMediaPlayer.setLooping(true);

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
        mediaPlayer.stop();
        mediaPlayer.release();
        voiceMediaPlayer.stop();
        voiceMediaPlayer.release();

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
        finish();
        Intent intent = new Intent(StartSessionActivity.this, FeedbackActivity.class); // replace with the actual activity name
        startActivity(intent);
    }

    private void startVibration(int type) {
        if (isVibrating) {
            long[] pattern = {0, 1150, 350};
            if (vibrator != null) {
                vibrator.vibrate(pattern, 0);
            }
        }
    }

    private void stopVibration() {
        if (vibrator != null) {
            vibrator.cancel();
        }
        isVibrating = false;
        vibrationStatus.setText(R.string.vibration_off);
    }
}

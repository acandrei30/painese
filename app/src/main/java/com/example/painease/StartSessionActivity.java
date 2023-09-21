package com.example.painease;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
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
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> scheduledVibration1;
    private ScheduledFuture<?> scheduledVibration2;
    private ScheduledFuture<?> scheduledVibration3;
    private ScheduledFuture<?> scheduledVibration4;
    private ScheduledFuture<?> scheduledVibration5;
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
    private long remainingTime = 15 * 60 * 1000;

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

    private Runnable stopVibrationRunnable1;
    private Runnable stopVibrationRunnable2;
    private Runnable stopVibrationRunnable3;
    private Runnable stopVibrationRunnable4;
    private Runnable stopVibrationRunnable5;

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
                isVibrating = false;
            } else {
                startVibration(0);
                vibrationButton.setImageResource(R.drawable.vibration);
                isVibrating = true;
            }
        });

        stopButton.setOnClickListener(v -> stopSession());
        restartButton.setOnClickListener(v -> restartSession());

        startSession();
    }

    private void startSession() {
        mediaPlayer.start();
        voiceMediaPlayer.start();

        countDownTimer = new CountDownTimer(remainingTime, 1000) {
            public void onTick(long millisUntilFinished) {
                remainingTime = millisUntilFinished;
                timerTextView.setText(String.format("%02d:%02d", millisUntilFinished / (60 * 1000), (millisUntilFinished / 1000) % 60));
            }

            public void onFinish() {
                stopSession();
            }
        }.start();

        playPauseButton.setImageResource(R.drawable.pause);

        // Vibration patterns
        setupVibrationPatterns();

        // Start vibration patterns
        vibrationHandler1.postDelayed(vibrationRunnable1, 10000);
        vibrationHandler2.postDelayed(vibrationRunnable2, 30000);
        vibrationHandler3.postDelayed(vibrationRunnable3, 60000);
        vibrationHandler4.postDelayed(vibrationRunnable4, 90000);
        vibrationHandler5.postDelayed(vibrationRunnable5, 120000);

    }
    private void scheduleVibrations() {
        scheduledVibration1 = scheduler.schedule(() -> runOnUiThread(() -> startVibration(1)), 10, TimeUnit.SECONDS);
        scheduledVibration2 = scheduler.schedule(() -> runOnUiThread(() -> startVibration(2)), 30, TimeUnit.SECONDS);
        scheduledVibration3 = scheduler.schedule(() -> runOnUiThread(() -> startVibration(3)), 60, TimeUnit.SECONDS);
        scheduledVibration4 = scheduler.schedule(() -> runOnUiThread(() -> startVibration(4)), 90, TimeUnit.SECONDS);
        scheduledVibration5 = scheduler.schedule(() -> runOnUiThread(() -> startVibration(5)), 120, TimeUnit.SECONDS);
    }

    private void pauseSession() {
        mediaPlayer.pause();
        voiceMediaPlayer.pause();
        stopAllVibrations();
        countDownTimer.cancel();
        isPaused = true;
        playPauseButton.setImageResource(R.drawable.play);
    }

    private void resumeSession() {
        mediaPlayer.start();
        if (isVoiceOn) {
            voiceMediaPlayer.start();
        }
        if (isVibrating) {
            startVibration(0);
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

        stopAllVibrations();

        remainingTime = 15 * 60 * 1000;
        timerTextView.setText("15:00");
        startSession();
    }

    private void stopSession() {
        mediaPlayer.stop();
        mediaPlayer.release();
        voiceMediaPlayer.stop();
        voiceMediaPlayer.release();

        stopAllVibrations();

        finish();
        Intent intent = new Intent(StartSessionActivity.this, FeedbackActivity.class);
        startActivity(intent);
    }

    private void startVibration(int type) {
        long[] pattern;
        int repeatIndex;  // to determine which part of the pattern to repeat
        switch (type) {
            case 1:
                pattern = new long[]{0, 1350, 400};
                repeatIndex = 1;  // Repeat the vibration indefinitely
                break;
            case 2:
                pattern = new long[]{0, 700, 2000};
                repeatIndex = 1;
                break;
            case 3:
                pattern = new long[]{0, 1350, 400};
                repeatIndex = 1;  // only one vibration, repeat it
                break;
            case 4:
                pattern = new long[]{0, 2000};
                repeatIndex = 0;
                break;
            case 5:
                pattern = new long[]{0, 1350, 400};
                repeatIndex = 1;
                break;
            default:
                pattern = new long[]{0, 1150, 350};
                repeatIndex = 1;
        }
        if (vibrator != null && isVibrating) {
            vibrator.vibrate(pattern, repeatIndex); // Start the vibration pattern and repeat indefinitely
        }
    }
    private void stopVibration() {
        if (vibrator != null) {
            vibrator.cancel();
        }
        vibrationStatus.setText(R.string.vibration_off);
    }

    private void stopAllVibrations() {
        if (vibrator != null) {
            vibrator.cancel();
        }

        vibrationHandler1.removeCallbacks(vibrationRunnable1);
        vibrationHandler2.removeCallbacks(vibrationRunnable2);
        vibrationHandler3.removeCallbacks(vibrationRunnable3);
        vibrationHandler4.removeCallbacks(vibrationRunnable4);
        vibrationHandler5.removeCallbacks(vibrationRunnable5);

        vibrationHandler1.removeCallbacks(stopVibrationRunnable1);
        vibrationHandler2.removeCallbacks(stopVibrationRunnable2);
        vibrationHandler3.removeCallbacks(stopVibrationRunnable3);
        vibrationHandler4.removeCallbacks(stopVibrationRunnable4);
        vibrationHandler5.removeCallbacks(stopVibrationRunnable5);
    }

    private void setupVibrationPatterns() {
        vibrationRunnable1 = () -> {
            startVibration(1);
            vibrationHandler1.postDelayed(stopVibrationRunnable1, 20000);
        };
        stopVibrationRunnable1 = this::stopVibration;

        vibrationRunnable2 = () -> {
            startVibration(2);
            vibrationHandler2.postDelayed(stopVibrationRunnable2, 50000);
        };
        stopVibrationRunnable2 = this::stopVibration;

        vibrationRunnable3 = () -> {
            startVibration(3);
            vibrationHandler3.postDelayed(stopVibrationRunnable3, 70000);
        };
        stopVibrationRunnable3 = this::stopVibration;

        vibrationRunnable4 = () -> {
            startVibration(4);
            vibrationHandler4.postDelayed(stopVibrationRunnable4, 100000);
        };
        stopVibrationRunnable4 = this::stopVibration;

        vibrationRunnable5 = () -> {
            startVibration(5);
            vibrationHandler5.postDelayed(stopVibrationRunnable5, 140000);
        };
        stopVibrationRunnable5 = this::stopVibration;
    }
}

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

import android.widget.Toast;





public class StartSessionActivity extends AppCompatActivity {

    private boolean isBackPressedOnce = false;
    private long lastVoicePauseTime = 0;
    private int voiceLastPosition = 0;
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

    private Handler vibrationHandler6 = new Handler();
    private Handler vibrationHandler7 = new Handler();

    private Runnable vibrationRunnable1;
    private Runnable vibrationRunnable2;
    private Runnable vibrationRunnable3;
    private Runnable vibrationRunnable4;
    private Runnable vibrationRunnable5;

    private Runnable vibrationRunnable6;
    private Runnable vibrationRunnable7;

    private Runnable stopVibrationRunnable1;
    private Runnable stopVibrationRunnable2;
    private Runnable stopVibrationRunnable3;
    private Runnable stopVibrationRunnable4;
    private Runnable stopVibrationRunnable5;

    private Runnable stopVibrationRunnable6;
    private Runnable stopVibrationRunnable7;

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private long lastStopClickTime = 0;
    private long lastRestartClickTime = 0;
    private long lastPauseClickTime = 0;
    private long lastVoiceClickTime = 0;
    private long lastVibrateClickTime = 0;
    private long lastPlayPauseClickTime = 0;
    private long lastVibrationClickTime = 0;
    private long  lastBackClickTime = 0;

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



        int musicResId; // Declare a variable to store the resource ID of the background music
        switch (selectedVoice) {
            case "Paul":
                musicResId = R.raw.meditation_music_paul; // Assign the correct music file
                break;
            case "Claire":
                musicResId = R.raw.meditation_music_claire; // Assign the correct music file
                break;
            case "Anna":
            default:
                musicResId = R.raw.meditation_music_anna; // Assign the correct music file
                break;
        }
        mediaPlayer = MediaPlayer.create(this, musicResId); // Create the mediaPlayer with the correct music file
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


        mediaPlayer.setLooping(false);
        voiceMediaPlayer.setLooping(false);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        ImageButton stopButton = findViewById(R.id.stopButton);
        ImageButton restartButton = findViewById(R.id.restartButton);





        playPauseButton.setOnClickListener(stopv -> {
            if (System.currentTimeMillis() - lastPlayPauseClickTime < 3000) {
                if (isPaused) {
                    resumeSession();
                } else {
                    pauseSession();
                }
            } else {
                showToast("Please tap again to play/pause");
                lastPlayPauseClickTime = System.currentTimeMillis();
            }
        });

        voiceButton.setOnClickListener(v -> {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastVoiceClickTime < 3000) {
                // Double-click detected, toggle voice
                if (isVoiceOn) {
                    // Pause and store the current position and time
                    voiceLastPosition = voiceMediaPlayer.getCurrentPosition();
                    voiceMediaPlayer.pause();
                    voiceButton.setImageResource(R.drawable.voiceoff);
                    isVoiceOn = false;
                    // Record the time when voice was paused
                    lastVoicePauseTime = System.currentTimeMillis();
                } else {
                    // Calculate the time elapsed since pausing
                    long elapsedTime = System.currentTimeMillis() - lastVoicePauseTime;
                    // Seek to the last position + elapsed time and start playback
                    voiceMediaPlayer.seekTo(voiceLastPosition + (int) elapsedTime);
                    voiceMediaPlayer.start();
                    voiceButton.setImageResource(R.drawable.voiceon);
                    isVoiceOn = true;
                }
            } else {
                // Single click, show tooltip
                showToast("Please tap again to toggle voice");
            }
            lastVoiceClickTime = clickTime;
        });

        vibrationButton.setOnClickListener(v -> {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastVibrationClickTime < 3000) {
                // Double-click detected, toggle vibrations
                if (isVibrating) {
                    stopVibration();
                    vibrationButton.setImageResource(R.drawable.vibrationoff);
                    isVibrating = false;
                } else {
                    long elapsedTime = (15 * 60 * 1000) - remainingTime;

                    // If you have specific times when the vibrations are supposed to be active
                    // you would check the elapsed time and post the corresponding vibration runnables
                    // and also set up the subsequent vibrations.

                    // Example:
                    if (elapsedTime < 10000) {
                        vibrationHandler1.postDelayed(vibrationRunnable1, 10000 - elapsedTime);
                    } else if (elapsedTime < 20000) {
                        vibrationHandler2.postDelayed(vibrationRunnable2, 20000 - elapsedTime);
                    } // And so on, for all vibration patterns

                    vibrationButton.setImageResource(R.drawable.vibration);
                    isVibrating = true;
                }
            } else {
                // Single click, show tooltip
                showToast("Please tap again to toggle vibration");
            }
            lastVibrationClickTime = clickTime;
        });


        stopButton.setOnClickListener(v -> {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastStopClickTime < 3000) {
                // Double-click detected, perform the stop action
                stopSession(); // Replace this with your actual method call.
            } else {
                // Single click, show tooltip
                showToast("Please tap again to stop");
            }
            lastStopClickTime = clickTime;
        });

        restartButton.setOnClickListener(v -> {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastRestartClickTime < 3000) {
                // Double-click detected, perform the restart action
                restartSession(); // Replace this with your actual method call.
            } else {
                // Single click, show tooltip
                showToast("Please tap again to restart");
            }
            lastRestartClickTime = clickTime;
        });

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
        vibrationHandler1.postDelayed(vibrationRunnable1, 3200);
        vibrationHandler2.postDelayed(vibrationRunnable2, 118000);
        vibrationHandler3.postDelayed(vibrationRunnable3, 249000);
        vibrationHandler2.postDelayed(vibrationRunnable4, 396000);
        vibrationHandler3.postDelayed(vibrationRunnable5, 534000);
        vibrationHandler2.postDelayed(vibrationRunnable6, 660000);
        vibrationHandler3.postDelayed(vibrationRunnable7, 780000);


    }

    @Override
    public void onBackPressed() {
        long clickTime = System.currentTimeMillis();
        if (clickTime - lastBackClickTime < 3000) {
            // Double-click detected
            if (isBackPressedOnce) {
                // This is the second tap, perform the back action
                stopSession(); // Stop media playback and clear vibrations

                // Create an intent to navigate back to PainTypeActivity
                Intent intent = new Intent(this, PainTypeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the activity stack
                startActivity(intent);
                finish(); // Finish the current activity
            } else {
                // First tap, set the flag and show the tooltip
                isBackPressedOnce = true;
                showToast("Please tap again to go back");
            }
        } else {
            // Single click, reset the flag and show the tooltip
            isBackPressedOnce = false;
            showToast("Please tap again to go back");
        }
        lastBackClickTime = clickTime;
    }

    private void setupVibrationPatterns() {
        vibrationRunnable1 = () -> {
            startVibration(1);
            vibrationHandler1.postDelayed(stopVibrationRunnable1, 49000);
        };
        stopVibrationRunnable1 = this::stopVibration;

        vibrationRunnable2 = () -> {
            startVibration(2);
            vibrationHandler2.postDelayed(stopVibrationRunnable2, 80000);
        };
        stopVibrationRunnable2 = this::stopVibration;

        vibrationRunnable3 = () -> {
            startVibration(3);
            vibrationHandler3.postDelayed(stopVibrationRunnable3, 88000);
        };
        stopVibrationRunnable3 = this::stopVibration;

        vibrationRunnable4 = () -> {
            startVibration(4);
            vibrationHandler4.postDelayed(stopVibrationRunnable4, 83000);
        };
        stopVibrationRunnable4 = this::stopVibration;

        vibrationRunnable5 = () -> {
            startVibration(5);
            vibrationHandler5.postDelayed(stopVibrationRunnable5, 85000);
        };
        stopVibrationRunnable5 = this::stopVibration;

        vibrationRunnable6 = () -> {
            startVibration(6);
            vibrationHandler6.postDelayed(stopVibrationRunnable6, 85000);
        };
        stopVibrationRunnable6 = this::stopVibration;

        vibrationRunnable7 = () -> {
            startVibration(7);
            vibrationHandler6.postDelayed(stopVibrationRunnable7, 85000);
        };
        stopVibrationRunnable7 = this::stopVibration;



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
    private void pauseSession() {
        mediaPlayer.pause();
        voiceMediaPlayer.pause();
        stopAllVibrations();
        countDownTimer.cancel(); // this will cancel the ongoing countdown
        isPaused = true;
        playPauseButton.setImageResource(R.drawable.play);
    }

    private void resumeSession() {
        mediaPlayer.start();
        if (isVoiceOn) {
            voiceMediaPlayer.start();
        }

        // create a new CountDownTimer with the remaining time
        countDownTimer = new CountDownTimer(remainingTime, 1000) {
            public void onTick(long millisUntilFinished) {
                remainingTime = millisUntilFinished;
                timerTextView.setText(String.format("%02d:%02d", millisUntilFinished / (60 * 1000), (millisUntilFinished / 1000) % 60));
            }

            public void onFinish() {
                // Instead of calling stopSession, start the FeedbackActivity here
                Intent intent = new Intent(StartSessionActivity.this, FeedbackActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity to prevent going back to it from FeedbackActivity
            }
        }.start();

        // Resume the vibration patterns if necessary
        if (isVibrating) {
            // Repost the vibration runnables with updated delay based on the remaining time
            // For example, if the first vibration pattern was supposed to start 1 sec after the start
            // and 3 sec have passed, repost it with a delay of 0
            // You will need to calculate the updated delays based on your vibration pattern logic
            long updatedDelay1 = Math.max(0, 1000 - (15 * 60 * 1000 - remainingTime));
            vibrationHandler1.postDelayed(vibrationRunnable1, updatedDelay1);

            // Do the same for the other vibration patterns
            // e.g.
            long updatedDelay2 = Math.max(0, 10000 - (15 * 60 * 1000 - remainingTime));
            vibrationHandler2.postDelayed(vibrationRunnable2, updatedDelay2);

            long updatedDelay3 = Math.max(0, 10000 - (15 * 60 * 1000 - remainingTime));
            vibrationHandler3.postDelayed(vibrationRunnable3, updatedDelay3);

            long updatedDelay4 = Math.max(0, 10000 - (15 * 60 * 1000 - remainingTime));
            vibrationHandler4.postDelayed(vibrationRunnable4, updatedDelay4);

            long updatedDelay5 = Math.max(0, 10000 - (15 * 60 * 1000 - remainingTime));
            vibrationHandler5.postDelayed(vibrationRunnable4, updatedDelay5);

            long updatedDelay6 = Math.max(0, 10000 - (15 * 60 * 1000 - remainingTime));
            vibrationHandler6.postDelayed(vibrationRunnable6, updatedDelay4);

            long updatedDelay7 = Math.max(0, 10000 - (15 * 60 * 1000 - remainingTime));
            vibrationHandler7.postDelayed(vibrationRunnable7, updatedDelay5);
            // ... and so on for the rest of your vibration patterns
        }

        isPaused = false;
        playPauseButton.setImageResource(R.drawable.pause);
    }


    private void restartSession() {
        // If a countdown timer is running, cancel it.
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        // Stop and release media players to avoid leaks.
        mediaPlayer.stop();
        mediaPlayer.release();
        voiceMediaPlayer.stop();
        voiceMediaPlayer.release();

        // Get the selected voice again in this method, similar to onCreate
        String selectedVoice = getIntent().getStringExtra("selectedVoice");
        if (selectedVoice == null) selectedVoice = "Anna"; // Default value.

        // Initialize media players again with the correct music and voice files based on the selected voice.
        int musicResId; // Declare a variable to store the resource ID of the background music
        int voiceResId; // Declare a variable to store the resource ID of the voice
        switch (selectedVoice) {
            case "Paul":
                musicResId = R.raw.meditation_music_paul; // Assign the correct music file
                voiceResId = R.raw.paul_full; // Assign the correct voice file
                break;
            case "Claire":
                musicResId = R.raw.meditation_music_claire; // Assign the correct music file
                voiceResId = R.raw.claire_full; // Assign the correct voice file
                break;
            case "Anna":
            default:
                musicResId = R.raw.meditation_music_anna; // Assign the correct music file
                voiceResId = R.raw.anna_full; // Assign the correct voice file
                break;
        }
        mediaPlayer = MediaPlayer.create(this, musicResId); // Create the mediaPlayer with the correct music file
        mediaPlayer.setLooping(true);

        voiceMediaPlayer = MediaPlayer.create(this, voiceResId); // Create the voiceMediaPlayer with the correct voice file
        voiceMediaPlayer.setLooping(true);

        // Stop all running vibrations.
        stopAllVibrations();

        // Reset remaining time to 15 minutes.
        remainingTime = 15 * 60 * 1000;

        // Directly update the timerTextView to "15:00".
        timerTextView.setText("15:00");

        // Finally, start a new session.
        startSession();
    }


    private void startVibration(int type) {
        long[] pattern;
        int repeatIndex;  // to determine which part of the pattern to repeat
        switch (type) {
            case 1:
                pattern = new long[]{0, 1150, 400};
                repeatIndex = 1;  // Repeat the vibration indefinitely
                break;
            case 2:
                pattern = new long[]{0, 700, 3000};
                repeatIndex = 1;
                break;
            case 3:
                pattern = new long[]{0, 4000, 1000};
                repeatIndex = 1;  // only one vibration, repeat it
                break;
            case 4:
                pattern = new long[]{0, 2000};
                repeatIndex = 0;
                break;
            case 5:
                pattern = new long[]{0, 1150, 400};
                repeatIndex = 1;
                break;
            case 6:
                pattern = new long[]{0, 4000, 1000};
                repeatIndex = 1;
                break;
            case 7:
                pattern = new long[]{0, 2000};
                repeatIndex =0;
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
        vibrationHandler6.removeCallbacks(vibrationRunnable6);
        vibrationHandler7.removeCallbacks(vibrationRunnable7);

        vibrationHandler1.removeCallbacks(stopVibrationRunnable1);
        vibrationHandler2.removeCallbacks(stopVibrationRunnable2);
        vibrationHandler3.removeCallbacks(stopVibrationRunnable3);
        vibrationHandler4.removeCallbacks(stopVibrationRunnable4);
        vibrationHandler5.removeCallbacks(stopVibrationRunnable5);
        vibrationHandler6.removeCallbacks(stopVibrationRunnable6);
        vibrationHandler7.removeCallbacks(stopVibrationRunnable7);
    }


}

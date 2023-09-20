package com.example.painease;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HeadphonesActivity extends AppCompatActivity {

    private Button proceedWithoutHeadphonesButton;
    private String selectedPart;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (areHeadphonesConnected()) {
                navigateToVoiceSelectionActivity();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headphones);

        proceedWithoutHeadphonesButton = findViewById(R.id.proceedWithoutHeadphonesButton);

        // Receive the selected body part data from the PainLocationActivity
        selectedPart = getIntent().getStringExtra("selectedPart");

        proceedWithoutHeadphonesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToVoiceSelectionActivity();
            }
        });
    }

    private void navigateToVoiceSelectionActivity() {
        Intent intent = new Intent(HeadphonesActivity.this, VoiceSelectionActivity.class);
        intent.putExtra("selectedPart", selectedPart);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(receiver, filter);

        // Immediately check current headphone state when activity is resumed
        if (areHeadphonesConnected()) {
            navigateToVoiceSelectionActivity();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    private boolean areHeadphonesConnected() {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        AudioDeviceInfo[] audioDevices = audioManager.getDevices(AudioManager.GET_DEVICES_ALL);
        for (AudioDeviceInfo deviceInfo : audioDevices) {
            if (deviceInfo.getType() == AudioDeviceInfo.TYPE_WIRED_HEADPHONES ||
                    deviceInfo.getType() == AudioDeviceInfo.TYPE_WIRED_HEADSET ||
                    deviceInfo.getType() == AudioDeviceInfo.TYPE_BLUETOOTH_A2DP ||
                    deviceInfo.getType() == AudioDeviceInfo.TYPE_BLUETOOTH_SCO) {
                return true;
            }
        }
        return false;
    }
}

package com.example.painease;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;

public class SplashScreenActivity extends AppCompatActivity {

    private BroadcastReceiver downloadReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Register a BroadcastReceiver to listen for download completion
        registerDownloadReceiver();

        // Start downloading the files
        downloadAudioFile("paul_full.mp3");
        downloadAudioFile("claire_full.mp3");
        downloadAudioFile("anna_full.mp3");
        downloadAudioFile("meditation_music_paul.mp3");
        downloadAudioFile("meditation_music_claire.mp3");
        downloadAudioFile("meditation_music_anna.mp3");
    }

    private void downloadAudioFile(String fileName) {
        String audioUrl = "https://paineaseapp.com/audio/" + fileName;

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(audioUrl));
        request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS, fileName);

        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }

    private void registerDownloadReceiver() {
        downloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                // Check if the download is complete
                if (downloadId != -1) {
                    // Check if all downloads are complete
                    if (areAllDownloadsComplete()) {
                        // All downloads are complete, start PainTypeActivity
                        startActivity(new Intent(SplashScreenActivity.this, PainTypeActivity.class));
                        finish();
                    } else {
                        // Handle the case where not all downloads are complete
                        // You can show an error message or retry the downloads
                        // before allowing the user to proceed.
                        // For example, display a message and provide a button to retry.
                        showErrorAndRetry();
                    }
                }
            }
        };
        registerReceiver(downloadReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    private boolean areAllDownloadsComplete() {
        // Check if all expected files are downloaded
        File[] downloadedFiles = {
                new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "paul_full.mp3"),
                new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "claire_full.mp3"),
                new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "anna_full.mp3"),
                new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "meditation_music_paul.mp3"),
                new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "meditation_music_claire.mp3"),
                new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "meditation_music_anna.mp3")
        };

        for (File file : downloadedFiles) {
            if (!file.exists()) {
                // At least one file is not downloaded yet
                return false;
            }
        }

        // All files are downloaded
        return true;
    }

    private void showErrorAndRetry() {
        // Display an error message to the user and provide an option to retry the downloads.
        // You can implement this part based on your app's UI and user experience.
        // For example, show a dialog or a toast message with a retry button.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downloadReceiver != null) {
            unregisterReceiver(downloadReceiver);
        }
    }
}

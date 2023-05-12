package com.tbcmad.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView percentageTextView;
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        progressBar = findViewById(R.id.ProgressBar);
        percentageTextView = findViewById(R.id.loadingPercentTextView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateProgress();
            }
        }, 1000);
    }

    private void updateProgress() {
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;

                    // Update the progress bar and percentage text on the main thread
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            percentageTextView.setText(String.valueOf(progressStatus) + "%");

                            // When progress reaches 100, navigate to the appropriate activity
                            if (progressStatus == 100) {
                                navigateToActivity();
                            }
                        }
                    });

                    try {
                        // Delay for a short period to simulate progress
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void navigateToActivity() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("patinetpref", 0);
        boolean authentication = preferences.getBoolean("authentication", false);

        Intent intent;
        if (authentication) {
            intent = new Intent(SplashActivity.this, MainActivity.class);
        } else {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        }

        startActivity(intent);
        finish();
    }
}

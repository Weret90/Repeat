package com.demo.repeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean isWorking = false;
    private boolean wasWorking = false;
    private TextView textViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTimer = findViewById(R.id.textViewTimer);
        if (savedInstanceState != null) {
            isWorking = savedInstanceState.getBoolean("isWorking");
            wasWorking = savedInstanceState.getBoolean("wasWorking");
            seconds = savedInstanceState.getInt("seconds");
        }
        runTimer();
    }

    public void onClickStartTimer(View view) {
        isWorking = true;
    }

    public void onClickPauseTimer(View view) {
        isWorking = false;
    }

    public void onClickRestartTimer(View view) {
        isWorking = false;
        seconds = 0;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("isWorking", isWorking);
        outState.putBoolean("wasWorking", wasWorking);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasWorking = isWorking;
        isWorking = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isWorking = wasWorking;
    }

    public void runTimer() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = seconds % 3600 / 60;
                int leftSec = seconds % 60;

                String timer = String.format("%02d:%02d:%02d", hours, minutes, leftSec);
                textViewTimer.setText(timer);

                if (isWorking) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
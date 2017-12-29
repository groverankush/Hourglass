package com.ankushgrover.android_hourglass;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ankushgrover.hourglass.Hourglass;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView text;
    private Hourglass timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text = findViewById(R.id.text);


        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
        findViewById(R.id.resume).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);


        timer = new Hourglass() {
            @Override
            public void onTimerTick(final long timeRemaining) {
                text.setText(String.valueOf(timeRemaining));
            }

            @Override
            public void onTimerFinish() {
                text.setText("00");

                setEnabled(R.id.time, true);
                setEnabled(R.id.interval, true);
                setVisibility(R.id.start, true);
                setVisibility(R.id.stop, false);
                setVisibility(R.id.pause, false);
                setVisibility(R.id.resume, false);

                Toast.makeText(MainActivity.this, "onTimerFinish", Toast.LENGTH_SHORT).show();

            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:

                EditText time = findViewById(R.id.time);
                EditText interval = findViewById(R.id.interval);

                if (time.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Time cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                timer.setTime(Integer.valueOf(((EditText) findViewById(R.id.time)).getText().toString()));
                timer.setInterval(interval.getText().toString().isEmpty() ? 1000 : Integer.valueOf(interval.getText().toString()));
                timer.startTimer();

                setEnabled(R.id.time, false);
                setEnabled(R.id.interval, false);
                setVisibility(R.id.start, false);
                setVisibility(R.id.stop, true);
                setVisibility(R.id.pause, true);
                setVisibility(R.id.resume, false);

                break;
            case R.id.stop:

                timer.stopTimer();
                setEnabled(R.id.time, true);
                setEnabled(R.id.interval, true);
                setVisibility(R.id.start, true);
                setVisibility(R.id.stop, false);
                setVisibility(R.id.pause, false);
                setVisibility(R.id.resume, false);

                break;
            case R.id.resume:

                timer.resumeTimer();

                setVisibility(R.id.start, false);
                setVisibility(R.id.stop, true);
                setVisibility(R.id.pause, true);
                setVisibility(R.id.resume, false);

                break;
            case R.id.pause:

                timer.pauseTimer();

                setVisibility(R.id.start, false);
                setVisibility(R.id.stop, true);
                setVisibility(R.id.pause, false);
                setVisibility(R.id.resume, true);

                break;
        }
    }

    private void setEnabled(@IdRes int id, boolean isEnabled) {
        findViewById(id).setEnabled(isEnabled);
    }

    private void setVisibility(@IdRes int id, boolean isVisible) {
        findViewById(id).setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}

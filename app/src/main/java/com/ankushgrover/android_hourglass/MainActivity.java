package com.ankushgrover.android_hourglass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ankushgrover.hourglass.HourGlass;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new HourGlass(10000) {
            @Override
            public void onTimerTick(final long timeRemaining) {


                Log.d("Timer", timeRemaining + "");
                Toast.makeText(MainActivity.this, timeRemaining + "", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onTimerFinish() {
                Log.d("Timer", "Finish                                             ");

            }
        };


    }
}

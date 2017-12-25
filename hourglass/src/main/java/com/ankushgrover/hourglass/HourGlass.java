package com.ankushgrover.hourglass;

import android.os.Handler;
import android.os.Looper;


/**
 * Created by Ankush Grover(ankush.dev2@gmail.com) on 21/12/17.
 */


public abstract class HourGlass extends Thread implements TimerContract.TimerTick {

    private final Handler handler;
    /**
     * To maintain Timer start and stop status.
     */
    private boolean isRunning;
    /**
     * To maintain Timer resume and pause status.
     */
    private boolean isPaused;

    /**
     * Timer time.
     */
    private long time;
    private long localTime;

    public HourGlass(long time) {


        this.time = time;
        this.handler = new Handler(Looper.getMainLooper());
        startTimer();

    }

    @Override
    public void run() {
        super.run();

        this.isRunning = true;
         localTime = 0;

        while (!isInterrupted() && localTime < time) {


            if (!isPaused){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onTimerTick(time - localTime);                    }
                });
            }


            try {
                Thread.sleep(1000);
                if (!isPaused)
                    localTime += 1000;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }



        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                onTimerFinish();
            }
        });

        this.isRunning = false;


    }

    /**
     * Method to check whether the timer is running or not
     *
     * @return
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Method to start the timer.
     */
    public void startTimer() {
        if (!this.isRunning) {
            start();
        }
    }

    /**
     * Method to stop the timer.
     */
    public void stopTimer() {
        if (!this.isRunning) {
            interrupt();
        }
    }

    /**
     * Method to check whether the timer is paused.
     *
     * @return
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * To pause the timer from Main thread.
     *
     * @param isPaused
     */
    public synchronized void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    /**
     * Setter for Time.
     *
     * @param time
     */
    public void setTime(long time) {
        if (this.time <= 0)
            if (time < 0)
                time *= -1;
        this.time = time;
    }
}

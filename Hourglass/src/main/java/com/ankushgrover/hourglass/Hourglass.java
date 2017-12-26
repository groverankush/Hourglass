package com.ankushgrover.hourglass;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;


/**
 * Created by Ankush Grover(ankush.dev2@gmail.com) on 21/12/17.
 */


public abstract class Hourglass extends Thread implements TimerContract.TimerTick {

    private static final int INTERVAL = 1000;

    private Handler handler;

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
    private long interval;

    public Hourglass() {
        init(0, INTERVAL);
    }

    public Hourglass(long time) {
        init(time, INTERVAL);
    }

    public Hourglass(long time, long interval) {
        init(time, interval);
    }

    /**
     * Method to initialize HourGlass.
     *
     * @param time
     * @param interval
     */
    private void init(long time, long interval) {
        setTime(time);
        setInterval(interval);

        this.handler = new Handler(Looper.getMainLooper());
    }


    @Override
    public void run() {
        super.run();

        this.isRunning = true;
        this.isPaused = false;
        localTime = 0;

        while (!isInterrupted() && localTime < time) {


            if (!isPaused) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onTimerTick(time - localTime);
                    }
                });
            }


            try {
                Log.d("Interval", interval + "");
                Thread.sleep(interval);
                if (!isPaused)
                    localTime += interval;
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
     * To stop the timer from Main thread.
     *
     * @param isRunning
     */
    private synchronized void setRunning(boolean isRunning) {
        this.isRunning = isRunning;

        if (this.isRunning) {
            start();
        }

        if (!this.isRunning) {
            interrupt();
        }
    }

    /**
     * Method to start the timer.
     */
    public void startTimer() {

        setRunning(true);
    }

    /**
     * Method to stop the timer.
     */
    public void stopTimer() {
        setRunning(false);
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
    private synchronized void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    /**
     * Convenience method to pause the timer.
     */
    public synchronized void pauseTimer() {
        setPaused(true);
    }

    /**
     * Convenience method to resume the timer.
     */
    public synchronized void resumeTimer() {
        setPaused(false);
    }

    /**
     * Setter for Time.
     *
     * @param time
     */
    public void setTime(long time) {
        if (isRunning)
            return;

        if (this.time <= 0)
            if (time < 0)
                time *= -1;
        this.time = time;
    }

    /**
     * Setter for interval.
     *
     * @param interval
     */
    public void setInterval(long interval) {
        if (isRunning)
            return;

        if (this.interval <= 0)
            if (interval < 0)
                interval *= -1;
        this.interval = interval;
    }
}

package com.ankushgrover.hourglass;

/**
 * Created by Ankush Grover(ankush.grover@finoit.co.in) on 21/12/17.
 */


public class TimerThread extends Thread {

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


    private TimerContract.TimerTick link;

    public TimerThread(long time,  TimerContract.TimerTick link) {

        this.link = link;

    }

    @Override
    public void run() {
        super.run();

        this.isRunning = true;
        long localTime = 0;

        while (!isInterrupted() && localTime < time) {


            if (link != null && !isPaused)
                link.onTimerTick(time - localTime);

            try {
                Thread.sleep(1000);
                if (!isPaused)
                    localTime += 1000;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }


        }
        link.onTimerFinish();
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
        if (this.time == 0)
            if (time < 0)
                time *= -1;
        this.time = time;
    }
}

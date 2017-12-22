package com.ankushgrover.hourglass;


/**
 * Created by Ankush Grover(ankush.grover@finoit.co.in) on 21/12/17.
 */

public interface TimerContract {


    interface TimerTick {

        /**
         * Method to be called every second by the {@link TimerThread}
         *
         * @param timeRemaining
         */
        void onTimerTick(long timeRemaining);

        /**
         * Method to be called by {@link TimerThread} when the thread is getting  finished
         */
        void onTimerFinish();


    }


}

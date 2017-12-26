package com.ankushgrover.hourglass;


/**
 * Created by Ankush Grover(ankush.dev2@gmail.com) on 21/12/17.
 */

public interface TimerContract {


    interface TimerTick {

        /**
         * Method to be called every second by the {@link Hourglass}
         *
         * @param timeRemaining
         */
        void onTimerTick(long timeRemaining);

        /**
         * Method to be called by {@link Hourglass} when the thread is getting  finished
         */
        void onTimerFinish();


    }


}

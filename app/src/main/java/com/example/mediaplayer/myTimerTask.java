package com.example.mediaplayer;

import java.util.TimerTask;

public class myTimerTask {

    /**
     * @param currentDuration
     * @param totalDuration
     * @return
     */
    public static int getProgressPercentage(long currentDuration,
                                            long totalDuration) {
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        // return percentage
        return percentage.intValue();
    }


}

package com.example.mediaplayer.Timer;

import com.example.mediaplayer.Fragments.PlayingFragment;
import com.example.mediaplayer.MusicController;
import com.example.mediaplayer.ViewManager;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("ALL")
public class TimerManager {
    private static final TimerManager ourInstance = new TimerManager();
    private static Timer mTimer;
    private static TimerTask mTimer10ms;
    private static TimerTask mTimer1s;

    public static TimerManager getInstance() {
        return ourInstance;
    }

    private TimerManager() {
        mTimer = new Timer();
    }

    public static void startTimer10ms() {
        TimerTask timerTask10Ms = TimerTask10ms.getTimerTask();
        mTimer.schedule(timerTask10Ms, 0, 10);
    }

    public static void startTimer1s() {
        TimerTask timerTask1s = TimerTask1s.getTimerTask();
        mTimer.schedule(timerTask1s, 0, 1000);
    }

    public static void stopTimer() {
        mTimer.cancel();
        mTimer = new Timer();
        // mTimer.purge();
    }
}

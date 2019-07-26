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
        mTimer10ms = new TimerTask() {
            @Override
            public void run() {
                //    TODO:10msTimerToDo
            }
        };
        mTimer1s = new TimerTask() {
            @Override
            public void run() {
                //    TODO:1sTimerToDo
                int position = MusicController.getCurrentPostrion();
                ViewManager.setSeekBarCurrentProgress(position);
            }
        };
    }

    public static void startTimer10ms() {
        mTimer.schedule(mTimer10ms, 0, 10);
    }

    public static void startTimer1s() {
        mTimer.schedule(mTimer1s, 0, 1000);
    }

    public static void stopTimer() {
        mTimer.cancel();
        mTimer.purge();
    }
}

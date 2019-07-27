package com.example.mediaplayer.Timer;

import android.util.Log;

import com.example.mediaplayer.MusicController;
import com.example.mediaplayer.ViewManager;

import java.util.TimerTask;

public class TimerTask1s extends TimerTask {


    public static TimerTask getTimerTask() {
        TimerTask timerTask = new TimerTask1s();
        return timerTask;
    }

    public void TimerTask1s() {
    }

    @Override
    public void run() {
        //    TODO:1sTimerToDo
        int position = MusicController.getCurrentPostrion();
        ViewManager.setSeekBarCurrentProgress(position);
        Log.d("Timer", "运行了一次，当前位置为"+position);
    }
}

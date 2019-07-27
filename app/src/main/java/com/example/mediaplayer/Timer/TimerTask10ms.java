package com.example.mediaplayer.Timer;

import com.example.mediaplayer.MusicController;
import com.example.mediaplayer.ViewManager;

import java.util.TimerTask;

public class TimerTask10ms extends TimerTask {


    public static TimerTask getTimerTask() {
        TimerTask timerTask = new TimerTask10ms();
        return timerTask;
    }

    public void TimerTask10ms() {
    }

    @Override
    public void run() {
        //    TODO:10ms Timer ToDo
        // int position = MusicController.getCurrentPostrion();
        // ViewManager.setSeekBarCurrentProgress(position);
    }
}

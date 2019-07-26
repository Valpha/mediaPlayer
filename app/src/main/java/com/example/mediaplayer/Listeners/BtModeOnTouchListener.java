package com.example.mediaplayer.Listeners;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.mediaplayer.PlayMode;
import com.example.mediaplayer.Utils;
import com.example.mediaplayer.ViewManager;

public class BtModeOnTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                PlayMode playMode = Utils.getPlayingMode();
                switch (playMode) {
                    case SEQUENCE:
                        ViewManager.setBtModeSequenceDown();
                        Log.d("mode", "SequenceDown");
                        break;
                    case SHUFFLE:
                        ViewManager.setBtModeShuffleDown();
                        Log.d("mode", "ShuffleDown");
                        break;
                    case SINGLE:
                        ViewManager.setBtModeSingleDown();
                        Log.d("mode", "SingleDown");
                        break;
                    default:
                        break;
                }
                return false;
            case MotionEvent.ACTION_UP:
                playMode = Utils.getPlayingMode();
                switch (playMode) {
                    case SEQUENCE:
                        ViewManager.setBtModeShuffleUp();
                        Log.d("mode", "SequenceUp");
                        Utils.setPlayingMode(PlayMode.SHUFFLE);
                        break;
                    case SHUFFLE:
                        ViewManager.setBtModeSingleUp();
                        Log.d("mode", "ShuffleUp");
                        Utils.setPlayingMode(PlayMode.SINGLE);
                        break;
                    case SINGLE:
                        ViewManager.setBtModeSequenceUp();
                        Log.d("mode", "SingleUp");
                        Utils.setPlayingMode(PlayMode.SEQUENCE);
                        break;
                    default:
                        break;
                }
                return true;
            default:
                return false;
        }
    }
}

package com.example.mediaplayer.Listeners;

import android.media.MediaPlayer;
import android.util.Log;

public class MyOnCompletionListener implements MediaPlayer.OnCompletionListener {

    private static final String TAG = "MyOnCompletionListener";

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onCompletion: Playing Finished");
    }
}

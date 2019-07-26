package com.example.mediaplayer.Listeners;

import android.util.Log;
import android.widget.SeekBar;

import com.example.mediaplayer.AudioPlayer;

public class MySeekBarSeekToListener implements SeekBar.OnSeekBarChangeListener {
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            AudioPlayer audioPlayer = AudioPlayer.getInstance();
            audioPlayer.setMediaPlayerSeekTo(i);
            Log.d("MusicController", "当前拖拽进度为"+i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
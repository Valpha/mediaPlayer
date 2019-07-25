package com.example.mediaplayer.Listeners;

import android.widget.SeekBar;

import com.example.mediaplayer.AudioPlayer;

public class MySeekBarSeekToListener implements SeekBar.OnSeekBarChangeListener {
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            AudioPlayer audioPlayer = AudioPlayer.getInstance();
            audioPlayer.setMediaPlayerSeekTo(i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
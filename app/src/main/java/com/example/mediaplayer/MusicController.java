package com.example.mediaplayer;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;

import com.example.mediaplayer.Fragments.PlayingFragment;
import com.example.mediaplayer.Timer.TimerManager;

import java.io.IOException;

/**
 * 在这里写入对播放器的控制的方法
 */
public class MusicController {

    public static PlayMode getPlayMode(){
        return Utils.getPlayingMode();
    }
    public static void setPlayMode(PlayMode playMode){
        Utils.setPlayingMode(playMode);
    }
    public static void changeSong() throws IOException {
        stopPlaying();
        Song song = PlayList.getCurrentSong();
        AudioPlayer audioPlayer = AudioPlayer.getInstance();

        audioPlayer.setMediaPlayerReset();
        audioPlayer.setMediaPlayerDataSource(song.getSsrc());
        audioPlayer.setMediaPlayerPrepare();
        PlayingFragment.getInstance().curadapter.notifyDataSetChanged();
        ViewManager.setIvCovers(song);
        ViewManager.setTvTitleText(song.getTitle());
        ViewManager.setTvArtistText(song.getSinger());
        ViewManager.setTvWriterText(song.getWriter());
        ViewManager.setTvComposerText(song.getZuoqu());
        ViewManager.setLvLyric(song.getLyric());
        if (song.getFavorite()){
            ViewManager.setBtFavoriteTrue();
        }else {
            ViewManager.setBtFavoriteFalse();
        }
        ViewManager.setSbProgressMax(audioPlayer.getMediaPlayerDuration());
        Log.d("MusicController", "当前设置SeekBar总长为"+audioPlayer.getMediaPlayerDuration());
    }

    public static void startPlaying() {
        AudioPlayer audioPlayer = AudioPlayer.getInstance();
        audioPlayer.setMediaPlayerStart();

        Utils.writeStatusPlay();

        ViewManager.setBtPlayingImageToPause();
        ViewManager.setIvAnimationStart();

        TimerManager.startTimer1s();
    }

    public static void stopPlaying() {
        AudioPlayer audioPlayer = AudioPlayer.getInstance();
        audioPlayer.setMediaPlayerStop();

        Utils.writeStatusStop();
        ViewManager.setSeekBarMoveToStart();
        ViewManager.setBtPlayingImageToPlaying();
        ViewManager.setIvAnimationStop();
        TimerManager.stopTimer();
    }

    public static void pausePlaying() {
        AudioPlayer audioPlayer = AudioPlayer.getInstance();
        audioPlayer.setMediaPlayerPause();

        Utils.writeStatusStop();
        ViewManager.setBtPlayingImageToPlaying();
        ViewManager.setIvAnimationStop();
        TimerManager.stopTimer();
    }

    public static void setCompletionListener(MediaPlayer.OnCompletionListener listener) {
        AudioPlayer audioPlayer = AudioPlayer.getInstance();
        audioPlayer.setMediaPlayerPlayCompletionListener(listener);
    }

    public static int getCurrentPostrion() {
        AudioPlayer audioPlayer = AudioPlayer.getInstance();
        return audioPlayer.getMediaPlayerPosition();
    }

    // public void updateCurrentPlayingList(){
    //     int position = i + 1;
    //     mtvCount.setText(position + "/20");
    //     curltosong(curSelect);
    // }

}

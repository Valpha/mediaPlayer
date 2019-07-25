package com.example.mediaplayer;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 *
 */
public class AudioPlayer {
    private MediaPlayer mediaPlayer;
    private static AudioPlayer audioPlayer = new AudioPlayer();

    /**
     * initial a MediaPlayer instance
     */
    private AudioPlayer(){
        mediaPlayer = new MediaPlayer();
    }

    /**
     * @return AudioPlayerInstance
     *
     * use audioPlayer.xxx to use mediaPlayer
     */
    public static AudioPlayer getInstance(){
        return audioPlayer;
    }

    /**
     * reser mediaPlayer
     */
    public void setMediaPlayerReset(){
        mediaPlayer.reset();
    }

    /**
     * @param path set source path
     * @throws IOException if file not exist
     */
    public void setMediaPlayerDataSource(String path) throws IOException {
        mediaPlayer.setDataSource(path);
    }

    /**
     * @throws IOException anything happen
     */
     public void setMediaPlayerPrepare() throws IOException {
        mediaPlayer.prepare();
     }

    /**
     * @param progress progress in percent
     */
     public void setMediaPlayerSeekTo(int progress){
         int duration = mediaPlayer.getDuration();
         int curPos = duration * progress;
         mediaPlayer.seekTo(curPos);
     }

    /**
     * start playing
     */
     public void setMediaPlayerStart(){
        mediaPlayer.start();
     }

    /**
     * pause playing
     */
     public void setMediaPlayerPause(){
        mediaPlayer.pause();
     }

    /**
     * stop
     */
     public void setMediaPlayerStop(){
        mediaPlayer.stop();
     }

    /**
     * @param listener MediaPlayer.OnCompletionListener
     */
     public void setMediaPlayerPlayCompletionListener(MediaPlayer.OnCompletionListener listener){
         mediaPlayer.setOnCompletionListener(listener);
     }

    /**
     * @return Status Boolean Playing
     */
     public boolean getMediaPlayerIsPlaying(){
        return mediaPlayer.isPlaying();
     }
}

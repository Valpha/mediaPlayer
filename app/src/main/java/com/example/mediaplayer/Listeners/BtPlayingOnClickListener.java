package com.example.mediaplayer.Listeners;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mediaplayer.Fragments.PlayingFragment;
import com.example.mediaplayer.MusicController;
import com.example.mediaplayer.PlayList;
import com.example.mediaplayer.R;
import com.example.mediaplayer.Timer.TimerManager;
import com.example.mediaplayer.Utils;
import com.example.mediaplayer.ViewManager;

import java.util.Timer;
import java.util.TimerTask;

public class BtPlayingOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {

        int order = PlayList.getCurrentOrder();
        boolean status = Utils.getPlayingStatus();
        if (order < 0) {
            Toast.makeText(PlayingFragment.getInstance().getActivity(), "抱歉当前没有选中的歌曲哦", Toast.LENGTH_SHORT).show();
        } else if (status) {
            ViewManager.setIvAnimationStop();
            MusicController.pausePlaying();
        } else {
            ViewManager.setIvAnimationStart();
            MusicController.startPlaying();
            // TimerManager.startTimer();定时器在MusicController中使用了
        }
    }
}
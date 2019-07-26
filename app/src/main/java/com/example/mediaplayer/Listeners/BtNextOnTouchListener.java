package com.example.mediaplayer.Listeners;

import android.view.MotionEvent;
import android.view.View;

import com.example.mediaplayer.MusicController;
import com.example.mediaplayer.PlayList;
import com.example.mediaplayer.ViewManager;

import java.io.IOException;

public class BtNextOnTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ViewManager.setBtNextDown();
                return true;

            case MotionEvent.ACTION_UP:
                int order = PlayList.getCurrentOrder();
                PlayList.setSongOrder(order + 1);
                try {
                    MusicController.changeSong();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MusicController.startPlaying();
                ViewManager.setBtNextUp();
                return true;
            default:
                return false;

        }
    }
}
    /*{*/
    /*    @Override*/
    /*    public boolean onTouch (View view, MotionEvent event){*/
    /*    if (event.getAction() == MotionEvent.ACTION_DOWN) {*/
    /*        mp.reset();*/
    /*        int k = 19;*/
    /*        if (songOrder >= k) {*/
    /*            songOrder = -1;*/
    /*        }*/
    /*        musicStatusChange(songs.get(++songOrder).getSsrc());*/
    /*        ii = 1;*/
    /*        mbtPlaying.setImageDrawable(getResources().getDrawable(R.drawable.pause));*/
    /*        mp.start();*/
    /*        ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.next2));*/
    /*        // curadapter.changeSelect(songOrder);*/
    /*        changeView(songOrder);*/
    /*    } else if (event.getAction() == MotionEvent.ACTION_UP) {*/
    /*        ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.next1));*/
    /*    }*/
    /*    return false;*/
    /*}*/
    /*}*/
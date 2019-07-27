package com.example.mediaplayer.Listeners;

import android.view.MotionEvent;
import android.view.View;

import com.example.mediaplayer.MusicController;
import com.example.mediaplayer.PlayList;
import com.example.mediaplayer.ViewManager;

import java.io.IOException;

public class BtPrevOnTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ViewManager.setBtPrevDown();
                return true;

            case MotionEvent.ACTION_UP:
                int order = PlayList.getCurrentOrder();
                PlayList.setSongOrder(order - 1);
                try {
                    MusicController.changeSong();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                 MusicController.startPlaying();
                ViewManager.setBtPrevUp();
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
    /*        ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.lastbt_two));*/

    /*        mp.reset();*/
    /*        if (songOrder < 1) {*/
    /*            songOrder = 20;*/
    /*        }*/
    /*        ii = 1;*/
    /*        mbtPlaying.setImageDrawable(getResources().getDrawable(R.drawable.pause));*/
    /*        musicStatusChange(songs.get(--songOrder).getSsrc());*/
    /*        mp.start();*/
    /*        // curadapter.changeSelect(songOrder);*/
//                  歌词同步的封装
    /*        changeView(songOrder);*/

    /*    } else if (event.getAction() == MotionEvent.ACTION_UP) {*/
    /*        ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.lastbt_com));*/

    /*    }*/
    /*    return false;*/
    /*}*/
    /*}*/

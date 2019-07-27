package com.example.mediaplayer.Listeners;

import android.view.View;

import com.example.mediaplayer.PlayList;
import com.example.mediaplayer.Utils;
import com.example.mediaplayer.ViewManager;

public class BtFavoriteOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        int position = PlayList.getCurrentOrder();
        Boolean favorate = PlayList.getSongFavorate(position);
        if(favorate)
        {
            ViewManager.setBtFavoriteFalse();
            PlayList.setCurrentSongFavorateFalse(position);
        }
        else
        {
            ViewManager.setBtFavoriteTrue();
            PlayList.setCurrentSongFavorateTrue(position);
        }

    }


}
package com.example.mediaplayer;

import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class PlayList {
    private static final PlayList ourInstance = new PlayList();

    public static List<Song> currentList;
    public static int currentOrder = -1;
    public static PlayList getInstance() {
        return ourInstance;
    }

    private PlayList() {
        currentOrder = Utils.getCurrentOrder();
    }

    public static void setSongOrder(int songOrder) {
        if (songOrder < 0) {
            currentOrder = currentList.size() + songOrder;
        } else if (songOrder >= currentList.size()) {
            currentOrder = songOrder - currentList.size();
        } else {
            currentOrder = songOrder;
        }
        Utils.writeCurrentOrder(currentOrder);
        ViewManager.setTvCount((currentOrder+1)+"/20");;
        if(currentList.get(currentOrder).getFavorite())
        {
            ViewManager.setBtFavoriteTrue();
        }

        Log.d("Utils", "写入songOrder"+currentOrder);
    }

    public  static  void setCurrentSongFavorateTrue(int currentOrder){
        currentList.get(currentOrder).setFavorite(true);
    }
    public  static  void setCurrentSongFavorateFalse(int currentOrder){
        currentList.get(currentOrder).setFavorite(false);
    }
    public static Song getCurrentSong() {
        return currentList.get(currentOrder);
    }

    public static int getCurrentOrder() {
        return currentOrder;
    }

    public void loadCurrentList(List<Song> songs) {
        currentList = songs;
    }

    public static Song getSongByOrder(int songOrder) {
        return currentList.get(songOrder);
    }

    public  static Boolean getSongFavorate(int songOrder){
        return currentList.get(songOrder).getFavorite();

    }

}

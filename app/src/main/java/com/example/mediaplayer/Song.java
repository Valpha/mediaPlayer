package com.example.mediaplayer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */

public class Song {
    private String title;
    private String singer;
    private String[] objectView;
    private String writer;
    private String zuoqu;
    private String ssrc;
    private String picpath;
    private Bitmap cover;

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    private HashMap<String, String> lyricList = new HashMap<>(50);
    private boolean favorite;

    /**
     * @param title
     * @param singer
     * @param objectView
     * @param writer
     * @param zuoqu
     * @param songsrc
     * @param picpath
     * @param lyricPath
     */
    public Song(String title, String singer, String[] objectView, String writer, String zuoqu, String songsrc, String picpath, String lyricPath) {
        this.title = title;
        this.singer = singer;
        this.objectView = objectView;
        this.zuoqu = zuoqu;
        this.writer = writer;
        this.ssrc = songsrc;
        this.picpath = picpath;
        this.cover = BitmapFactory.decodeFile(picpath);
        // TODO:Implement this method
        // this.lyricList = getLyricFromLyricPath(lyricPath);

        this.favorite = false;
    }

    private List<HashMap<String, String>> getLyricFromLyricPath(String lyricPath) {
        // TODO:Implement this method
        List<HashMap<String, String>> lrcList = null;
        /*在这里解析歌词文件，解析成哈希表的数据类型并返回*/
        return lrcList;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.cover = BitmapFactory.decodeFile(picpath);
    }

    public String getSsrc() {
        return ssrc;
    }

    public void setSsrc(String songsrc) {
        this.ssrc = songsrc;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getZuoqu() {
        return zuoqu;
    }

    public void setZuoqu(String zuoqu) {
        this.zuoqu = zuoqu;
    }

    public String[] getObjectView() {
        return objectView;
    }

    public void setObjectView(String[] objectView) {
        for (int i=0;i<objectView.length;i++){
            lyricList.put(String.valueOf(i), objectView[i]);
        }
        this.objectView = objectView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getObjectViewLength() {
        int len = objectView.length;
        return len;
    }

    public Bitmap getCover() {
        return cover;

    }

    public HashMap<String, String> getLyric() {
        return lyricList;
    }


    public boolean getFavorite() {
        return favorite;
    }


}

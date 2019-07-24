package com.example.mediaplayer;

public class Song {
    private String title;
    private  String singer ;
    private  String[] objectView;
    public Song(String title, String singer,String[] objectView) {
        this.title = title;
        this.singer = singer;
        this.objectView = objectView;
    }

    public String[] getObjectView() {
        return objectView;
    }
    public void setObjectView(String[] objectView) {
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
    public  int getObjectViewLength(){
        int len = objectView.length;
        return len;
    }
}

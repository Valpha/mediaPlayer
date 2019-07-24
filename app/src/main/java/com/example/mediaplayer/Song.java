package com.example.mediaplayer;

public class Song {
    private String title;
    private  String singer ;
    private  String[] objectView;
    private  String writer;
    private  String zuoqu;

    public Song(String title, String singer,String[] objectView, String writer, String zuoqu) {
        this.title = title;
        this.singer = singer;
        this.objectView = objectView;
        this.zuoqu=zuoqu;
        this.writer=writer;
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

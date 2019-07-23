package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Layout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mLeftLayout;
    private LinearLayout mRightLayout;
//    private String title;
//    private String singer;
//    public Song song = new Song(title,singer);
//    private List<Song> songs = new ArrayList<Song>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        扫描磁盘获取song
//        scanDisk();
        mLeftLayout =  findViewById(R.id.fg_leftlayout);
        mRightLayout =  findViewById(R.id.fg_rightlayout);
      }
//
//    private List<Song> scanDisk() {
//
//        song.setSinger("周杰伦");
//        song.setTitle("世界末日");
//        songs.add(song);
//        song.setSinger("周杰伦");
//        song.setTitle("世界末日2");
//        songs.add(song);
//        song.setSinger("周杰伦");
//        song.setTitle("世界末日3");
//        songs.add(song);
////        List<String> filepaths = new ArrayList<String>();
////        for (int i =0 ;i<= filepaths.size();i++) {
////            String title = Utils.getTitle(filepath);
////            String singer = Utils.getTsinger(filepath);
////            Song song = new Song(title, singer);
////            songs.add(song);
////        }
//
//        return songs;
//    }
}

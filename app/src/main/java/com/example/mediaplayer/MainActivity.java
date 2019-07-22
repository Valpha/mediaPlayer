package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;
/**
 * @author shizhuoxin
 */
public class MainActivity extends AppCompatActivity {

    private FrameLayout mfgcurlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mfgcurlist= (FrameLayout)findViewById(R.id.fg_curlist);

        CurlistFragment curlistFragment = CurlistFragment.newInstance();
//        开启事务
        FragmentManager sfm = getSupportFragmentManager();
        FragmentTransaction ts =  sfm.beginTransaction();
        ts.replace(R.id.fg_curlist,curlistFragment);
        ts.commit();

    }
}

package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Layout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mLeftLayout;
    private LinearLayout mRightLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeftLayout =  findViewById(R.id.fg_leftlayout);
        mRightLayout =  findViewById(R.id.fg_rightlayout);

//        //动态创建Fragment并且加载Fragment到容器中
//        LeftFragment leftfragment = LeftFragment.newInstance();
//        RightFragment rightfragment = RightFragment.newInstance();
//        leftfragment.setOnChoiceChangedLisenter(new LeftFragment.OnChoiceChangedLisenter() {
//            @Override
//            public void onChoiceChanged(int index) {
//                Toast.makeText(MainActivity.this, index+"", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        //开启事务，动态加载fragment到容器
//        FragmentManager sfm = getSupportFragmentManager();
//        FragmentTransaction ts = sfm.beginTransaction();
//
//        //把leftfragment加载到fg_leftlayout容器中去
//        ts.replace(R.id.fg_leftlayout,leftfragment);
//        //第三个参数代表framgent的TAG
//
//        ts.replace(R.id.fg_rightlayout, rightfragment, "rightfragment");
//        //事务需要提交
//        ts.commit();
//
      }
}

package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Layout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author ycn
 * 继承于AppCompatActivity的新方法
 */
public class MainActivity extends AppCompatActivity {
    private FrameLayout mLeftLayout;
    private FrameLayout mRightLayout;
    private RightFragment rightfragment;
    private LeftFragment leftfragment;
    private Right1Fragment rightfragment1;
    private Right2Fragment rightfragment2;
    private Right3Fragment rightfragment3;
    private Right4Fragment rightfragment4;
    private Right5Fragment rightfragment5;
    //private Fragment right1fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mLeftLayout = findViewById(R.id.fg_leftlayout);
//        mRightLayout = findViewById(R.id.fg_rightlayout);
        //动态创建Fragment并且加载Fragment到容器中
        leftfragment = LeftFragment.newInstance();
        rightfragment = RightFragment.newInstance();
        rightfragment1 = Right1Fragment.newInstance();
        rightfragment2 = Right2Fragment.newInstance();
        rightfragment3 = Right3Fragment.newInstance();
        rightfragment4 = Right4Fragment.newInstance();
        rightfragment5 = Right5Fragment.newInstance();


        //Right1Fragment right1fragment = Right1Fragment.newInstance();
        leftfragment.setOnChoiceChangedLisenter(new LeftFragment.OnChoiceChangedLisenter() {
            @Override
            public void onChoiceChanged(int index) {
                Toast.makeText(MainActivity.this, index + "改变选择了", Toast.LENGTH_LONG).show();
                changeFragment(index);
            }
        });

        loadFragment(R.id.fg_leftlayout,leftfragment);
        loadFragment(R.id.fg_rightlayout,rightfragment);


/*        //开启事务，动态加载fragment到容器
        FragmentManager sfm = getSupportFragmentManager();
        FragmentTransaction ts = sfm.beginTransaction();

        //把leftfragment加载到fg_leftlayout容器中去
        ts.replace(R.id.fg_leftlayout, leftfragment);
        //第三个参数代表framgent的TAG

        ts.replace(R.id.fg_rightlayout, rightfragment, "rightfragment");

        //事务需要提交
        ts.commit();*/
    }

    private void loadFragment(int containerViewId, Fragment fragment) {
        FragmentManager sfm = getSupportFragmentManager();
        FragmentTransaction ts = sfm.beginTransaction();
        ts.replace(containerViewId, fragment);
        ts.commit();
        return;
    }

    private void changeFragment(int index) {
        switch (index) {
            case 0:
                loadFragment(R.id.fg_rightlayout, rightfragment);
                break;
            case 1:
                loadFragment(R.id.fg_rightlayout, rightfragment1);
                break;
            case 2:
                loadFragment(R.id.fg_rightlayout, rightfragment2);
                break;
            case 3:
                loadFragment(R.id.fg_rightlayout, rightfragment3);
                break;
            case 4:
                loadFragment(R.id.fg_rightlayout, rightfragment4);
                break;
            case 5:
                loadFragment(R.id.fg_rightlayout, rightfragment5);
                break;
            default:

                break;

        }


    }


}

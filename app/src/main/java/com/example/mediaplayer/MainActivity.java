package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.mediaplayer.Fragments.NaviBarFragment;
import com.example.mediaplayer.Fragments.PlayingFragment;
import com.example.mediaplayer.Fragments.Right1Fragment;
import com.example.mediaplayer.Fragments.Right2Fragment;
import com.example.mediaplayer.Fragments.Right3Fragment;
import com.example.mediaplayer.Fragments.Right4Fragment;
import com.example.mediaplayer.Fragments.Right5Fragment;

/**
 * @author ycn
 * 继承于AppCompatActivity的新方法
 */
public class MainActivity extends AppCompatActivity {
    private PlayingFragment playingFragment;
    private NaviBarFragment leftfragment;
    private Right1Fragment rightfragment1;
    private Right2Fragment rightfragment2;
    private Right3Fragment rightfragment3;
    private Right4Fragment rightfragment4;
    private Right5Fragment rightfragment5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();
        setSwitchFragmentListener();
    }

    /**
     * 初始化
     */
    private void initial() {
        setFullScreenMode();
        initFragment();
        initSharedPreferenced();
    }

    private void initSharedPreferenced() {
        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
        sharedPreferencesUtils.initSharePreferences(this);
    }

    /**
     * 初始化Fragments
     */
    private void initFragment() {
        leftfragment = NaviBarFragment.newInstance();
        playingFragment = PlayingFragment.getInstance();
        loadFragment(R.id.fg_leftlayout, leftfragment);
        loadFragment(R.id.fg_rightlayout, playingFragment);
        rightfragment1 = Right1Fragment.newInstance();
        rightfragment2 = Right2Fragment.newInstance();
        rightfragment3 = Right3Fragment.newInstance();
        rightfragment4 = Right4Fragment.newInstance();
        rightfragment5 = Right5Fragment.newInstance();
    }

    /**
     * 隐藏状态栏
     * 设置沉浸式状态栏
     */
    private void setFullScreenMode() {
        MainActivity.this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }


    /**
     * 动态加载Fragment
     *
     * @param containerViewId 待加载至的容器
     * @param fragment        待加载的Fragment
     */
    private void loadFragment(int containerViewId, Fragment fragment) {
        FragmentManager sfm = getSupportFragmentManager();
        FragmentTransaction ts = sfm.beginTransaction();
        ts.replace(containerViewId, fragment);
        ts.commit();
    }

    /**
     * 设置监听函数，监听按键序号，利用序号切换Fragment
     */
    private void setSwitchFragmentListener() {
        leftfragment.setOnChoiceChangedLisenter(new NaviBarFragment.OnChoiceChangedLisenter() {
            @Override
            public void onChoiceChanged(int index) {
                changeFragment(index);
            }
        });
    }

    /**
     * 判断序号切换Fragment
     *
     * @param index 序号
     */
    private void changeFragment(int index) {
        switch (index) {
            case 0:
                loadFragment(R.id.fg_rightlayout, playingFragment);
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

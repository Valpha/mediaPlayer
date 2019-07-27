package com.example.mediaplayer;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.UiThread;

import com.example.mediaplayer.Adapters.LyricAdapter;
import com.example.mediaplayer.Fragments.PlayingFragment;
import com.example.mediaplayer.Listeners.BtFavoriteOnClickListener;
import com.example.mediaplayer.Listeners.BtModeOnTouchListener;
import com.example.mediaplayer.Listeners.BtNextOnTouchListener;
import com.example.mediaplayer.Listeners.BtPrevOnTouchListener;

import java.util.HashMap;
import java.util.List;

public class ViewManager {
    private static final ViewManager ourInstance = new ViewManager();
    private static ImageView ivSmallCover;
    private static ListView lvCurrentList;
    private static TextView tvCount;
    private static ImageButton btPlaying;
    private static ImageButton btFavorite;
    private static ImageButton btNext;
    private static ImageButton btMode;
    private static ImageButton btPrevious;
    private static TextView tvTitle;
    private static TextView tvArtist;
    private static ListView lvLyric;
    private static TextView tvComposer;
    private static TextView tvWriter;
    private static SeekBar sbProgress;
    private static ImageView ivChangPian;
    private static ImageView ivCover;
    private static ImageView ivReflection;
    private static Animation cdAmination;
    private static TextView tvRseekbar;
    private static TextView tvLseekbar;


    public static ViewManager getInstance() {
        return ourInstance;
    }

    private ViewManager() {
    }

    public static void initCdAnimation() {
        cdAmination = AnimationUtils.loadAnimation(PlayingFragment.getInstance().getActivity(), R.anim.music_cd_anim);
        cdAmination.setInterpolator(new LinearInterpolator());
    }

    public static void initView(View view) {
        lvCurrentList   = (ListView)    view.findViewById(R.id.lv_curl);
        lvLyric         = (ListView)    view.findViewById(R.id.lv_lyric);
        btPlaying       = (ImageButton) view.findViewById(R.id.bt_playing);
        btFavorite      = (ImageButton) view.findViewById(R.id.bt_favorite);
        btNext          = (ImageButton) view.findViewById(R.id.bt_next);
        btMode          = (ImageButton) view.findViewById(R.id.bt_mode);
        btPrevious      = (ImageButton) view.findViewById(R.id.bt_previous);
        tvCount         = (TextView)    view.findViewById(R.id.tv_count);
        tvTitle         = (TextView)    view.findViewById(R.id.tv_title);
        tvArtist        = (TextView)    view.findViewById(R.id.tv_artist);
        tvComposer      = (TextView)    view.findViewById(R.id.tv_composer);
        tvWriter        = (TextView)    view.findViewById(R.id.tv_writer);
        tvRseekbar      = (TextView)    view.findViewById(R.id.r_seekbar);
        tvLseekbar      = (TextView)    view.findViewById(R.id.l_seekbar);
        tvWriter        = (TextView)    view.findViewById(R.id.tv_writer);
        sbProgress      = (SeekBar)     view.findViewById(R.id.sb_progress);
        ivChangPian     = (ImageView)   view.findViewById(R.id.iv_changpian);
        ivCover         = (ImageView)   view.findViewById(R.id.iv_cover);
        ivReflection    = (ImageView)   view.findViewById(R.id.iv_reflection);
        ivSmallCover    = (ImageView)   view.findViewById(R.id.iv_smallcover);

        initCdAnimation();
    }

    public  static  void setTvCount(String count){
        tvCount.setText(count);
    }
    public static void setSeekBarProgressMax(int max) {
        sbProgress.setMax(max);
    }

    public static void setOnSeekBarProgressChangeListener(SeekBar.OnSeekBarChangeListener listener) {
        sbProgress.setOnSeekBarChangeListener(listener);
    }

    public static void setLvCurrentListAdapter(ListAdapter adapter) {
        lvCurrentList.setAdapter(adapter);
    }

    public static void setLvCurrentListOnItemClickListener(AdapterView.OnItemClickListener listener) {
        lvCurrentList.setOnItemClickListener(listener);
    }

    public static void setBtPlayingImageToPause() {
        btPlaying.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.pause));
    }

    public static void setBtPlayingImageToPlaying() {
        btPlaying.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.play_1));
    }

    public static void setIvCovers(Song song) {
        Bitmap cover = song.getCover();
        Utils.loadCover(cover, ivCover, ivReflection, ivSmallCover);
    }

    public static void setSbProgressMax(int max) {
        sbProgress.setMax(max);
    }

    public static void setTvTitleText(String titleText) {
        tvTitle.setText(titleText);
    }

    public static void setTvArtistText(String artistText) {
        tvArtist.setText(artistText);
    }

    public static void setTvComposerText(String composerText) {
        tvComposer.setText(composerText);
    }

    public static void setTvWriterText(String writerText) {
        tvWriter.setText(writerText);
    }

    public static void setLvLyric(HashMap<String, String> lyricList) {
        ListAdapter adapter = new LyricAdapter(lyricList);
        lvLyric.setAdapter(adapter);
    }

    public static void setIvAnimationStart() {
        ivChangPian.startAnimation(cdAmination);
    }

    public static void setIvAnimationStop() {
        ivChangPian.clearAnimation();
    }

    public static void setSeekBarCurrentProgress(int position) {
        sbProgress.setProgress(position);
    }

    public static void setBtNextDown() {
        btNext.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.next2));
    }

    public static void setBtNextUp() {
        btNext.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.next1));
    }

    public static void setBtPrevDown() {
        btPrevious.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.lastbt_two));
    }

    public static void setBtPrevUp() {
        btPrevious.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.lastbt_com));
    }

    public static void setBtModeSequenceDown() {
        btMode.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.shunxu2));
    }

    public static void setBtModeSequenceUp() {
        btMode.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.shunxu1));
    }

    public static void setBtModeShuffleDown() {
        btMode.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.shuiji2));
    }

    public static void setBtModeShuffleUp() {
        btMode.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.shuiji1));
    }

    public static void setBtModeSingleDown() {
        btMode.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.danquxunhuan_2));
    }

    public static void setBtModeSingleUp() {
        btMode.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.danquxunhuan_1));
    }

    public static void setBtFavoriteTrue() {
        btFavorite.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.like2));
    }

    public static void setBtFavoriteFalse() {
        btFavorite.setImageDrawable(PlayingFragment.getInstance().getResources().getDrawable(R.drawable.like1));
    }

    public static void setBtPlayingOnClickListener(View.OnClickListener listener) {
        btPlaying.setOnClickListener(listener);
    }

    public static void setBtNextOnTouchListener(View.OnTouchListener listener) {
        btNext.setOnTouchListener(listener);
    }

    public static void setBtModeOnTouchListener(View.OnTouchListener listener) {
        btMode.setOnTouchListener(listener);
    }

    public static void setBtPrevOnTouchListener(View.OnTouchListener listener) {
        btPrevious.setOnTouchListener(listener);
    }

    public static void setBtFavoriteOnClickListener(View.OnClickListener listener) {
        btFavorite.setOnClickListener(listener);
    }

    public static void setSeekBarMoveToStart() {
        sbProgress.setProgress(0);
    }

    public static void setTvLseekbar(final String time) {
        PlayingFragment.getInstance().getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvLseekbar.setText(time);
            }
        });

    }
}

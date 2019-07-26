package com.example.mediaplayer.Listeners;

import android.view.View;
import android.widget.AdapterView;

import com.example.mediaplayer.Adapters.CurAdapter;
import com.example.mediaplayer.MusicController;
import com.example.mediaplayer.PlayList;
import com.example.mediaplayer.R;
import com.example.mediaplayer.Song;
import com.example.mediaplayer.Utils;
import com.example.mediaplayer.ViewManager;

import java.io.IOException;

public class PlayListOnItemClickListener implements AdapterView.OnItemClickListener {
    private final CurAdapter adapter;

    public PlayListOnItemClickListener(CurAdapter curadapter) {
        this.adapter = curadapter;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


        adapter.notifyDataSetChanged();
        // changePlayListView(adapterView, view, i);

        try {
            changeSong(i);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // changeView(i);
        // curadapter.changeSelect(i);
        // changePlayListView(i);
        // songOrder = i;
        // audioPlayer.setMediaPlayerReset();
        // musicStatusChange(songs.get(i).getSsrc());
        // audioPlayer.setMediaPlayerStart();
        // mbtPlaying.setImageDrawable(getResources().getDrawable(R.drawable.pause));
        // ii = 1;
        // testLoadCover(i);
    }



    // private void changeView(int order) {
    //     Song song = PlayList.getSongByOrder(order);
    //     // ViewManager.setBtPlayingImageToPause();
    //
    // }

    private void changeSong(int order) throws IOException {
        PlayList.setSongOrder(order);
        MusicController.changeSong();
        MusicController.startPlaying();
    }

    // 动态修改选中列表颜色，因为使用回收View的方式，导致部分ViewHolder被回收，无法成功加载。
    // 换方法，使用刷新列表的方式来切换View
    // private void changePlayListView(AdapterView<?> adapterView, View view, int i) {
    //     View oldView = adapterView.getChildAt(Utils.getCurrentOrder());
    //     CurAdapter.ViewHolder mViewHolder = (CurAdapter.ViewHolder) oldView.getTag();
    //     mViewHolder.status.setVisibility(View.INVISIBLE);
    //     mViewHolder.title.setTextColor(0xffffffff);
    //     mViewHolder.singer.setTextColor(0xffffffff);
    //
    //     mViewHolder = (CurAdapter.ViewHolder) view.getTag();
    //     mViewHolder.title.setTextColor(0xff01B8F9);
    //     mViewHolder.singer.setTextColor(0xff01B8F9);
    //     mViewHolder.status.setVisibility(View.VISIBLE);
    //
    // }

    private void changePlayListView(AdapterView<?> adapterView, View view, int i) {
    }

}

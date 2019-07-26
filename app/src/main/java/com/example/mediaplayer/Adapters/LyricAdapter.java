package com.example.mediaplayer.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mediaplayer.Fragments.PlayingFragment;
import com.example.mediaplayer.R;

import java.util.HashMap;
import java.util.List;

public class LyricAdapter extends BaseAdapter {
    private HashMap<String, String> lyricList;
    private ViewHolder viewHolder;

    public LyricAdapter(HashMap<String, String> lyricList) {
        this.lyricList = lyricList;
    }

    @Override
    public int getCount() {
        return lyricList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(PlayingFragment.getInstance().getActivity(), R.layout.item_lyric, null);
            viewHolder = new ViewHolder();
            viewHolder.lyric = view.findViewById(R.id.tv_lyric);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.lyric.setText(lyricList.get(String.valueOf(i)));
        //
        // 设置颜色，应当在ViewManager中实现
        //
        // if (i == 0) {
        //     //蓝色
        //     viewHolder.lyric.setTextColor(0xff01B8F9);
        // } else {
        //     //白色
        //     viewHolder.lyric.setTextColor(0xffffffff);
        // }
        return view;
    }

    private class ViewHolder {
        public TextView lyric;
    }
}
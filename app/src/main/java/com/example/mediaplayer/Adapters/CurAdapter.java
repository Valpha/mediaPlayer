package com.example.mediaplayer.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediaplayer.Fragments.PlayingFragment;
import com.example.mediaplayer.PlayList;
import com.example.mediaplayer.R;
import com.example.mediaplayer.Song;
import com.example.mediaplayer.Utils;

import java.util.List;

public class CurAdapter extends BaseAdapter {

    private ViewHolder viewHolder;
    private List<Song> songs;
    private Integer curSelect;

    public CurAdapter(List<Song> songs) {
        this.songs = songs;
        this.curSelect = PlayList.getCurrentOrder();
    }

    @Override
    public int getCount() {
        return songs.size();
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
        viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(PlayingFragment.getInstance().getActivity(), R.layout.item_curl, null);
            viewHolder.singer = view.findViewById(R.id.tv_singer);
            viewHolder.title = view.findViewById(R.id.tv_title);
            viewHolder.status = view.findViewById(R.id.iv_status);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(songs.get(i).getTitle());
        viewHolder.singer.setText(songs.get(i).getSinger());
        curSelect = PlayList.getCurrentOrder();
        if (curSelect == i) {
            viewHolder.title.setTextColor(0xff01B8F9);
            viewHolder.singer.setTextColor(0xff01B8F9);
            viewHolder.status.setVisibility(View.VISIBLE);
        } else {
            viewHolder.status.setVisibility(View.INVISIBLE);
            viewHolder.title.setTextColor(0xffffffff);
            viewHolder.singer.setTextColor(0xffffffff);
        }

        return view;
    }

    public class ViewHolder {
        public ImageView status;
        public TextView title;
        public TextView singer;

    }

}

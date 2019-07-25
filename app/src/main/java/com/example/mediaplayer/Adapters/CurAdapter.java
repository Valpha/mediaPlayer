package com.example.mediaplayer.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediaplayer.Fragments.RightFragment;
import com.example.mediaplayer.MainActivity;
import com.example.mediaplayer.R;

public class CurAdapter extends BaseAdapter {

    private  ViewHolder viewHolder;

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
            view = View.inflate(, R.layout.item_curl, null);
            viewHolder.singer = view.findViewById(R.id.tv_singer);
            viewHolder.title = view.findViewById(R.id.tv_title);
            viewHolder.status = view.findViewById(R.id.iv_status);
            view.setTag(viewHolder);
        } else {
            viewHolder = (RightFragment.CurAdapter.ViewHolder) view.getTag();
        }
        viewHolder.title.setText(songs.get(i).getTitle());
        viewHolder.singer.setText(songs.get(i).getSinger());
        if (curselect == i) {
            viewHolder.title.setTextColor(0xff01B8F9);
            viewHolder.singer.setTextColor(0xff01B8F9);
            viewHolder.status.setVisibility(View.VISIBLE);
            int position = i + 1;
            mtvCount.setText(position + "/20");
            curltosong(curselect);
        } else {
            viewHolder.status.setVisibility(View.INVISIBLE);
            viewHolder.title.setTextColor(0xffffffff);
            viewHolder.singer.setTextColor(0xffffffff);
        }

        return view;
    }
    private class ViewHolder {
        public ImageView status;
        public TextView title;

        public ViewHolder() {
            this.status = null;
            this.title = null;
            this.singer = null;
        }

        public TextView singer;
    }

}

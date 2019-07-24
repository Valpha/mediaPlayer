package com.example.mediaplayer;


import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RightFragment extends Fragment {
    private String tvContent;
    private ListView mlvcurl;
//    private List<HashMap<String ,String>> contactsList;
    private String title;
    private String singer;

    private List<Song> songs = new ArrayList<Song>();
    private List<HashMap<String, String>> lyricList;
    private TextView tv_count;
    private ListView lvGeci;
    private String[] objectView = new String[]{"爱像一阵风", "吹完它就走", "这样的节奏",
                                               "谁都无可奈何", "没有你以后","我灵魂失控",
                                               "黑云在降落", "我被它拖着走", "静静悄悄默默离开",
                                               "陷入了危险边缘 baby~"};




    private int curselect = -1;
    int flag = 0;
    private ImageButton bt_lastsong;
    private ImageButton bt_modol;
    private ImageButton bt_nextsong;
    private ImageButton bt_playing;


    public RightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LeftFragment.
     */
    public static RightFragment newInstance() {
        RightFragment fragment = new RightFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_right, container, false);
        mlvcurl = (ListView)view.findViewById(R.id.lv_curl);
        tv_count=(TextView)view.findViewById(R.id.tv_count);
        //朱远帆----歌词

        bt_playing = (ImageButton)view.findViewById(R.id.palyingBtn);
        bt_nextsong = (ImageButton)view.findViewById(R.id.next_Btn);
        bt_modol = (ImageButton)view.findViewById(R.id.modulBtn);
        bt_lastsong = (ImageButton)view.findViewById(R.id.last_Btn);
        lyricShow();
        lvGeci = (ListView) view.findViewById(R.id.lv_geci);
        lyricList = new ArrayList<>();
        for (int i=0; i<objectView.length; i++){
            HashMap<String, String> map = new HashMap<>();
            map.put("lyric", objectView[i]);
            lyricList.add(map);
        }

        LyricAdapter lyricadapter = new LyricAdapter();
        lvGeci.setAdapter(lyricadapter);


//
//        contactsList = new ArrayList<>();
////        HashMap<String, String>  map = new HashMap<>();
//        for (int i = 0; i < 10; i++) {
//            HashMap<String, String> map = new HashMap<>();
//            map.put("singer", "周杰伦");
//            map.put("title", "龙卷风");
//            contactsList.add(map);
//        }
        scanDisk();

        final CurAdapter curadapter = new CurAdapter();
        mlvcurl.setAdapter(curadapter);

        mlvcurl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                调用位置
                curadapter.changeSelect(i);
            }
        });

        bt_playing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"播放",Toast.LENGTH_LONG).show();

            }
        });

        bt_nextsong.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    Toast.makeText(getActivity(),"下一首歌",Toast.LENGTH_LONG).show();
//                    ac.onChoiceChanged("下一首");
                    ((ImageButton)view).setImageDrawable(getResources().getDrawable(R.drawable.next2));
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.next1));
                }
                return false;
            }
        });

        bt_modol.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    ((ImageButton)view).setImageDrawable(getResources().getDrawable(R.drawable.shunxu1));
                    Toast.makeText(getActivity(),"顺序播放",Toast.LENGTH_LONG).show();
                }else  {
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.shunxu2));
                }
                return false;
            }
        });

        bt_lastsong.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    ((ImageButton)view).setImageDrawable(getResources().getDrawable(R.drawable.lastbt_two));
                    Toast.makeText(getActivity(),"上一首歌",Toast.LENGTH_LONG).show();
//                    ac.onChoiceChanged("上一首");
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.lastbt_com));
                }
                return false;
            }
        });
        return view;
    }

    private void lyricShow() {

    }

// 往song中加歌曲
    private List<Song> scanDisk() {
        for(int i=0;i<4;i++) {
            Song song1 = new Song(title, singer);
            song1.setSinger("周杰伦");
            song1.setTitle("反方向的钟");
            songs.add(song1);
            Song song2 = new Song(title, singer);
            song2.setSinger("周杰伦");
            song2.setTitle("世界末日");
            songs.add(song2);
            Song song3 = new Song(title, singer);
            song3.setSinger("周杰伦");
            song3.setTitle("龙卷风");
            songs.add(song3);
            Song song4 = new Song(title, singer);
            song4.setSinger("周杰伦");
            song4.setTitle("爱在西元前");
            songs.add(song4);
            Song song5 = new Song(title, singer);
            song5.setSinger("周杰伦");
            song5.setTitle("星晴");
            songs.add(song5);
        }

//        List<String> filepaths = new ArrayList<String>();
//        for (int i =0 ;i<= filepaths.size();i++) {
//            String title = Utils.getTitle(filepath);
//            String singer = Utils.getTsinger(filepath);
//            Song song = new Song(title, singer);
//            songs.add(song);
//        }

        return songs;
    }
    /**
     * @author shizhuoxin
     * CurAdapter
     */
    private class CurAdapter extends BaseAdapter {
        private ViewHolder viewHolder;
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
                view = View.inflate(getActivity(), R.layout.item_curl, null);
                viewHolder.singer = view.findViewById(R.id.tv_singer);
                viewHolder.title = view.findViewById(R.id.tv_title);
                viewHolder.status = view.findViewById(R.id.iv_status);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.title.setText(songs.get(i).getTitle());
            viewHolder.singer.setText(songs.get(i).getSinger());
            if (curselect == i) {
                viewHolder.title.setTextColor(0xff01B8F9);
                viewHolder.singer.setTextColor(0xff01B8F9);
                viewHolder.status.setVisibility(View.VISIBLE);
                int position = i + 1;
                tv_count.setText(position + "/10");
                curltosong(curselect);
            } else {
                viewHolder.status.setVisibility(View.INVISIBLE);
                viewHolder.title.setTextColor(0xffffffff);
                viewHolder.singer.setTextColor(0xffffffff);
            }

            return view;
        }

//      得到songs内的位置
        private void curltosong(int curselect) {
            Toast.makeText(getActivity(), "curselect"+curselect, Toast.LENGTH_SHORT).show();
        }


        /**
         * 判断位置是否一致
         */
        public void changeSelect(int i) {
            if (i != curselect) {
                curselect = i;
                notifyDataSetChanged();
            }

        }

        /**
         * ViewHolder类
         */
        private class ViewHolder {
            public ImageView status;
            public TextView title;
            public TextView singer;
        }
    }
    //歌词colum的adapter
    private class LyricAdapter extends BaseAdapter {
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderLyric viewholder = null;
            if(convertView == null){
                convertView = View.inflate(getActivity(), R.layout.item_lyric, null);
                viewholder = new ViewHolderLyric();
                viewholder.lyric = convertView.findViewById(R.id.tv_lyric);
                convertView.setTag(viewholder);
            }else {
                viewholder = (ViewHolderLyric)convertView.getTag();
            }

            viewholder.lyric.setText(lyricList.get(position).get("lyric"));
//            viewholder.lyric.setTextColor(#FFFFFF);
            if(position == 3){
                //蓝色
                viewholder.lyric.setTextColor(0xff01B8F9);
            }else {
                //白色
                viewholder.lyric.setTextColor(0xffffffff);
            }
            return convertView;
        }
        private class ViewHolderLyric {
            public TextView lyric;
        }
    }

}

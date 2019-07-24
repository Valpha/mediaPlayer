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
    LyricAdapter lyricadapter = new LyricAdapter();
    private List<Song> songs = new ArrayList<Song>();
    private List<HashMap<String, String>> lyricList;
    private TextView tv_count;
    private ListView lvGeci;
    private int curselect = -1;
    int flag = 0;
    private String[] objectview=null;
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
        scanDisk();

        //朱远帆----歌词

        bt_playing = (ImageButton)view.findViewById(R.id.palyingBtn);
        bt_nextsong = (ImageButton)view.findViewById(R.id.next_Btn);
        bt_modol = (ImageButton)view.findViewById(R.id.modulBtn);
        bt_lastsong = (ImageButton)view.findViewById(R.id.last_Btn);
        lvGeci = (ListView) view.findViewById(R.id.lv_geci);
        lyricList = new ArrayList<>();


        final CurAdapter curadapter = new CurAdapter();
        mlvcurl.setAdapter(curadapter);

        mlvcurl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                调用位置
                curadapter.changeSelect(i);
//                updateLyric();
                lyricShow();
                lvGeci.setAdapter(lyricadapter);
                lyricadapter.notifyDataSetChanged();

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

    private void updateLyric(int index) {


    }

    private void lyricShow() {
        lyricList.clear();
        int pos=0;
        if(curselect>=0)
        {  pos=curselect;}
        for (int t=0; t<songs.get(pos).getObjectViewLength(); t++){
            HashMap<String, String> map = new HashMap<>();
            map.put("lyric", songs.get(pos).getObjectView()[t]);
            lyricList.add(map);
            Log.d("hhhhhh", "lyricShow: "+songs.get(pos).getObjectView()[t]);
        }

        Toast.makeText(getActivity(), "ddd"+pos, Toast.LENGTH_SHORT).show();

    }

// 往song中加歌曲
    private List<Song> scanDisk() {
        for(int i=0;i<4;i++) {
            Song song1 = new Song(title, singer,objectview);
            song1.setSinger("周杰伦");
            song1.setTitle("反方向的钟");
            String[] tempobjectView = new String[]{"迷迷蒙蒙 你给的梦 ", "出现裂缝 隐隐作痛", "怎么沟通你都没空",
                    "说我不懂 说了没用 ", "他的笑容 有何不同","在你心中 我不再受宠 ",
                    "我的天空 是雨是风 还是彩虹 ", "你在操纵", "恨自己真的没用情绪激动 ",
                    "颗心到现在还在抽痛 "};

            song1.setObjectView(tempobjectView);
            songs.add(song1);
            Song song2 = new Song(title, singer,objectview);
            song2.setSinger("周杰伦");
            song2.setTitle("世界末日");
            String[] tempobjectView2 = new String[]{"想笑 来伪装掉下的眼泪 ", "点点头 承认自己会怕黑", "我只求 能借一点的时间来陪",
                    "你却连同情都不给", "想哭 来试探自己麻痹了没 ","全世界 好像只有我疲惫",
                    "无所谓 反正难过就敷衍走一回 ", "但愿绝望和无奈远走高飞", "天灰灰 会不会  ",
                    "让我忘了你是谁 "};
            song2.setObjectView(tempobjectView2);
            songs.add(song2);
            Song song3 = new Song(title, singer,objectview);
            song3.setSinger("周杰伦");
            song3.setTitle("龙卷风");
            String[] tempobjectView3 = new String[]{"爱像一阵风 吹完它就走 ", "这样的节奏 谁都无可奈何 ", "没有你以后 我灵魂失控 ",
                    "黑云在降落 我被它拖着走", "静静悄悄默默离开 ","陷入了危险边缘Baby",
                    "我的世界已狂风暴雨 ", "Wu 爱情来的太快就像龙卷风 ", "离不开暴风圈来不及逃 ",
                    "我不能再想我不能再想 "};
            song3.setObjectView(tempobjectView3);
            songs.add(song3);
            Song song4 = new Song(title, singer,objectview);
            song4.setSinger("周杰伦");
            song4.setTitle("爱在西元前");
            String[] tempobjectView4 = new String[]{"古巴比伦王颁布了汉谟拉比法典", "刻在黑色的玄武岩 距今已经三千七百多年", "你在橱窗前 凝视碑文的字眼 ",
                    "我却在旁静静欣赏你那张我深爱的脸 ", "祭司 神殿 征战 弓箭 是谁的从前 ","喜欢在人潮中你只属于我的那画面 ",
                    "经过苏美女神身边 我以女神之名许愿", "思念像底格里斯河般的漫延 ", "当古文明只剩下难解的语言  ",
                    "传说就成了永垂不朽的诗篇 "};
            song4.setObjectView(tempobjectView4);
            songs.add(song4);
            Song song5 = new Song(title, singer,objectview);
            song5.setSinger("周杰伦");
            song5.setTitle("星晴");
            String[] tempobjectView5 = new String[]{"一步两步三步四步 望着天 手牵手 ", "一颗两颗三颗四颗 连成线看星星 ", "一步两步三步四步 望着天 手牵手 ",
                    "一颗两颗三颗四颗 连成线看星星 ", "乘着风游荡在蓝天边 ","一片云掉落在我面前",
                    "捏成你的形状 ", "随风跟着我", "一口一口吃掉忧愁 ",
                    "载着你彷佛载着阳光  "};
            song5.setObjectView(tempobjectView5);
            songs.add(song5);
        }


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
                tv_count.setText(position + "/20");
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
//            Toast.makeText(getActivity(), "curselect"+curselect, Toast.LENGTH_SHORT).show();
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

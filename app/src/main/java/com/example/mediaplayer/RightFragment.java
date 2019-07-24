package com.example.mediaplayer;


import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RightFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author ycn
 */


public class RightFragment extends Fragment {
    private String tvContent;
    private ListView mlvcurl;
    private String title;
    private String singer;
    private String writer;
    private String zuoqu;
    LyricAdapter lyricadapter = new LyricAdapter();
    private List<Song> songs = new ArrayList<Song>();
    private List<HashMap<String, String>> lyricList;
    private TextView mtvCount;
    private ListView lvGeci;
    private int curselect = -1;
    int flag = 0;
    private String[] objectview = null;
    private String ssrc;
    private ImageButton mbtLastSong;
    private ImageButton mbtModol;
    private ImageButton mbtNextSong;
    private ImageButton mbtPlaying;
    private MediaPlayer mp;
    private int songOrder;

    private TextView mtvSongName;
    private TextView mtvsSinger;
    private TextView mtvZuoQu;
    private TextView mtvWriter;
    private ImageButton mbtFavorite;
    private SeekBar sb_seek;
    private String TAG = "RightFragment";

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
        songOrder = 0;
        //音乐播放器
        mp = new MediaPlayer();
        View view = inflater.inflate(R.layout.fragment_right, container, false);

        initView(view);

        sb_seek.setMax(100);
        sb_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, progress+"");
                if(mp.isPlaying()){
                    //实现过拽播放
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //音乐播放结束
                Log.d(TAG, "onCompletion---");
            }
        });

        lyricList = new ArrayList<>();
        scanDisk();
        musicStatusChange(songs.get(0).getSsrc());
        final CurAdapter curadapter = new CurAdapter();
        mlvcurl.setAdapter(curadapter);
        mlvcurl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                curadapter.changeSelect(i);
                changeView(i);
            }});

        mbtPlaying.setOnClickListener(new View.OnClickListener() {
            private boolean i =true;

            @Override
            public void onClick(View view) {
                if(i) {
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.pause));
                    Toast.makeText(getActivity(), "正在播放", Toast.LENGTH_LONG).show();
                    sb_seek.setMax(mp.getDuration());
                    mp.start();

                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            //1秒钟调用一次
                            sb_seek.setProgress(mp.getCurrentPosition());

                        }
                    };
                    new Timer().schedule(task, 0 ,1000);
                    i =false;
                }else {
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.play_1));
                    Toast.makeText(getActivity(), "已经暂停", Toast.LENGTH_LONG).show();
                    mp.pause();
                    i = true;
                }
            }
        });

        mbtNextSong.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    mp.reset();
                    int k=19;
                    if (songOrder >= k) {
                        songOrder = -1;
                    }
                    musicStatusChange(songs.get(++songOrder).getSsrc());

                    mp.start();
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.next2));

                    curadapter.changeSelect(songOrder);
                    changeView(songOrder);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.next1));
                }
                return false;
            }
        });

        mbtModol.setOnClickListener(new View.OnClickListener() {
            private int i=0;

            @Override
            public void onClick(View view) {
                if(i ==0) {
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.shuiji2));
                    Toast.makeText(getActivity(), "随机播放", Toast.LENGTH_LONG).show();
                    i = 1;
                }else if (i ==1){
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.shunxu2));
                    Toast.makeText(getActivity(), "顺序播放", Toast.LENGTH_LONG).show();
                    i = 2;
                }else {
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.xunhuan2));
                    Toast.makeText(getActivity(), "单曲循环", Toast.LENGTH_LONG).show();
                    i = 0;
                }
            }
        });

        mbtLastSong.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.lastbt_two));

                    mp.reset();
                    if (songOrder < 1) {
                        songOrder = 20;
                    }
                    musicStatusChange(songs.get(--songOrder).getSsrc());
                    mp.start();
                    curadapter.changeSelect(songOrder);
//                  歌词同步的封装
                    changeView(songOrder);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.lastbt_com));
                }
                return false;
            }
        });

        mbtFavorite.setOnClickListener(new View.OnClickListener() {
            private boolean i = true;
            @Override
            public void onClick(View view) {
                if (i){
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.like2));
                    Toast.makeText(getActivity(), "已收藏", Toast.LENGTH_LONG).show();
                    i =false;
                }else {
                    ((ImageButton) view).setImageDrawable(getResources().getDrawable(R.drawable.like1));
                    Toast.makeText(getActivity(), "已取消收藏", Toast.LENGTH_LONG).show();
                    i = true;
                }
            }
        });
        return view;
    }

    private void initView(View view) {
        mlvcurl = (ListView) view.findViewById(R.id.lv_curl);
        mtvCount = (TextView) view.findViewById(R.id.tv_count);
        mbtPlaying = (ImageButton) view.findViewById(R.id.palyingBtn);
        mbtFavorite = (ImageButton)view.findViewById(R.id.fravoriteBtn);
        mbtNextSong = (ImageButton) view.findViewById(R.id.next_Btn);
        mbtModol = (ImageButton) view.findViewById(R.id.modulBtn);
        mbtLastSong = (ImageButton) view.findViewById(R.id.last_Btn);
        mtvSongName = (TextView) view.findViewById(R.id.tv_songname);
        mtvsSinger = (TextView) view.findViewById(R.id.tv_ssinger);
        lvGeci = (ListView) view.findViewById(R.id.lv_geci);
        mtvZuoQu = (TextView) view.findViewById(R.id.tv_zuoqu);
        mtvWriter = (TextView) view.findViewById(R.id.tv_writer);
        sb_seek = (SeekBar)view.findViewById(R.id.v_seekbar);

    }

    private void changeView(int i) {
//                updateLyric();
        lyricShow();
        lvGeci.setAdapter(lyricadapter);
        lyricadapter.notifyDataSetChanged();
        mtvSongName.setText(songs.get(i).getTitle());
        mtvsSinger.setText(songs.get(i).getSinger());
        mtvWriter.setText(songs.get(i).getWriter());
        mtvZuoQu.setText(songs.get(i).getZuoqu());
    }

    private void musicStatusChange(String songname) {
        File musicpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        File mp3file = new File(musicpath, songname);
        String path = mp3file.getAbsolutePath();
        try {
            mp.setDataSource(path);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateLyric(int index) {


    }

    private void lyricShow() {
        lyricList.clear();
        int pos = 0;
        if (curselect >= 0) {
            pos = curselect;
        }
        for (int t = 0; t < songs.get(pos).getObjectViewLength(); t++) {
            HashMap<String, String> map = new HashMap<>(50);
            map.put("lyric", songs.get(pos).getObjectView()[t]);
            lyricList.add(map);
        }

    }

    /**
     * @return 往song中加歌曲
     */
    private List<Song> scanDisk() {
        int k=4;
        for (int i = 0; i < k; i++) {
            Song song1 = new Song(title, singer, objectview, writer, zuoqu, ssrc);
            song1.setSinger("周杰伦");
            song1.setTitle("反方向的钟");
            String[] tempobjectView = new String[]{"迷迷蒙蒙 你给的梦 ", "出现裂缝 隐隐作痛", "怎么沟通你都没空",
                    "说我不懂 说了没用 ", "他的笑容 有何不同", "在你心中 我不再受宠 ",
                    "我的天空 是雨是风 还是彩虹 ", "你在操纵", "恨自己真的没用情绪激动 ",
                    "颗心到现在还在抽痛 "};

            song1.setObjectView(tempobjectView);
            song1.setWriter("作词：方文山 ");
            song1.setZuoqu("作曲：周杰伦");
            song1.setSsrc("薛之谦 - 慢半拍.mp3");
            songs.add(song1);
            Song song2 = new Song(title, singer, objectview, writer, zuoqu, ssrc);
            song2.setSinger("周杰伦");
            song2.setTitle("世界末日");
            String[] tempobjectView2 = new String[]{"想笑 来伪装掉下的眼泪 ", "点点头 承认自己会怕黑", "我只求 能借一点的时间来陪",
                    "你却连同情都不给", "想哭 来试探自己麻痹了没 ", "全世界 好像只有我疲惫",
                    "无所谓 反正难过就敷衍走一回 ", "但愿绝望和无奈远走高飞", "天灰灰 会不会  ",
                    "让我忘了你是谁 "};
            song2.setObjectView(tempobjectView2);
            song2.setWriter("作词：方文山 ");
            song2.setZuoqu("作曲：周杰伦");
            song2.setSsrc("小坂明子 - 海の見える街.mp3");
            songs.add(song2);
            Song song3 = new Song(title, singer, objectview, writer, zuoqu, ssrc);
            song3.setSinger("周杰伦");
            song3.setTitle("龙卷风");
            String[] tempobjectView3 = new String[]{"爱像一阵风 吹完它就走 ", "这样的节奏 谁都无可奈何 ", "没有你以后 我灵魂失控 ",
                    "黑云在降落 我被它拖着走", "静静悄悄默默离开 ", "陷入了危险边缘Baby",
                    "我的世界已狂风暴雨 ", "Wu 爱情来的太快就像龙卷风 ", "离不开暴风圈来不及逃 ",
                    "我不能再想我不能再想 "};
            song3.setObjectView(tempobjectView3);
            song3.setWriter("作词：徐若瑄 ");
            song3.setZuoqu("作曲：周杰伦");
            song3.setSsrc("周杰伦—龙卷风.mp3");
            songs.add(song3);
            Song song4 = new Song(title, singer, objectview, writer, zuoqu, ssrc);
            song4.setSinger("周杰伦");
            song4.setTitle("爱在西元前");
            String[] tempobjectView4 = new String[]{"古巴比伦王颁布了汉谟拉比法典", "刻在黑色的玄武岩 距今已经三千七百多年", "你在橱窗前 凝视碑文的字眼 ",
                    "我却在旁静静欣赏你那张我深爱的脸 ", "祭司 神殿 征战 弓箭 是谁的从前 ", "喜欢在人潮中你只属于我的那画面 ",
                    "经过苏美女神身边 我以女神之名许愿", "思念像底格里斯河般的漫延 ", "当古文明只剩下难解的语言  ",
                    "传说就成了永垂不朽的诗篇 "};
            song4.setObjectView(tempobjectView4);
            song4.setWriter("作词：方文山 ");
            song4.setZuoqu("作曲：周杰伦");
            song4.setSsrc("周杰伦—爱在西元前.mp3");
            songs.add(song4);
            Song song5 = new Song(title, singer, objectview, writer, zuoqu, ssrc);
            song5.setSinger("周杰伦");
            song5.setTitle("星晴");
            String[] tempobjectView5 = new String[]{"一步两步三步四步 望着天 手牵手 ", "一颗两颗三颗四颗 连成线看星星 ", "一步两步三步四步 望着天 手牵手 ",
                    "一颗两颗三颗四颗 连成线看星星 ", "乘着风游荡在蓝天边 ", "一片云掉落在我面前",
                    "捏成你的形状 ", "随风跟着我", "一口一口吃掉忧愁 ",
                    "载着你彷佛载着阳光  "};
            song5.setObjectView(tempobjectView5);
            song5.setWriter("作词：周杰伦 ");
            song5.setZuoqu("作曲：周杰伦");
            song5.setSsrc("周杰伦—星晴.mp3");
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
                mtvCount.setText(position + "/20");
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


   /**
    * 歌词的adapter
    */
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
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.item_lyric, null);
                viewholder = new ViewHolderLyric();
                viewholder.lyric = convertView.findViewById(R.id.tv_lyric);
                convertView.setTag(viewholder);
            } else {
                viewholder = (ViewHolderLyric) convertView.getTag();
            }
            viewholder.lyric.setText(lyricList.get(position).get("lyric"));
//            viewholder.lyric.setTextColor(#FFFFFF);
            if (position == 0) {
                //蓝色
                viewholder.lyric.setTextColor(0xff01B8F9);
            } else {
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

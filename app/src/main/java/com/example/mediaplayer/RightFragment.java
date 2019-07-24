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
            song1.setSinger("BAMBOO");
            song1.setTitle("让他走");
            String[] tempobjectView = new String[]{ "他还是走了对吗 ",
                    "讽刺是一种默认你还爱着他 ",
                    "溃烂的伤口总会结痂就别再去抓 ",
                    "就像你初次见他不知所措心乱如麻 ",
                    "此刻你也只想沉默听我陈述这段话 ",
                    "你还是嗅着周围带熟悉味道的空气 ",
                    "二氧化碳充满着彼此熟悉记忆 ",
                    "他是从某年某月开始离开了你 ",
                    "总有一天你会拼命却再也记不起 "
                    , "浴室里的情侣牙刷还有毛巾, "
                    , "他曾用你喜欢的方式打动你芳心 "
                    , "某种液体在眼眶挑衅出出又进进 "
                    , "它们像看客一般玩的尽情又尽兴 "
                    , "他没有给过任何承诺离开也是情理 "
                    , "但你还是责怪自己胡乱生了气,"
                    , "把美好重复回忆把它用力的记 "};

            song1.setObjectView(tempobjectView);
            song1.setWriter("作词：Bamboo.Lee ");
            song1.setZuoqu("作曲：Bamboo.Lee");
            song1.setSsrc("BAMBOO - 让他走.mp3");
            songs.add(song1);
            Song song2 = new Song(title, singer, objectview, writer, zuoqu, ssrc);
            song2.setSinger("Jocelyn Pook Russian Red ");
            song2.setTitle("Loving Strangers");
            String[] tempobjectView2 = new String[]{"Ahah"
                    , "爱上了陌生人"
                    , "Loving strangers ×3"
                    , "我口袋破了一个洞"
                    , "Ah"
                    , "钱都从那里消失了"
                    , "I’ve got a hole, oh in my pocket"
                    , "我想经常存在于你的心里"
                    , "where all the money has gone"
                    , "因为你的心很忙，我的心却很悠闲"
                    , "I’ve got a whole lot of work to do with your heart"
                    , "爱上陌生人"
                    , "Cuz it’s so busy, mine’s not"
                    , "爱上陌生人"
                    , "Loving strangers ×3"};
            song2.setObjectView(tempobjectView2);
            song2.setWriter("作词：未知 ");
            song2.setZuoqu("作曲：未知");
            song2.setSsrc("Jocelyn Pook Russian Red - Loving Strangers.mp3");
            songs.add(song2);
            Song song3 = new Song(title, singer, objectview, writer, zuoqu, ssrc);
            song3.setSinger("Nuo.");
            song3.setTitle("天气之子-グランドエスケープ");
            String[] tempobjectView3 = new String[]{"那个夏天的日子"
                    , "在那片天空之上的我们.."
                    , "将这个世界的形状...彻底的..."
                    , "改变了"
                    , "（重力陷入沉睡）"
                    , "我...不想回去"
                    , "（在千年一回的今日）"
                    , "你是离家出走的少年吧"
                    , "在他从梦境醒来之时）"
                    , "少年 在找工作吧"
                    , "（带回那曾失去的居所）"
                    , "天空一直比大海不可思议"
                    , "是个未知的世界"
                    , "（“一二”地踢向大地）"};
            song3.setObjectView(tempobjectView3);
            song3.setWriter("作词：RADWIMPS ");
            song3.setZuoqu("作曲：RADWIMPS");
            song3.setSsrc("Nuo. - 天气之子-グランドエスケープ（Cover：RADWIMPS）.mp3");
            songs.add(song3);
            Song song4 = new Song(title, singer, objectview, writer, zuoqu, ssrc);
            song4.setSinger(" 黒木渚");
            song4.setTitle("カルデラ");
            String[] tempobjectView4 = new String[]{"不要看我挣扎的样子"
                    , "カルデラ"
                    , "即使不再年轻 即使失去氧气"
                    , "黒木渚"
                    , "也必定会烙上印记"
                    , "最低だ」と思う日は 会いに来てよ"
                    , "为什么 无论是患病的时候还是受侮辱的夜晚"
                    , "私がもがくのを見ていなよ"
                    , "顽固如我 都能有条不紊地活下去呢？"
                    , "若さ失っても 酸素失っても"
                    , "喷涌而出的总是 恰到时机"
                    , "必ず目印でいるよ"
                    , "黑色的事件 像是焦急等待一般"
                    , "なんでどうして 病める時も 屈辱の夜も"};
            song4.setObjectView(tempobjectView4);
            song4.setWriter("作词： 黒木渚 ");
            song4.setZuoqu("作曲： 黒木渚");
            song4.setSsrc("黒木渚 - カルデラ.mp3");
            songs.add(song4);
            Song song5 = new Song(title, singer, objectview, writer, zuoqu, ssrc);
            song5.setSinger("相对论");
            song5.setTitle("最后的晚餐");
            String[] tempobjectView5 = new String[]{ "灯光下酒杯在旋转 酒杯里有半只香烟 香烟里有欲望蔓延"
                    , "分辨不出你我的脸 忘记客人谁来埋单 只是想在空中盘旋"
                    , "我已带上你对我的期盼 再最后一次同你作欢"
                    , "我已离开你双眼的视线 一切结束在这次晚餐 让你习惯"
                    , "月光下杂乱的脚步 带领你我走向迷雾 时空交错误入云图"
                    , "有个声音问我认不认输 我开始意识到这是个错误 想哭"
                    , "我已带上你对我的期盼 再最后一次同你作欢"
                    , "我已离开你双眼的视线 一切结束在这次晚餐 让你习惯"
                    , "我已带上你对我的期盼 再最后一次同你作欢"
                    , "我已离开你双眼的视线 一切结束在这次晚餐"
                    , "带上你的期盼  最后一次"
                    , "离开你的视线 让我结束"};
            song5.setObjectView(tempobjectView5);
            song5.setWriter("作词：邵庄 ");
            song5.setZuoqu("作曲：相对论乐队");
            song5.setSsrc("相对论 - 最后的晚餐.mp3");
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

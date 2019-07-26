package com.example.mediaplayer.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mediaplayer.Adapters.CurAdapter;
import com.example.mediaplayer.Listeners.BtFavoriteOnClickListener;
import com.example.mediaplayer.Listeners.BtModeOnTouchListener;
import com.example.mediaplayer.Listeners.BtNextOnTouchListener;
import com.example.mediaplayer.Listeners.BtPlayingOnClickListener;
import com.example.mediaplayer.Listeners.BtPrevOnTouchListener;
import com.example.mediaplayer.Listeners.PlayListOnItemClickListener;
import com.example.mediaplayer.Listeners.MyOnCompletionListener;
import com.example.mediaplayer.Listeners.MySeekBarSeekToListener;
import com.example.mediaplayer.MusicController;
import com.example.mediaplayer.PlayList;
import com.example.mediaplayer.R;
import com.example.mediaplayer.Song;
import com.example.mediaplayer.ViewManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayingFragment#getInstance} factory method to
 * create an instance of this fragment.
 *
 * @author ycn
 */
public class PlayingFragment extends Fragment {
    private static PlayingFragment playingFragment = new PlayingFragment();
    private ListView mlvcurl;
    // LyricAdapter lyricadapter = new LyricAdapter();
    // private List<Song> songs = new ArrayList<Song>();
    // private List<HashMap<String, String>> lyricList;
    private ListView lvGeci;
    private int curselect = -1;
    private String[] objectview = null;
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
    private View ivCd;
    // private Animation cdAmination;
    private int ii;
    private ImageView iv_cover;
    private ImageView iv_reflection;
    private ImageView iv_smallCover;
    private TextView mtvCount;
    private String title;
    private String singer;
    private String writer;
    private String zuoqu;
    private String ssrc;
    private String picpath;
    private String lyricPath;
    public CurAdapter curadapter;

    public PlayingFragment() {
        // Required empty public constructor
    }

    public static PlayingFragment getInstance() {
        return playingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //音乐播放器
        // mp = new MediaPlayer();

        View view = inflater.inflate(R.layout.fragment_playing, container, false);
        ViewManager.initView(view);
        // initView(view);

//       testLoadCover();


        // initCDAnim();
        // sb_seek.setMax(100);
        ViewManager.setSeekBarProgressMax(100);
        // sb_seek.setOnSeekBarChangeListener(new MySeekBarSeekToListener());
        ViewManager.setOnSeekBarProgressChangeListener(new MySeekBarSeekToListener());

        // mp.setOnCompletionListener(new MyOnCompletionListener());
        MusicController.setCompletionListener(new MyOnCompletionListener());
        // songOrder = -1;
        // lyricList = new ArrayList<>();
        // 在ViewManager中实现了
        List<Song> songs = scanDisk();
        PlayList playList = PlayList.getInstance();
        // 重新加载播放列表
        playList.loadCurrentList(songs);

        // musicStatusChange(songs.get(0).getSsrc());
        //
        // 加载歌曲的写法，初始化时不需要加载第0首歌曲，PlayList中已经初始化为0了
        //
        // try {
        //     MusicController.changeSong(PlayList.getSongByOrder(0));
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        curadapter = new CurAdapter(songs);
        // mlvcurl.setAdapter(curadapter);
        ViewManager.setLvCurrentListAdapter(curadapter);
        // mlvcurl.setOnItemClickListener(new PlayListOnItemClickListener());
        ViewManager.setLvCurrentListOnItemClickListener(new PlayListOnItemClickListener(curadapter));


        // mbtPlaying.setOnClickListener(new BtPlayingOnClickListener() );
        ViewManager.setBtPlayingOnClickListener(new BtPlayingOnClickListener());
        // mbtNextSong.setOnTouchListener(new BtNextOnTouchListener());
        ViewManager.setBtNextOnTouchListener(new BtNextOnTouchListener());
        // mbtModol.setOnTouchListener(new BtModeOnTouchListener());
        ViewManager.setBtModeOnTouchListener(new BtModeOnTouchListener());
        // mbtLastSong.setOnTouchListener(new BtPrevOnTouchListener());
        ViewManager.setBtPrevOnTouchListener(new BtPrevOnTouchListener());
        // mbtFavorite.setOnClickListener(new BtFavoriteOnClickListener());
        ViewManager.setBtFavoriteOnClickListener(new BtFavoriteOnClickListener());
        return view;
    }

    // public void initCDAnim() {
    //     cdAmination = AnimationUtils.loadAnimation(getActivity(), R.anim.music_cd_anim);
    //     cdAmination.setInterpolator(new LinearInterpolator());
    // }

    // private void testLoadCover(int i) {
    //     Bitmap bitmap = BitmapFactory.decodeFile(songs.get(i).getPicpath());
    //     Utils.loadCover(bitmap, iv_cover, iv_reflection, iv_smallCover);
    //
    // }

    // private void initView(View view) {
    //     mlvcurl = (ListView) view.findViewById(R.id.lv_curl);
    //     mtvCount = (TextView) view.findViewById(R.id.tv_count);
    //     mbtPlaying = (ImageButton) view.findViewById(R.id.bt_playing);
    //     mbtFavorite = (ImageButton) view.findViewById(R.id.bt_favorite);
    //     mbtNextSong = (ImageButton) view.findViewById(R.id.bt_next);
    //     mbtModol = (ImageButton) view.findViewById(R.id.bt_mode);
    //     mbtLastSong = (ImageButton) view.findViewById(R.id.bt_previous);
    //     mtvSongName = (TextView) view.findViewById(R.id.tv_title);
    //     mtvsSinger = (TextView) view.findViewById(R.id.tv_artist);
    //     lvGeci = (ListView) view.findViewById(R.id.lv_lyric);
    //     mtvZuoQu = (TextView) view.findViewById(R.id.tv_composer);
    //     mtvWriter = (TextView) view.findViewById(R.id.tv_writer);
    //     sb_seek = (SeekBar) view.findViewById(R.id.sb_progress);
    //     ivCd = (ImageView) view.findViewById(R.id.iv_changpian);
    //
    //     iv_cover = (ImageView) view.findViewById(R.id.iv_cover);
    //     iv_reflection = (ImageView) view.findViewById(R.id.iv_reflection);
    //     iv_smallCover = (ImageView) view.findViewById(R.id.iv_smallcover);
    //
    // }

    /*private void changeView(int i) {
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

    }*/

    /**
     * @return 往song中加歌曲
     */
    private List<Song> scanDisk() {
        int k = 4;
        List<Song> songs = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            Song song1 = new Song(title, singer, objectview, writer, zuoqu, ssrc, picpath, lyricPath);
            song1.setSinger("BAMBOO");
            song1.setTitle("让他走");
            String[] tempobjectView = new String[]{"他还是走了对吗 ",
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
            song1.setSsrc("/sdcard/Music/BAMBOO - 让他走.mp3");
            song1.setPicpath("/sdcard/Music/BAMBOO - 让他走.png");
            songs.add(song1);
            Song song2 = new Song(title, singer, objectview, writer, zuoqu, ssrc, picpath, lyricPath);
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
            song2.setSsrc("/sdcard/Music/Jocelyn Pook Russian Red - Loving Strangers.mp3");
            song2.setPicpath("/sdcard/Music/Jocelyn Pook Russian Red - Loving Strangers.png");
            songs.add(song2);
            Song song3 = new Song(title, singer, objectview, writer, zuoqu, ssrc, picpath, lyricPath);
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
            song3.setSsrc("/sdcard/Music/Nuo. - 天气之子-グランドエスケープ（Cover：RADWIMPS）.mp3");
            song3.setPicpath("/sdcard/Music/Nuo. - 天气之子-グランドエスケープ（Cover：RADWIMPS）.png");
            songs.add(song3);
            Song song4 = new Song(title, singer, objectview, writer, zuoqu, ssrc, picpath, lyricPath);
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
            song4.setSsrc("/sdcard/Music/黒木渚 - カルデラ.mp3");
            song4.setPicpath("/sdcard/Music/黒木渚 - カルデラ.png");
            songs.add(song4);
            Song song5 = new Song(title, singer, objectview, writer, zuoqu, ssrc, picpath, lyricPath);
            song5.setSinger("相对论");
            song5.setTitle("最后的晚餐");
            String[] tempobjectView5 = new String[]{"灯光下酒杯在旋转 酒杯里有半只香烟 香烟里有欲望蔓延"
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
            song5.setSsrc("/sdcard/Music/相对论 - 最后的晚餐.mp3");
            song5.setPicpath("/sdcard/Music/相对论 - 最后的晚餐.png");
            songs.add(song5);
        }


        return songs;
    }


    // *
    //  * 歌词的adapter
    //  */
    // /*private class LyricAdapter extends BaseAdapter {*/
    //     @Override
    //     public int getCount() {
    //         return lyricList.size();
    //     }

        // @Override
        // public Object getItem(int i) {
        //     return null;
        // }

        // @Override
        // public long getItemId(int i) {
        //     return 0;
        // }

//         @Override
//         public View getView(int position, View convertView, ViewGroup parent) {
//             ViewHolderLyric viewholder = null;
//             if (convertView == null) {
//                 convertView = View.inflate(getActivity(), R.layout.item_lyric, null);
//                 viewholder = new ViewHolderLyric();
//                 viewholder.lyric = convertView.findViewById(R.id.tv_lyric);
//                 convertView.setTag(viewholder);
//             } else {
//                 viewholder = (ViewHolderLyric) convertView.getTag();
//             }
//             viewholder.lyric.setText(lyricList.get(position).get("lyric"));
// //            viewholder.lyric.setTextColor(#FFFFFF);
//             if (position == 0) {
//                 //蓝色
//                 viewholder.lyric.setTextColor(0xff01B8F9);
//             } else {
//                 //白色
//                 viewholder.lyric.setTextColor(0xffffffff);
//             }
//             return convertView;
//         }


    //     private class ViewHolderLyric {
    //         public TextView lyric;
    //     }
    // }

}

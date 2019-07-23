package com.example.mediaplayer;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
    private ListView mlvcurl;
    private List<HashMap<String ,String>> contactsList;
    private List<HashMap<String, String>> lyricList;
    private TextView tv_count;
    int position =999;
    private ListView lvGeci;
    private String[] objectView = new String[]{"爱像一阵风", "吹完它就走", "这样的节奏", "谁都无可奈何", "没有你以后","我灵魂失控"};


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
        View view = inflater.inflate(R.layout.fragment_right,container,false);
        mlvcurl = (ListView)view.findViewById(R.id.lv_curl);
        tv_count=(TextView)view.findViewById(R.id.tv_count);
        //朱远帆----歌词
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



        contactsList =new ArrayList<>();
//        HashMap<String, String>  map = new HashMap<>();
        for(int i=0;i<30;i++)
        {
            HashMap<String, String> map = new HashMap<>();
            map.put("singer","周杰伦");
            map.put("title","世界末日");
            contactsList.add(map);
        }

       MyAdapter myadapter = new MyAdapter();
        mlvcurl.setAdapter(myadapter);
        mlvcurl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyAdapter.ViewHolder viewHolder = (MyAdapter.ViewHolder)view.getTag();

                    i=i+1;
                 tv_count.setText(i+"/10");

                Toast.makeText(getActivity(), "click"+i, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void lyricShow() {



    }

    private class MyAdapter extends BaseAdapter {

        private ViewHolder viewHolder;

        @Override
        public int getCount() {
            return contactsList.size();
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
            if(view==null) {
                viewHolder = new ViewHolder();
                view = View.inflate(getActivity(), R.layout.item_curl,null);
                viewHolder.singer = view.findViewById(R.id.tv_singer);
                viewHolder.title = view.findViewById(R.id.tv_title);
                viewHolder.status = view.findViewById(R.id.iv_status);
                view.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.title.setText(contactsList.get(i).get("title"));

            viewHolder.singer.setText(contactsList.get(i).get("singer"));
            return  view;
        }

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

            return convertView;

        }

        private class ViewHolderLyric {
            public TextView lyric;
        }
    }
}

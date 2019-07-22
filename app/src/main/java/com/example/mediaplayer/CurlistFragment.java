package com.example.mediaplayer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurlistFragment extends Fragment {
    private ListView mlvcurl;
    private List<HashMap<String ,String>> contactsList;
    // TODO: Rename parameter arguments, choose names that match


    public CurlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CurlistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurlistFragment newInstance() {
        CurlistFragment fragment = new CurlistFragment();
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
        View view = inflater.inflate(R.layout.fragment_curlist,container,false);
        mlvcurl = (ListView)view.findViewById(R.id.lv_curl);
        contactsList =new ArrayList<>();
//        HashMap<String, String>  map = new HashMap<>();
        for(int i=0;i<30;i++)
        {
            HashMap<String, String> map = new HashMap<>(5);
            map.put("title","歌曲名"+i);
            map.put("singer","歌手"+i);
            contactsList.add(map);
        }
        MyAdapter myadapter = new MyAdapter();
        mlvcurl.setAdapter(myadapter);
        return view;
    }

    private class MyAdapter extends BaseAdapter {
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
            ViewHolder viewHolder = null;
            if(view==null) {

                viewHolder = new ViewHolder();
                view = View.inflate(getActivity(), R.layout.item_curl, null);
                viewHolder.title = view.findViewById(R.id.tv_title);
                viewHolder.singer = view.findViewById(R.id.tv_singer);
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
            public TextView title;
            public TextView singer;
        }
    }
}

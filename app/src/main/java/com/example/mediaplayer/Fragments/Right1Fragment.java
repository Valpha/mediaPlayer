package com.example.mediaplayer.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mediaplayer.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link Right1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author ycn
 */
public class Right1Fragment extends Fragment {
    private TextView tvContent;

    public Right1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment fragment_navibar.
     */
    public static Right1Fragment newInstance() {
        Right1Fragment fragment = new Right1Fragment();
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
        View view = inflater.inflate(R.layout.fragment_right1, container, false);
        tvContent = view.findViewById(R.id.tv_content);
        return view;
    }

    public void changText(String fragment2text) {
        tvContent.setText(fragment2text);
    }
}

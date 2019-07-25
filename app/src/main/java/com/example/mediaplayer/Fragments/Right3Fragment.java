package com.example.mediaplayer.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mediaplayer.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link Right3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author ycn
 */
public class Right3Fragment extends Fragment {
    private TextView tvContent3;

    public Right3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LeftFragment.
     */
    public static Right3Fragment newInstance() {
        Right3Fragment fragment = new Right3Fragment();
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
        View view = inflater.inflate(R.layout.fragment_right3, container, false);
        tvContent3 = view.findViewById(R.id.tv_content3);
        return view;
    }


}

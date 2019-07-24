package com.example.mediaplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link Right5Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Right5Fragment extends Fragment {
    private TextView tvContent5;

    public Right5Fragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LeftFragment.
     */
    public static Right5Fragment newInstance() {
        Right5Fragment fragment = new Right5Fragment();
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
        View view =inflater.inflate(R.layout.fragment_right5, container, false);
        tvContent5 = view.findViewById(R.id.tv_content5);
        return view;
    }


}

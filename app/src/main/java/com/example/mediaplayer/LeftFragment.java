package com.example.mediaplayer;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftFragment extends Fragment {
   // private RadioGroup mLeftSelect;
  //  private OnChoiceChangedLisenter mListner;


    public LeftFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LeftFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeftFragment newInstance() {
        LeftFragment fragment = new LeftFragment();
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
        return inflater.inflate(R.layout.fragment_left, container, false);
    }
//    /**
//     * 设置监听状态变化的接口
//     * @param listner 监听器对象
//     */
//    public void  setOnChoiceChangedLisenter(OnChoiceChangedLisenter listner){
//        if(listner instanceof OnChoiceChangedLisenter){
//            mListner = listner;
//        } else {
//            throw new RuntimeException("提供的接口不标准");
//        }
//    }
//    /**
//     * 设置监听选择变化的接口
//     */
//    public interface OnChoiceChangedLisenter{
//        /**
//         * 状态变化的重写方法
//         * @param index 监听变化的序号
//         */
//        void onChoiceChanged(int index);
//    }


}


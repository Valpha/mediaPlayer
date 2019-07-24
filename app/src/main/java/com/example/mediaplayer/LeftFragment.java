package com.example.mediaplayer;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RightFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author ycn
 */
public class LeftFragment extends Fragment {
    private RadioGroup mLeftSelect;
    private OnChoiceChangedLisenter mListner;

    /**
     * 用于创建新的fragment
     */
    public LeftFragment() {

    }


    /**
     * @return fragment
     */
    public static LeftFragment newInstance() {
        LeftFragment fragment = new LeftFragment();
        return fragment;
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * @param inflater           打气筒
     * @param container          容器
     * @param savedInstanceState 保存当前状态
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        mLeftSelect = view.findViewById(R.id.rg_leftselect);
        mLeftSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //通过checkedId可以判定是哪个RadioButton被选中了
                switch (checkedId) {
                    case R.id.rb_playing:

                        if (mListner != null) {
                            mListner.onChoiceChanged(0);
                        }
                        break;
                    case R.id.rb_webmusic:

                        if (mListner != null) {
                            mListner.onChoiceChanged(1);
                        }
                        break;
                    case R.id.rb_localmusic:
                        if (mListner != null) {
                            mListner.onChoiceChanged(2);
                        }
//                        fragment2text = "本地音乐";
                        break;
                    case R.id.rb_connecttype:
                        if (mListner != null) {
                            mListner.onChoiceChanged(3);
                        }
//                        fragment2text = "连接方式";
                        break;
                    case R.id.rb_playlist:
                        if (mListner != null) {
                            mListner.onChoiceChanged(4);
                        }
//                        fragment2text = "播放列表";
                        break;
                    case R.id.rb_myFavorite:
                        if (mListner != null) {
                            mListner.onChoiceChanged(5);
                        }
//                        fragment2text = "我的收藏";
                        break;
                    default:
                        break;
                }

            }
        });
        return view;
    }

    /**
     * 设置监听状态变化的接口
     *
     * @param listner 监听器对象
     */
    public void setOnChoiceChangedLisenter(OnChoiceChangedLisenter listner) {
        if (listner instanceof OnChoiceChangedLisenter) {
            mListner = listner;
        } else {
            throw new RuntimeException("提供的接口不标准");
        }
    }


    /**
     * 设置监听选择变化的接口
     */
    public interface OnChoiceChangedLisenter {
        /**
         * 状态变化的重写方法
         *
         * @param index 监听变化的序号
         */
        void onChoiceChanged(int index);

    }
}


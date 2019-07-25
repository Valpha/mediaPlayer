package com.example.mediaplayer;

import android.graphics.Bitmap;
import android.widget.ImageView;

import static com.example.mediaplayer.ImageUtils.*;


/**
 * @author Valpha
 */
public class Utils {

    public Utils() {
        return;
    }

    /**
     * @param cover               封面图,Bitmap 格式
     * @param coverImageView      要更新的控件
     * @param reflectionImageView 倒影对应的控件
     * @param smallImageView      唱片中心的小图
     */
    public static void loadCover(Bitmap cover, ImageView coverImageView, ImageView reflectionImageView, ImageView smallImageView) {

        Bitmap mCover = zoomBitmap(cover, 190, 190);

        coverImageView.setImageBitmap(mCover);

        Bitmap mReflection = ReflectionImageWithOutOrigin(cover);
        reflectionImageView.setImageBitmap(mReflection);

        Bitmap mSmallCover = zoomBitmap(cover, 70, 70);
        mSmallCover = RoundedCornerBitmap(mSmallCover, 35);
        smallImageView.setImageBitmap(mSmallCover);
    }

    /**
     * 舍弃该方法！
     *
     * @param currentDuration 当前时长
     * @param totalDuration   总时长
     * @return 当前百分比 int 类型
     */
    public static int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage = (double) 0;
        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        // return percentage
        return percentage.intValue();
    }


    /**
     * 播放状态写为“播放”
     */
    public static void writeStatusPlay() {
        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
        sharedPreferencesUtils.writeBoolean(Contract.SHAREDPREFERENCES_PLAYINGSTATUS, true);
    }

    /**
     * 播放状态写为“停止”
     */
    public static void writeStatusStop() {
        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
        sharedPreferencesUtils.writeBoolean(Contract.SHAREDPREFERENCES_PLAYINGSTATUS, false);
    }

    /**
     * @return 当前播放状态
     */
    public static boolean getPlayingStatus() {
        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
        return sharedPreferencesUtils.readBoolean(Contract.SHAREDPREFERENCES_PLAYINGSTATUS);
    }


    /**
     * @param progress 写入当前播放进度
     */
    public static void writeProgress(int progress) {
        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
        sharedPreferencesUtils.writeInt(Contract.SHAREDPREFERENCES_PLAYINGPROGRESS, progress);
    }

    /**
     * @return 当前播放进度
     */
    public static int getProgress() {
        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
        return sharedPreferencesUtils.readInt(Contract.SHAREDPREFERENCES_PLAYINGPROGRESS);
    }


    /**
     * @param order 存入当前播放次序
     */
    public static void writeCurrentOrder(int order) {
        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
        sharedPreferencesUtils.writeInt(Contract.SHAREDPREFERENCES_CURRENTORDER, order);
    }

    /**
     * @return 当前播放次序
     */
    public static int getCurrentOrder() {
        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
        return sharedPreferencesUtils.readInt(Contract.SHAREDPREFERENCES_CURRENTORDER);
    }
}

package com.example.mediaplayer;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.mediaplayer.ImageUtils.*;


public class Utils {

    public Utils() {
        return;
    }

    /**
     * @param cover         封面图,Bitmap 格式
     * @param iv_cover      要更新的控件
     * @param iv_ref        倒影对应的控件
     * @param iv_smallCover 唱片中心的小图
     */
    public static void loadCover(Bitmap cover, ImageView iv_cover, ImageView iv_ref, ImageView iv_smallCover) {

        Bitmap _cover = zoomBitmap(cover, 190, 190);

        iv_cover.setImageBitmap(_cover);

        Bitmap _reflection = ReflectionImageWithOutOrigin(cover);
        iv_ref.setImageBitmap(_reflection);

        Bitmap _smallCover = zoomBitmap(cover, 70, 70);
        _smallCover = RoundedCornerBitmap(_smallCover, 35);
        iv_smallCover.setImageBitmap(_smallCover);
    }

    /**
     * @param currentDuration 当前时长
     * @param totalDuration 总时长
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

}

package com.example.mediaplayer;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.mediaplayer.ImageUtils.*;


public class Utils {

    public Utils() {
    return;
    }

    public static void loadCover(Bitmap cover, ImageView iv_cover, ImageView iv_ref, ImageView iv_smallCover){

        Bitmap _cover = zoomBitmap(cover,190,190);
        iv_cover.setImageBitmap(_cover);

        Bitmap _reflection = ReflectionImageWithOutOrigin(cover);
        iv_ref.setImageBitmap(_reflection);

        Bitmap _smallCover = RoundedCornerBitmap(cover, 20);
        iv_smallCover.setImageBitmap(_smallCover);

    }
    public static int getProgressPercentage(long currentDuration,
                                            long totalDuration) {
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        // return percentage
        return percentage.intValue();
    }

}
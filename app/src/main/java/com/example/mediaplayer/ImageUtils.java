package com.example.mediaplayer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;

/**
 * 图片处理工具类（缩放，圆角，倒影）
 */
public class ImageUtils {
    /**
     * 图片缩放
     *
     * @param pBitmap
     * @param pW
     * @param pH
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap pBitmap, int pW, int pH) {
        // 获取原图的宽
        int _width = pBitmap.getWidth();
        // 获取原图的高
        int _heigth = pBitmap.getHeight();

        // 获取缩放比
        float _ScaleW = (float) pW / _width;
        float _ScaleH = (float) pH / _heigth;
        // 创建Matrix矩阵对象
        Matrix _Matrix = new Matrix();
        // 设置宽高的缩放比
        _Matrix.setScale(_ScaleW, _ScaleH);
        // 对截原图的0，0坐标到_width，_heigth的图片进行_Matrix处理
        return Bitmap.createBitmap(pBitmap, 0, 0, _width, _heigth, _Matrix,true);
    }

    /**
     * 图片圆角
     *
     * @param pBitmap
     * @param pRoundpx
     * @return
     */
    public static Bitmap RoundedCornerBitmap(Bitmap pBitmap, float pRoundpx) {
        // 创建图片画布大小
        Bitmap _NewBitmap = Bitmap.createBitmap(pBitmap.getWidth(), pBitmap.getHeight(), Config.ARGB_8888);
        // 创建画布
        Canvas _Canvas = new Canvas(_NewBitmap);
        // 设置画布透明
        _Canvas.drawARGB(0, 0, 0, 0);
        // 创建画笔
        Paint _Paint = new Paint();
        // 抗锯齿
        _Paint.setAntiAlias(true);
        // 画笔颜色透明
        _Paint.setColor(0xff000000);

        // 画与原图片大小一致的圆角矩形
        Rect _Rect = new Rect(0, 0, pBitmap.getWidth(), pBitmap.getHeight());
        RectF _RectF = new RectF(_Rect);
        _Canvas.drawRoundRect(_RectF, pRoundpx, pRoundpx, _Paint);
        // 设置下面张图片与上面张图片的交互模式
        _Paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        // 画原图到画布
        _Canvas.drawBitmap(pBitmap, _Rect, _Rect, _Paint);
        return _NewBitmap;
    }

    /**
     * 图片倒影
     *
     * @param pBitmap
     * @return
     */
    public static Bitmap ReflectionImageWithOrigin(Bitmap pBitmap) {
        // 创建等宽，高+高/5的画布
        Bitmap _NewBitmap = Bitmap.createBitmap(pBitmap.getWidth(),
                pBitmap.getHeight() + pBitmap.getHeight() / 5,
                Config.ARGB_8888);
        Canvas _Canvas = new Canvas(_NewBitmap);
        // 画上原图
        _Canvas.drawBitmap(pBitmap, 0, 0, null);

        // 原图翻转，
        Matrix _Matrix = new Matrix();
        _Matrix.preScale(1, -1);
        Bitmap _Bitmap = Bitmap.createBitmap(pBitmap, 0, 0, pBitmap.getWidth(), pBitmap.getHeight(), _Matrix, true);

        // 在剩余画布上画上翻转图
        _Canvas.drawBitmap(_Bitmap, 0, pBitmap.getHeight(), null);
        Paint _Paint = new Paint();

        // 实现图片的渐变效果
        LinearGradient shader = new LinearGradient(0, pBitmap.getHeight(), 0,
                _NewBitmap.getHeight(), 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
        _Paint.setShader(shader);
        _Paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        _Canvas.drawRect(0, pBitmap.getHeight(), pBitmap.getWidth(), _NewBitmap.getHeight(), _Paint);
        return _NewBitmap;
    }

    /**
     * 图片倒影
     *
     * @param pBitmap
     * @return
     */
    public static Bitmap ReflectionImageWithOutOrigin(Bitmap pBitmap) {
        // 创建等宽，高+高/5的画布
        Bitmap _NewBitmap = Bitmap.createBitmap(pBitmap.getWidth(), pBitmap.getHeight() / 3, Config.ARGB_8888);
        Canvas _Canvas = new Canvas(_NewBitmap);

        // _Canvas.drawBitmap(pBitmap, 0, 0, null);// 画上原图

        // 原图翻转
        Matrix _Matrix = new Matrix();
        _Matrix.preScale(1, -1);
        Bitmap _Bitmap = Bitmap.createBitmap(pBitmap, 0, 0, pBitmap.getWidth(), pBitmap.getHeight(), _Matrix, true);

        // 在剩余画布上画上翻转图
        _Canvas.drawBitmap(_Bitmap, 0, 0, null);
        Paint _Paint = new Paint();

        // 实现图片的渐变效果
        LinearGradient shader = new LinearGradient(0, 0, 0,
                _NewBitmap.getHeight(), 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
        _Paint.setShader(shader);
        _Paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        _Canvas.drawRect(0, 0, pBitmap.getWidth(), _NewBitmap.getHeight(), _Paint);
        return _NewBitmap;
    }

}
package com.example.administrator.myall.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.administrator.myall.R;

/**
 * Created by Administrator on 2017/10/29 0029.
 */

public class StripMeiZi extends View {
    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    private Canvas mCanvas,mainCanvas;
    private Bitmap mBeforeBitmap;
    private Bitmap mBackBitmap;
    private int mLastX,mLastY;
    private int screenW, screenH; //屏幕宽高
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);


    public StripMeiZi(Context context) {
        this(context, null);
    }
    /**
     * 获取屏幕宽高，sdk17后不建议采用
     *
     * @param context
     */
    public static int[] getScreenHW(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        int[] HW = new int[] { width, height };
        return HW;
    }

    /**
     * 获取屏幕宽高，建议采用
     *
     * @param context
     */
    public static int[] getScreenHW2(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int[] HW = new int[] { width, height };
        return HW;
    }

    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    public static int getScreenW(Context context) {
        return getScreenHW2(context)[0];
    }

    /**
     * 获取屏幕的高度
     *
     * @param context
     * @return
     */
    public static int getScreenH(Context context) {
        return getScreenHW2(context)[1];
    }
    public StripMeiZi(Context context, AttributeSet attrs) {
        super(context, attrs);
        screenW = StripMeiZi.getScreenW(context);
        screenH = StripMeiZi.getScreenH(context);
        init();
    }


    public StripMeiZi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        //背后图片，这里让它全屏
        mBackBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mv1);
        mBackBitmap = Bitmap.createScaledBitmap(mBackBitmap, screenW, screenH, false);
        //前面的图片，并绘制到Canvas上
        mBeforeBitmap = Bitmap.createBitmap(screenW, screenH, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBeforeBitmap);
        mCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
                R.mipmap.mv2), null, new RectF(0, 0, screenW, screenH), null);//前景图画在了mCanvas上面
        //画笔相关的设置
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND); // 圆角
        mPaint.setStrokeCap(Paint.Cap.ROUND); // 圆角
        mPaint.setStrokeWidth(80);    // 设置画笔宽
    }

    private void drawPath() {
        mPaint.setXfermode(mXfermode);
        mCanvas.drawPath(mPath, mPaint);//mCanvas上面的前景图与路径合成镂空图片
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mainCanvas = canvas;
        canvas.drawBitmap(mBackBitmap, 0, 0, null);//将背景图片画到屏幕上
        drawPath();
        canvas.drawBitmap(mBeforeBitmap, 0, 0, null);//将镂空图片画到屏幕上面
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:

                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);

                if (dx > 3 || dy > 3)
                    mPath.lineTo(x, y);

                mLastX = x;
                mLastY = y;
                break;
        }
        invalidate();
        return true;
    }
}

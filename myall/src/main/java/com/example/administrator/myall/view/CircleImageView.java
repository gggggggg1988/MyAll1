package com.example.administrator.myall.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.example.administrator.myall.R;
import com.lidroid.xutils.util.LogUtils;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/10/29 0029.
 */

/**
 * <com.jay.xfermodedemo1.CircleImageView
 android:layout_width="160dp"
 android:layout_height="280dp"
 android:layout_margin="10dp"
 android:src="@mipmap/ic_bg_meizi1"
 app:Radius="30dp"
 app:type="round" />
 */

public class CircleImageView extends android.support.v7.widget.AppCompatImageView {
    private Paint mPaint;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private Bitmap mMaskBitmap;
    private WeakReference<Bitmap> mWeakBitmap;

    //图片相关的属性
    private int type;                           //类型，圆形或者圆角
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;
    private static final int BODER_RADIUS_DEFAULT = 10;     //圆角默认大小值
    private int mBorderRadius;                  //圆角大小


    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //取出attrs中我们为View设置的相关值
        TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        mBorderRadius = tArray.getDimensionPixelSize(R.styleable.CircleImageView_Radius, BODER_RADIUS_DEFAULT);
        type = tArray.getInt(R.styleable.CircleImageView_type, TYPE_CIRCLE);
        tArray.recycle();
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (type == TYPE_CIRCLE) {
            int width = Math.min(getMeasuredWidth(), getMeasuredHeight());
            setMeasuredDimension(width, width);    //设置当前View的大小
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //在缓存中取出bitmap
        Bitmap bitmap = mWeakBitmap == null ? null : mWeakBitmap.get();
        if (bitmap == null || bitmap.isRecycled()) {
            //获取图片宽高
            Drawable drawable = getDrawable();
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            LogUtils.i("intrinsic width----"+width);
            LogUtils.i("intrinsic height----"+height);

            LogUtils.i(" width----"+getWidth());
            LogUtils.i(" height----"+getHeight());

            if (drawable != null) {
                bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                Canvas drawCanvas = new Canvas(bitmap);
                float scale = 1.0f;
                if (type == TYPE_ROUND) {
                    scale = Math.max(getWidth() * 1.0f / width, getHeight()
                            * 1.0f / height);
                } else {
                    scale = getWidth() * 1.0F / Math.min(width, height);
                }
                //根据缩放比例，设置bounds，相当于缩放图片了
                drawable.setBounds(0, 0, (int) (scale * width),
                        (int) (scale * height));

                drawable.draw(drawCanvas);//bitmap与drawCanvas连在一起了  drauable内部的图片已经画在了drawCanvas上了
                if (mMaskBitmap == null || mMaskBitmap.isRecycled()) {
                    mMaskBitmap = getBitmap();
                }

                mPaint.reset();
                mPaint.setFilterBitmap(false);
                mPaint.setXfermode(mXfermode);

                //绘制形状
                drawCanvas.drawBitmap(mMaskBitmap, 0, 0, mPaint);//将bitmap与mMaskBitmap画在同一个drawCanvas上面，通过mPaint的xfermode发生反应，形成一张新的bitmap

                //bitmap缓存起来，避免每次调用onDraw，分配内存
                mWeakBitmap = new WeakReference<Bitmap>(bitmap);

                //绘制图片
                canvas.drawBitmap(bitmap, 0, 0, null);//将上面反应生成的bitmap画在屏幕上
                mPaint.setXfermode(null);

            }
        }
        if (bitmap != null) {
            mPaint.setXfermode(null);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint);
            return;
        }
    }

    //缓存Bitmap，避免每次OnDraw都重新分配内存与绘图
    @Override
    public void invalidate() {
        mWeakBitmap = null;
        if (mWeakBitmap != null) {
            mMaskBitmap.recycle();
            mMaskBitmap = null;
        }
        super.invalidate();
    }

    //定义一个绘制形状的方法

    private Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);   //抗锯齿
        paint.setColor(Color.BLACK);
        if (type == TYPE_ROUND) {
            canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()),
                    mBorderRadius, mBorderRadius, paint);
        } else {
            canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, paint);
        }



        return bitmap;
    }
}

package com.example.administrator.myall.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.administrator.myall.R;

/**
 * Created by Administrator on 2018/1/13 0013.
 */

public class BouncingView extends LinearLayout {

    private Paint mPaint;
    private int mMaxArcHeight;
    private Path mPath;
    private int animateValue;
    private Status mStatus;

    public BouncingView(Context context) {
        super(context);
        init();
    }

    public BouncingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BouncingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
        mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        mMaxArcHeight = getResources().getDimensionPixelSize(R.dimen.arc_max_height);
    }

    public void show() {
        mStatus = Status.STATUS_UP;
        ValueAnimator animator = ValueAnimator.ofInt(0, mMaxArcHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animateValue = (int) animation.getAnimatedValue();
//                LogUtils.i("animation value show----------"+animateValue);

                if (mMaxArcHeight==animateValue ) {
                    bounce();
                }
                invalidate();

            }
        });

        animator.setDuration(500);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currentPointY = 0;
//        LogUtils.i("mStatus---cal "+mStatus);
        switch (mStatus) {
            case NONE:
                currentPointY = 0;
                break;
            case STATUS_DOWN:
                currentPointY = mMaxArcHeight;
                break;
            case STATUS_UP:

                currentPointY = (int) ((1 - ((float)animateValue / mMaxArcHeight)) * getHeight()+mMaxArcHeight);
//                LogUtils.i("currentY---cal "+currentPointY);
                break;
            default:
                break;
        }
        mPath.reset();
//        LogUtils.i("currentY--- "+currentPointY);
        mPath.moveTo(0, currentPointY);
        mPath.quadTo(getWidth() / 2, currentPointY - animateValue, getWidth(), currentPointY);
        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(0, getHeight());
        mPath.close();

        canvas.drawPath(mPath, mPaint);

    }

    enum Status {
        NONE(0),
        STATUS_UP(1),
        STATUS_DOWN(2);
        int status;

        Status(int status) {
            this.status = status;
        }
    }
    public void bounce(){
        mStatus = Status.STATUS_DOWN;
        ValueAnimator animator = ValueAnimator.ofInt(mMaxArcHeight, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animateValue = (int) animation.getAnimatedValue();
//                LogUtils.i("animation value----------"+animateValue);
                invalidate();
            }
        });
        animator.setDuration(800);
        animator.start();
    }
}

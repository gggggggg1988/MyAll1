package com.example.administrator.myall.activitys;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.lidroid.xutils.util.LogUtils;

/**
 * Created by Administrator on 2017/10/28 0028.
 */

public class MyRelativeLayout extends RelativeLayout {
    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int modeH = MeasureSpec.getMode(heightMeasureSpec);

        if (modeW==MeasureSpec.AT_MOST) {
            LogUtils.i("modew-------"+1);
        }else if(modeW==MeasureSpec.EXACTLY){
            LogUtils.i("modew-------"+2);
        }else {
            LogUtils.i("modew-------"+3);
        }

        if (modeH==MeasureSpec.AT_MOST) {
            LogUtils.i("modeh-------"+1);
        }else if(modeH==MeasureSpec.EXACTLY){
            LogUtils.i("modeh-------"+2);
        }else {
            LogUtils.i("modeh-------"+3);
        }

        LogUtils.i("modeH-------"+modeH);
        LogUtils.i("modew-------"+modeW);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        LogUtils.i("H-------"+width);
        LogUtils.i("w-------"+height);
    }
}

package com.example.administrator.myall.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myall.R;

/**
 * Created by cantian on 2018/1/25.
 */

public class Clock extends View {
    private Paint mPaint;

    public Clock(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);          //抗锯齿
        mPaint.setColor(getResources().getColor(R.color.puple));//画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //画笔风格
        mPaint.setTextSize(36);             //绘制文字大小，单位px
        mPaint.setStrokeWidth(5);           //画笔粗细
    }

    public Clock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Clock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawTest(canvas);

        drawClock(canvas);
    }

    private void drawTest(Canvas canvas) {
        canvas.drawArc(new RectF(0, 0, 200, 100),0,90,true,mPaint);  //绘制弧形区域 从零开始顺时针扫90度  布尔值改为false的话就只剩下圆弧段

        Path path = new Path();
        path.moveTo(50,150);
        path.lineTo(100, 200);
        path.lineTo(200, 300);
        path.lineTo(300, 400);
        path.close();
        canvas.drawTextOnPath("最喜欢看曹神日狗了~", path, 50, 10, mPaint);    //绘制文字
    }


    private void drawClock(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(canvas.getWidth()/2, 200); //将位置移动画纸的坐标点:150,150
        canvas.drawCircle(0, 0, 100, mPaint); //画圆圈

        //使用path绘制路径文字
        canvas.save();
        canvas.translate(-75, -75);
        Path path = new Path();
        path.addArc(new RectF(0,0,150,150), -180, 180);
        Paint citePaint = new Paint(mPaint);
        citePaint.setTextSize(14);
        citePaint.setStrokeWidth(1);
        canvas.drawTextOnPath("绘制表盘~", path, 28, 0, citePaint);
        canvas.restore();//restore之后，坐标系圆点又回到上面的圆圈中心

        Paint tmpPaint = new Paint(mPaint); //小刻度画笔对象
        tmpPaint.setStrokeWidth(1);

        float  y=100;
        int count = 60; //总刻度数

        for(int i=0 ; i <count ; i++){
            if(i%5 == 0){
                canvas.drawLine(0f, y, 0, y+12f, mPaint);

//                canvas.drawText(String.valueOf(i/5+1), -4f, y+25f, tmpPaint);

            }else{
                canvas.drawLine(0f, y, 0f, y +5f, tmpPaint);
            }
            canvas.rotate(360/count,0f,0f); //旋转画纸
        }
        double arc = -Math.PI;
        double tx = 0;
        double ty = 0;
        double val = (2*Math.PI)/12;
        mPaint.setTextSize(14);
        for (int i = 0; i < 12; i++) {
            tx = 130* Math.cos(arc);
            ty = 130 * Math.sin(arc);
            arc+= val;
            canvas.drawText(String.valueOf(i+1), (float) tx-7, (float) ty+7, tmpPaint);
        }

        //绘制指针
        tmpPaint.setColor(Color.GRAY);
        tmpPaint.setStrokeWidth(4);
        canvas.drawCircle(0, 0, 7, tmpPaint);
        tmpPaint.setStyle(Paint.Style.FILL);
        tmpPaint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 5, tmpPaint);
        canvas.drawLine(0, 10, 0, -65, mPaint);
    }
}

package com.example.administrator.myall.activitys;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.lidroid.xutils.util.LogUtils;

/**
 * Created by Administrator on 2017/10/28 0028.
 */

public class TouchPullView extends View {
    private Paint m_paint;
    private Interpolator mProgressInterpolator = new DecelerateInterpolator();
    private int radius=50;
    private int mTargetWidth=400 ;//目标宽度
    private Path m_path =new Path();
    private Paint mPathPaint;
    private int mTargetGravityHight;//重心点高度
    //角度变化0-135度
    private  int mTangentAngle=110;
    private float pointX;
    private float pointY;
    private float progress;
    private int mDragHight=400;//拖动高度

    public void setProgress(float progress) {
        this.progress = progress;
        requestLayout();//请求重新进行测量
        LogUtils.i("progress------ "+progress);
    }

    public TouchPullView(Context context) {
        this(context,null);
        init();
    }

    private void init() {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setDither(true);
        p.setStyle(Paint.Style.FILL);
        p.setColor(0xff000000);
        m_paint = p;

        Paint p1 = new Paint();
        p1.setAntiAlias(true);
        p1.setDither(true);
        p1.setStyle(Paint.Style.FILL);
        p1.setColor(0xff000000);
        mPathPaint = p1;
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
//        context.obtainStyledAttributes(R.)
        init();
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        MeasureSpec.AT_MOST  父view会给过来一个最大的尺寸空间，子view最多只能占给过来的这么大  对应wrap_content
//         MeasureSpec.EXACTLY    父view给过来了一个尺寸，希望子view就这么大，按照这个尺寸来  对应布局里面的match_parent  和指定空间的宽高dp值的时候
//        MeasureSpec.UNSPECIFIED  父view没有指定尺寸空间限制，子view可以占据父控件所有的全部空间
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
        LogUtils.i("w-------"+width);
        LogUtils.i("h-------"+height);

        int iWidth = 2*radius+getPaddingLeft()+getPaddingRight();//最小值
        int iHight = (int) ((mDragHight*progress+0.5f)+getPaddingTop()+getPaddingBottom());//我们想要的最小的高度
        int measureWidth ,measureHight;
        if (modeW==MeasureSpec.AT_MOST) {//其实这里就是根据match_parent,wrapcontent或者指定的控件尺寸 进行一定的尺寸调整变动
            measureWidth = Math.min(iWidth, width);
        }else if(modeW==MeasureSpec.EXACTLY){
            measureWidth = width;
        }else {
            measureWidth = width;
        }

        if (modeH==MeasureSpec.AT_MOST) {
            measureHight = Math.min(height, iHight);
        }else if(modeH==MeasureSpec.EXACTLY){
            measureHight = height;
        }else {
            measureHight = height;

        }
        setMeasuredDimension(measureWidth,measureHight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int save = canvas.save();
        float transX = (getWidth()-getValueByLine(getWidth(),mTargetWidth,progress))/2;
        canvas.translate(transX,0);

        float x = getWidth()>>1;
        float y = getHeight()>>1;
        canvas.drawCircle(pointX,pointY,radius,m_paint);

        //画贝塞尔曲线
        canvas.drawPath(m_path,mPathPaint);
        canvas.restoreToCount(save);
    }

    private void  updatePathLayout(){
        final  float fprogress = mProgressInterpolator.getInterpolation(progress);
        final Path path = m_path;
        path.reset();
        final float w = getValueByLine(getWidth(), mTargetWidth, progress);
        final float h = getValueByLine(0, mDragHight, progress);
        final float cPointX= w/2;
        final float cPointY = h-radius;
        final float cRadius = radius;
        final float endControlY = mTargetGravityHight;
        pointX = cPointX;
        pointY = cPointY;
        path.moveTo(0,0);
        //左边部分的结束点和控制点
        float lEndPointX,lEndPointY;
        float lControlPointX,lControlPoingY;

        double radian = Math.toRadians(getValueByLine(0, mTangentAngle, fprogress));
        float x = (float) (Math.sin(radian)*radius);
        float y = (float) (Math.cos(radian)*radius);

        lEndPointX = cPointX-x;
        lEndPointY = cPointY+y;
//控制点y坐标变化
        lControlPoingY = getValueByLine(0, endControlY, fprogress);
        float tHight =lEndPointY-lControlPoingY ;
float tWidth = (float) (tHight/Math.tan(radian));
        lControlPointX = lEndPointX-tWidth;
        path.quadTo(lControlPointX,lControlPoingY,lEndPointX,lEndPointY);
        path.lineTo(cPointX+(cPointX-lEndPointX),lEndPointY);
        path.quadTo(cPointX+cPointX-lControlPointX,lControlPoingY,w,0);


    }
    private ValueAnimator m_animator;
    public void release(){
        LogUtils.i("release---------");
        if (m_animator == null) {
            m_animator = ValueAnimator.ofFloat(progress, 0f);
            m_animator.setInterpolator(new DecelerateInterpolator());
            m_animator.setDuration(400);
            m_animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Object val = animation.getAnimatedValue();
                    if (val instanceof  Float) {
                        setProgress((Float) val);
                    }
                }
            });

        }else {
            m_animator.cancel();//中途中断的话重新设置初始值并且开始动画
            m_animator.setFloatValues(progress,0f);//
        }
        m_animator.start();
    }
    /**
     * 获取当前值
     * @param start
     * @param end
     * @param progress
     * @return
     */
    private float getValueByLine(float start,float end,float progress){
        return  start+(end-start)*progress;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        pointX = w>>1;
//        pointY = h>>1;
        updatePathLayout();
    }
}

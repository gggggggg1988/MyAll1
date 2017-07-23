package com.example.administrator.myall;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Multi2 extends Activity {
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi2);

        imageView = (ImageView) this.findViewById(R.id.imageView);
        imageView.setOnTouchListener(new TouchListener());
    }

    private final class TouchListener implements View.OnTouchListener {
        private PointF startPoint = new PointF();
        private Matrix matrix = new Matrix();
        private Matrix currentMatrix = new Matrix();
        private int mode = 0;
        private static final int DRAG = 1;
        private static final int ZOOM = 2;
        private float startDis;//开始距离
        private PointF midPoint;//中间点

        public boolean onTouch(View v, MotionEvent event) {
            //使用switch (event.getAction())可以处理ACTION_DOWN和ACTION_UP事件;
            //使用switch (event.getAction() & MotionEvent.ACTION_MASK)就可以处理处理多点触摸的
            //ACTION_POINTER_DOWN和ACTION_POINTER_UP事件。
            //与操作(255)得到低八位
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN://手指压下屏幕
                    mode = DRAG;
                    currentMatrix.set(imageView.getImageMatrix());//记录ImageView当前的移动位置
                    startPoint.set(event.getX(), event.getY());
                    break;

                case MotionEvent.ACTION_MOVE://手指在屏幕移动，该事件会不断地触发
                    if(mode == DRAG){
                        float dx = event.getX() - startPoint.x;//得到在x轴的移动距离
                        float dy = event.getY() - startPoint.y;//得到在y轴的移动距离
                        matrix.set(currentMatrix);//在没有进行移动之前的位置基础上进行移动
                        //平移
                        matrix.postTranslate(dx, dy);
                    }else if(mode == ZOOM){//缩放
                        float endDis = distance(event);//结束距离
                        if(endDis > 10f){
                            float scale = endDis / startDis;//得到缩放倍数
                            matrix.set(currentMatrix);
                            //缩放postScale原始矩阵在右边，preScale原始矩阵在左边
                            matrix.postScale(scale, scale, midPoint.x, midPoint.y);
                        }
                    }
                    break;

                case MotionEvent.ACTION_UP://手指离开屏
                case MotionEvent.ACTION_POINTER_UP://有手指离开屏幕,但屏幕还有触点（手指）
                    mode = 0;
                    break;

                case MotionEvent.ACTION_POINTER_DOWN://当屏幕上还有触点（手指），再有一个手指压下屏幕,此时
                   // event中包含两个触点的坐标
                            mode = ZOOM;
                    startDis = distance(event);
                    //两个手指的距离像素大于10
                    if(startDis > 10f){
                        midPoint = mid(event);
                        currentMatrix.set(imageView.getImageMatrix());//记录ImageView当前的缩放倍数
                    }
                    break;
            }
            imageView.setImageMatrix(matrix);
            return true;
        }

    }
    /**
     * 计算两触点之间的距离
     * @param event
     * @return
     */
    public static float distance(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        return (float)Math.sqrt(dx*dx + dy*dy);
    }
    /**
     * 计算两触点之间的中间点
     * @param event
     * @return
     */
    public static PointF mid(MotionEvent event){
        float midX = (event.getX(1) + event.getX(0)) / 2;
        float midY = (event.getY(1) + event.getY(0)) / 2;
        return new PointF(midX, midY);
    }
}

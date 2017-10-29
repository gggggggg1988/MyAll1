package com.example.administrator.myall.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.myall.R;

public class StickyPulldown extends AppCompatActivity {

    private float mTouchMoveStartY;
    private final int TOUCH_MOVE_MAX_Y = 600;
    private TouchPullView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_pulldown);
        Intent in = getIntent();
        String title = in.getStringExtra("StickyPulldown");
        view = (TouchPullView) findViewById(R.id.pull_view);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle(title);
        findViewById(R.id.activity_main).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int act = event.getActionMasked();
                switch (act) {
                    case MotionEvent.ACTION_DOWN:
                        mTouchMoveStartY = event.getY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        view.release();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float y = event.getY();
                        if (y >= mTouchMoveStartY) {
                            float moveSize = y - mTouchMoveStartY;
                            float progress = (moveSize > TOUCH_MOVE_MAX_Y) ? 1 : moveSize / TOUCH_MOVE_MAX_Y;
                            view.setProgress(progress);
                        }
                        return true;


                    default:
                        break;
                }

                return false;
            }
        });


    }
}

package com.example.administrator.myall.activitys;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.administrator.myall.R;
import com.lidroid.xutils.util.LogUtils;

public class ChenJinshi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_chen_jinshi);

        LogUtils.i("status height-----"+getStatuBarHight(this));
    }

    private int getStatuBarHight(Context context) {
        int status_px=0;
        try {
            Class<?> clz = Class.forName("com.android.internal.R$dimen");
            Object obj = clz.newInstance();
            String status_bar_height = clz.getField("status_bar_height").get(obj).toString();
            int status_bar = Integer.parseInt(status_bar_height);
            status_px = context.getResources().getDimensionPixelSize(status_bar);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return status_px;
    }
}

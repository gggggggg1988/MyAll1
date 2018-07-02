package com.example.administrator.myall.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.myall.biz.KeepLiveActManager;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by Administrator on 2017/11/18 0018.
 */

public class KeepLiveAct extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);

        LogUtils.i("open act---------------");
        Window window = getWindow();
        window.setGravity(Gravity.LEFT|Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width=1;
        params.height = 1;
        params.x = 0;
        params.y = 0;
        window.setAttributes(params);

        KeepLiveActManager.getInstance(this).setKeepLiveAct(this);

    }

    @Override
    protected void onDestroy() {
        LogUtils.i("close act---------------");
        super.onDestroy();
    }
}

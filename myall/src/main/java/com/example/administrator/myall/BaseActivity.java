package com.example.administrator.myall;

import android.app.Activity;
import android.content.Intent;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by Administrator on 2016/5/22 0022.
 */
public class BaseActivity extends SlidingFragmentActivity {
    public void intent2Activity(Class<? extends  Activity> activity) {
        Intent intent = new Intent(this, Activity.class);
        startActivity(intent);
    }

}

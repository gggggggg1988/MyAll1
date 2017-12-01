package com.example.administrator.myall.biz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.administrator.myall.activitys.KeepLiveAct;

import java.lang.ref.SoftReference;


/**
 * Created by Administrator on 2017/11/18 0018.
 */

public class KeepLiveActManager {
    private static KeepLiveActManager instance;
    private static Context m_context;
    private SoftReference<Activity> keepLiveAct;
    private KeepLiveActManager(){

    }
    public void setKeepLiveAct(Activity act){
        keepLiveAct = new SoftReference<Activity>(act);
    }
    public static KeepLiveActManager getInstance(Context context){
        if (instance == null) {
            synchronized (KeepLiveActManager.class){
                if (instance == null) {
                    instance = new KeepLiveActManager();
                    m_context = context.getApplicationContext();
                }
            }
        }
        return instance;
    }

    public void startKeepLiveAct(){
        Intent intent = new Intent(m_context, KeepLiveAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        m_context.startActivity(intent);
    }

    public void finishKeepLiveAct(){
        if (keepLiveAct != null&&keepLiveAct.get()!=null) {
            Activity act = keepLiveAct.get();
            act.finish();
        }
    }
}

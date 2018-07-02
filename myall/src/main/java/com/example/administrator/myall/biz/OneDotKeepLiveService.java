package com.example.administrator.myall.biz;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class OneDotKeepLiveService extends Service {
    public OneDotKeepLiveService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ScreenStateListener listener = new ScreenStateListener(this, new ScreenStateListener.StateListener() {
            @Override
            public void onScreenOn() {
                KeepLiveActManager.getInstance(OneDotKeepLiveService.this).finishKeepLiveAct();
            }

            @Override
            public void onScreenOff() {
                KeepLiveActManager.getInstance(OneDotKeepLiveService.this).startKeepLiveAct();
            }

            @Override
            public void onUserPresent() {

            }
        });

        listener.begain();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

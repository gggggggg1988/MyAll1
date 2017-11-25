package com.example.administrator.myall.biz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by Administrator on 2017/11/18 0018.
 */

public class ScreenStateListener {
   private Context m_context;
    private StateListener stateListener;

    public ScreenStateListener(Context context, StateListener stateListener) {
        m_context = context;
        this.stateListener = stateListener;
    }

    interface StateListener{
       void onScreenOn();
        void onScreenOff();
        void onUserPresent();
    }

    private StateReceiver m_receiver;

    class StateReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                     case Intent.ACTION_SCREEN_ON:
                        stateListener.onScreenOn();
                         break;
                case Intent.ACTION_SCREEN_OFF:
                    stateListener.onScreenOff();
                    break;
                case Intent.ACTION_USER_PRESENT:
                    stateListener.onUserPresent();
                    break;

                     default:
                         break;
                   }

        }
    }

    public void begain(){
        m_receiver = new StateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        filter.addAction(Intent.ACTION_USER_PRESENT);

        m_context.registerReceiver(m_receiver,filter);
    }

    public void stop(){
        m_context.unregisterReceiver(m_receiver);
    }
}

package com.example.administrator.myall.service.keepAliveService;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;

import com.example.administrator.myall.R;
import com.lidroid.xutils.util.LogUtils;

public class LocalService extends Service {
    public LocalService() {
    }
    private MyBinder binder;
    private ServiceConnection connection;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return binder;
    }

    @Override
    public void onCreate() {
        binder = new MyBinder();
        connection = new ServiceConnection();
        super.onCreate();


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(this, RemoteService.class),connection,BIND_IMPORTANT);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker("双进程保活");
        builder.setContentTitle("contenttitle");
        builder.setContentText("contenttext");
        builder.setSmallIcon(R.mipmap.mv1);
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notice = builder.build();
        startForeground(startId,notice);


        return START_STICKY;
    }

    class MyBinder extends RemoteConnection.Stub{

        @Override
        public String getProcessName() throws RemoteException {
            return "RemoteService";
        }
    }

    class ServiceConnection implements android.content.ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.i("service disconnected------------");
            LocalService.this.startService(new Intent(LocalService.this, RemoteService.class));
            LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class),connection,BIND_IMPORTANT);
        }
    }

}

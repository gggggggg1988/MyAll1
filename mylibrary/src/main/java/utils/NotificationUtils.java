package utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import ndkjnidemo.wobiancao.com.mylibrary.R;

public class NotificationUtils extends ContextWrapper {
    private NotificationManager manager;
    public static final String id = "channel_1";
    public static final String name = "channel_name_1";
    private static NotificationUtils instance;
    private Notification.Builder m_builder;
    private static  int downLoadChanel=2;

    private NotificationUtils(Context context) {
        super(context);
    }

    public static NotificationUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (NotificationUtils.class) {
                if (instance == null) {
                    instance = new NotificationUtils(context);
                }
            }
        }
        return instance;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder getChannelNotification(String title, String content,int iconRes, PendingIntent intent) {
        m_builder = new Notification.Builder(getApplicationContext(), id);
        if (intent != null) {
            m_builder.setContentIntent(intent);
        }
        return m_builder.setContentTitle(title)
//                .setSubText("subtext---")
//                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .setContentText(content)
//                .setTicker("ticker----")
                .setWhen(System.currentTimeMillis())
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),android.R.drawable.ic_delete))
                .setSmallIcon(iconRes)
                .setAutoCancel(true);
    }

    public Notification.Builder getNotification_25(String title, String content,int iconRes, PendingIntent intent) {
         m_builder = new Notification.Builder(getApplicationContext());
        if (intent != null) {
            m_builder.setContentIntent(intent);
        }
        return m_builder.setContentTitle(title)
//                .setSubText("subtext---")
//                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .setContentText(content)

//                .setTicker("收到叶良辰发送过来的信息~")
                .setWhen(System.currentTimeMillis())
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_delete))
                .setSmallIcon(iconRes)
                .setContentIntent(intent)
                .setAutoCancel(true);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void sendNotification(String title, String content, int iconRes, PendingIntent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel();
            Notification notification = getChannelNotification
                    (title, content,iconRes,intent).build();
            getManager().notify(1, notification);
        } else {
            Notification notification = getNotification_25(title, content,iconRes,intent).build();
            getManager().notify(1, notification);
        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public NotificationUtils createDownloadNotification(){
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel();
            Notification notification = getChannelNotification("下载", "正在下载", android.R.drawable.ic_delete,null).build();
            getManager().notify(downLoadChanel, notification);
        } else {
            Notification notification = getNotification_25("下载", "正在下载", android.R.drawable.ic_delete,null).build();
            getManager().notify(downLoadChanel, notification);
        }

        return this;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setProgress(int progress){
        if (progress<100) {

            m_builder.setProgress(100, progress, false);
            manager.notify(downLoadChanel,m_builder.build());
             //下载进度提示
            m_builder.setContentText("下载"+progress+"%");
        }else{
            //下载完成后更改标题以及提示信息
            m_builder.setContentTitle("开始安装");
            m_builder.setContentText("安装中...");
            //设置进度为不确定，用于模拟安装
            m_builder.setProgress(0,0,true);
            manager.notify(downLoadChanel,m_builder.build());

        }

    }
}

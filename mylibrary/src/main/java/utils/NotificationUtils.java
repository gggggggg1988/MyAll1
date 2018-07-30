package utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

public class NotificationUtils extends ContextWrapper {
    private NotificationManager manager;
    public static final String id = "channel_1";
    public static final String name = "channel_name_1";
    private static NotificationUtils instance;
    private Notification.Builder m_builder;

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
        return m_builder.setContentTitle(title)
//                .setSubText("subtext---")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .setContentText(content)
//                .setTicker("ticker----")
                .setWhen(System.currentTimeMillis())
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),android.R.drawable.ic_delete))
                .setSmallIcon(iconRes)
                .setContentIntent(intent)
                .setAutoCancel(true);
    }

    public Notification.Builder getNotification_25(String title, String content,int iconRes, PendingIntent intent) {
         m_builder = new Notification.Builder(getApplicationContext());
        return m_builder.setContentTitle(title)
//                .setSubText("subtext---")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
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

    public void setProgress(int progress){
        m_builder.setProgress(100, 20, false);
    }
}

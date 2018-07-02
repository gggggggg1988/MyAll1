package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class WorkService extends Service {
    public WorkService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(WorkService.this,"Service is create-----------" , Toast.LENGTH_SHORT);
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(WorkService.this,"workService is working-----------" , Toast.LENGTH_SHORT);
//                    LogUtils.i();
                }

            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

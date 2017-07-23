package http;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import util.Consts;

/**
 * Created by Administrator on 2016/5/16 0016.
 */
public abstract class BaseProtocal<T> implements Consts {


    protected abstract T parseJson(String JSON);

    public void loadData(String url, Handler handler) {
        //T data = getDataFromLocal(url);
      getDataFromNet(url,handler);
    }

    private String json = null;

    private void getDataFromNet(final String ul, final Handler handler) {


        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(ul);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(5000);
                    con.setReadTimeout(5000);
                    int responseCode = con.getResponseCode();
                    StringBuilder builder = new StringBuilder();
                    if (responseCode == 200) {
                        InputStream inputStream = con.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        while ((json = reader.readLine())
                                != null) {
                            builder.append(json);
                        }

                        json = builder.toString();

                        Message message = Message.obtain(handler);
                        message.obj = json;
                        message.sendToTarget();
                    }


                } catch (MalformedURLException e) {

                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                super.run();
            }
        }.start();
    }

    private T getDataFromLocal(String url) {
        return null;
    }


}

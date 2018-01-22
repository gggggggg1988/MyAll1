package http;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

public class OkhttpHttpIml implements IHttpInterface {
    private OkHttpClient m_client=new OkHttpClient();
    @Override
    public void get(String url, Map<String, String> param, final CallBack callBack) {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        m_client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFail(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body().toString());
                }
            }
        });
    }

    @Override
    public void post(String url, Map<String, String> param, final CallBack callBack) {
        RequestBody body = appendParam(param);
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        m_client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               callBack.onFail(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body().toString());
                }
            }
        });
    }

    private RequestBody appendParam(Map<String, String> param) {
        FormBody.Builder builder = new FormBody.Builder();
        if (param == null&&param.size()==0) {
            return  builder.build();
        }

            Set<Map.Entry<String, String>> entries = param.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                builder.add(next.getKey(), next.getValue().toString());
            }
        return builder.build();
    }
}

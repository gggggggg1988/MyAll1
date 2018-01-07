package http.retrofit;

import entity.Data;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

public class TetrofitProxy {
    private static final TetrofitProxy ourInstance = new TetrofitProxy();
    IRetrofitInterface<Data> http1;

    public static TetrofitProxy getInstance() {
        return ourInstance;
    }

    private TetrofitProxy() {


    }

    public void get(){
        http1 = new RtrofitHttpIml();
        http1.get(new CallBack<Data>() {
            @Override
            public void onSuccess(Data data) {

            }

            @Override
            public void onFail(String e) {

            }
        });
    }

    public void post(){

    }
}

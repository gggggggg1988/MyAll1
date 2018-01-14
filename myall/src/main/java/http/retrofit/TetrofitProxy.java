package http.retrofit;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

public class TetrofitProxy {
    private static final TetrofitProxy ourInstance = new TetrofitProxy();

    public void setHttp1(IRetrofitInterface http1) {
        this.http1 = http1;
    }

    private IRetrofitInterface http1;

    public static TetrofitProxy getInstance() {
        return ourInstance;
    }

    private TetrofitProxy() {


    }

    public void get(CallBack callBack){

        http1.get(callBack);
    }

    public void post(){

    }
}

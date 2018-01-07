package http;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class HttpProxy implements IHttpInterface{
    private static final HttpProxy ourInstance = new HttpProxy();
    private IHttpInterface httpImp;
    public static HttpProxy getInstance() {
        return ourInstance;
    }

    private HttpProxy() {
    }
    public void init(IHttpInterface httpImp){
        this.httpImp = httpImp;
    }
    @Override
    public void get(String url, Map<String, Object> param, CallBack callBack) {
        httpImp.get(url,param,callBack);
    }

    @Override
    public void post(String url, Map<String, Object> param, CallBack callBack) {
        httpImp.post(url,param,callBack);
    }
}

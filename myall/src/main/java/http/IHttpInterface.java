package http;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public interface IHttpInterface {
    void get(String url, Map<String ,Object> param,CallBack callBack);
    void post(String url, Map<String ,Object> param,CallBack callBack);
}

package http.retrofit;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

public interface IRetrofitInterface<T> {
    public void get(http.retrofit.CallBack<T> callBack);
    public void post(http.retrofit.CallBack<T> callBack);

}

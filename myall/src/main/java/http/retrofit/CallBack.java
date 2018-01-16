package http.retrofit;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

public interface CallBack<T> {
    public void onSuccess(T data);

    public void onFail(String e);
}

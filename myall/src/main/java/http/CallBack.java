package http;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public interface CallBack {
    void onSuccess(String data);
    void onFail(String e);
}

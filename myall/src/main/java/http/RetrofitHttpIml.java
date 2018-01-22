package http;

import java.util.Map;

import entity.Item;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cantian on 2018/1/18.
 */

public class RetrofitHttpIml<T> implements IHttpInterface {

//    .map(new ApiResultFunc<T>(type)).compose(this.<T>apiTransformer())

    @Override
    public void get(String url, Map<String, String> param, final CallBack callBack) {
//        RetrofitGlobleConfig.baseUrl = url.substring(0, 25);
        DataService dataService = new ARetrofitRequest().create(DataService.class);

        Observable<Item> itemObservable = dataService.get(url,"json");
        itemObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Item>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Item value) {
                        HttpCallBack<Item> callBack1 = (HttpCallBack<Item>) callBack;
                        callBack1.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void post(String url, Map<String, String> param, CallBack callBack) {

    }
}

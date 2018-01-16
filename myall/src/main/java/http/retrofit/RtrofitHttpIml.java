package http.retrofit;

import com.google.gson.GsonBuilder;

import entity.Data;
import http.retrofit.service.BaiduService;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

public class RtrofitHttpIml implements IRetrofitInterface<Data> {
   private Retrofit m_builder = new Retrofit.Builder().baseUrl("")
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
            .build();


 @Override
 public void get(final CallBack<Data> callBack) {
  BaiduService baiduService = m_builder.create(BaiduService.class);
  baiduService.getData("sd", "ks", 4, 4).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Observer<Data>() {
           @Override
           public void onSubscribe(Disposable d) {

           }

           @Override
           public void onNext(Data value) {
               callBack.onSuccess(value);
           }

           @Override
           public void onError(Throwable e) {
             callBack.onFail(e.toString());
           }

           @Override
           public void onComplete() {

           }
          });
 }

 @Override
 public void post(CallBack<Data> callBack) {

 }
}

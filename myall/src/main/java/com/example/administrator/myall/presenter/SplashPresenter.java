package com.example.administrator.myall.presenter;


import com.example.administrator.myall.module.api.ApiService;
import com.example.administrator.myall.module.entity.SplashEntity;
import com.example.administrator.myall.utils.NetUtil;
import com.example.administrator.myall.utils.OkHttpImageDownloader;
import com.example.administrator.myall.utils.TimeUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import application.AllAplication;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/22
 * owspace
 */
public class SplashPresenter implements SplashContract.Presenter{
    private SplashContract.View view;
    private ApiService apiService;
    @Inject
    public SplashPresenter(SplashContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
//        Logger.d("apppp:"+apiService);
    }
    @Override
    public void getSplash(String deviceId ) {
        String client = "android";
        String version = "1.3.0";
        Long time = TimeUtil.getCurrentSeconds();
        apiService.getSplash(client,version,time,deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<SplashEntity>() {


                    @Override
                    public void onError(Throwable e) {
//                        Logger.e(e,"load splash failed:");
                    }

                    @Override
                    public void onComplete() {
//                        Logger.e("load splash onCompleted");
                    }

                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(SplashEntity splashEntity) {
                        if (NetUtil.isWifi(AllAplication.getContext())){
                            if (splashEntity != null){
                                List<String> imgs = splashEntity.getImages();
                                for (String url:imgs) {
                                    OkHttpImageDownloader.download(url);
                                }
                            }
                        }else{
//                            Logger.i("不是WIFI环境,就不去下载图片了");
                        }
                    }
                });
    }
}

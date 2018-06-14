package com.example.administrator.myall.dagger.module;

import com.example.administrator.myall.BuildConfig;
import com.example.administrator.myall.dagger.api.StringConverterFactory;
import com.example.administrator.myall.module.api.ApiService;
import com.example.administrator.myall.retrofit.util.EntityUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okhttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl("http://static.owspace.com/")
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(EntityUtils.gson))//
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }
    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }


}

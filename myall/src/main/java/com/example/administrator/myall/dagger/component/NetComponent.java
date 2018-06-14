package com.example.administrator.myall.dagger.component;

import com.example.administrator.myall.dagger.module.NetModule;
import com.example.administrator.myall.module.api.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Component(modules = NetModule.class)
@Singleton
public interface NetComponent {
    ApiService getApiService();
    OkHttpClient getOkHttp();
    Retrofit getRetrofit();
}

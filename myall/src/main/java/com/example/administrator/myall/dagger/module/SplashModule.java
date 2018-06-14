package com.example.administrator.myall.dagger.module;

import com.example.administrator.myall.presenter.SplashContract;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {
    private SplashContract.View m_view;

    public SplashModule(SplashContract.View view) {
        m_view = view;
    }
    @Provides
    public SplashContract.View provideView(){
        return m_view;
    }
}

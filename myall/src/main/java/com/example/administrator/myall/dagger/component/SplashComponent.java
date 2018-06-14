package com.example.administrator.myall.dagger.component;

import com.example.administrator.myall.activitys.SplashActivity;
import com.example.administrator.myall.dagger.module.SplashModule;
import com.example.administrator.myall.dagger.scopes.UserScope;

import dagger.Component;

@UserScope
@Component(modules = SplashModule.class,dependencies = NetComponent.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}

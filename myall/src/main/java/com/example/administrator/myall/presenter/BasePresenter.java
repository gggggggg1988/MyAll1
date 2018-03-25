package com.example.administrator.myall.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class BasePresenter<T> {
    protected WeakReference<T> view;

    public void attachView(T view){
        this.view = new WeakReference<T>(view);
    }

    public void detachView(){
        this.view.clear();
    }
}

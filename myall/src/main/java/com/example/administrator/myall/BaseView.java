package com.example.administrator.myall;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by cantian on 2017/11/28.
 */

public interface BaseView extends MvpView {
    void showProgress();

    void onCompleted();

    void onError(Throwable e);
}

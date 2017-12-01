package com.example.administrator.myall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

/**
 * Created by Administrator on 2016/5/22 0022.
 */
public abstract class MvpBaseActivity<V extends BaseView, P extends BasePresenter<V>> extends MvpActivity<V,P> {
    private  ProgressDialog progress;
    public void intent2Activity(Class<? extends  Activity> activity) {
        Intent intent = new Intent(this, Activity.class);
        startActivity(intent);
    }
    public void showProgress(){
      progress =  ProgressDialog.show(this,"提示","正在登陆中。。。",false,false);
    }

    public void hideProgress(){
        progress.cancel();
    }

}

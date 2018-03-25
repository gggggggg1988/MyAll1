package com.example.administrator.myall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public abstract class MVPBaseAct<V,T extends com.example.administrator.myall.presenter.BasePresenter<V>> extends AppCompatActivity {
    protected T presenter;//此处的V 就是View  T就是presenter
    public void intent2Activity(Class<? extends Activity> activity) {
        Intent intent = new Intent(this, Activity.class);
        startActivity(intent);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        presenter = initPresenter();
        presenter.attachView((V) this);
        super.onCreate(savedInstanceState);
    }

    public abstract T initPresenter() ;

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}

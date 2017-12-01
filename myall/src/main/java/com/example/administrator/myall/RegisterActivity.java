package com.example.administrator.myall;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myall.module.GreenDaoData;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends MvpBaseActivity<IRegisterView,RegisterPresenter> implements View.OnClickListener,MvpView {

    private LinearLayout llName;
    private TextView name;
    private LinearLayout llPassword;
    private TextView password;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        llName = (LinearLayout) findViewById(R.id.ll_name);
        name = (TextView) findViewById(R.id.name);
        llPassword = (LinearLayout) findViewById(R.id.ll_password);
        password = (TextView) findViewById(R.id.password);
        findViewById(R.id.button2).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        findViewById(R.id.button3).setOnClickListener(this);
    }

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        presenter = new RegisterPresenter(this);
        presenter.testView();

        return presenter;
    }

    private EditText getEtName(){
        return (EditText) findViewById(R.id.et_name);
    }

    private EditText getEtPassword(){
        return (EditText) findViewById(R.id.et_password);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
//                User user = new User();//activeAndroid
//                user.name = getEtName().getText().toString();
//                user.password = getEtPassword().getText().toString();
//                user.save();

                GreenDaoData user = new GreenDaoData(getEtName().getText().toString(),getEtPassword().getText().toString());//activeAndroid

                getPresenter().saveData(user);

                break;
            case R.id.button3:
                List<GreenDaoData> users = getPresenter().getDataList();
                List<String> usernames = new ArrayList<>();
                for (GreenDaoData s:users
                     ) {
                    usernames.add(s.getName());
                }
                listView.setAdapter(new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_list_item_1,usernames));
                break;
        }
    }

    /**
     *  private TextView password;
     private ListView listView;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_login);

     llName = (LinearLayout) findViewById(R.id.ll_name);
     name = (TextView) findViewById(R.id.name);
     llPassword = (LinearLayout) findViewById(R.id.ll_password);
     password = (TextView) findViewById(R.id.password);
     findViewById(R.id.button2).setOnClickListener(this);
     listView = (ListView) findViewById(R.id.listView);
     findViewById(R.id.button3).setOnClickListener(this);
     }

     private EditText getEtName(){
     return (EditText) findViewById(R.id.et_name);
     }

     private EditText getEtPassword(){
     return (EditText) findViewById(R.id.et_password);
     }
     @Override
     public void onClick(View view) {
     switch (view.getId()) {
     case R.id.button2:
     //TODO implement
     break;
     case R.id.button3:
     //TODO implement
     break;
     }
     }
     */
}

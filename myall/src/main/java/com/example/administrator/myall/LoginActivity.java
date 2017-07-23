package com.example.administrator.myall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import entity.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

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
                User user = new User();
                user.name = getEtName().getText().toString();
                user.password = getEtPassword().getText().toString();
                user.save();

                break;
            case R.id.button3:
                List<User> users = new Select().from(User.class).execute();
                List<String> usernames = new ArrayList<>();
                for (User s:users
                     ) {
                    usernames.add(s.name);
                }
                listView.setAdapter(new ArrayAdapter<String>(LoginActivity.this,android.R.layout.simple_list_item_1,usernames));
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

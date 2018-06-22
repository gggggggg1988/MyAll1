package com.example.administrator.myall.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.myall.R;
import com.example.administrator.myall.activitys.util.BottomNavigationViewHelper;

public class Main2Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView m_bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        initViews();
    }

    private void initViews() {
        setToolbar();
        m_bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_id);
        BottomNavigationViewHelper.disableShiftMode(m_bottomNavigationView);
        m_bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                         case R.id.item1:

                             break;

                         default:
                             break;
                       }

                return false;
            }
        });


    }

    //设置标题栏Toolbar
    private void setToolbar() {
        toolbar.setTitle("凤凰卫士");   //设置标题
        toolbar.setSubtitle("新浪消息");    //设置副标题
        toolbar.setSubtitleTextColor(Color.WHITE);  //设置副标题字体颜色
        setSupportActionBar(toolbar);   //必须使用
        //添加左边图标点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //添加menu项点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_r_img:
                        Log.e("Test---->","点击了右边图标");
                        break;
                    case R.id.toolbar_r_1:
                        Log.e("Test---->","点击了弹出菜单1");
                        break;
                    case R.id.toolbar_r_2:
                        Log.e("Test---->","点击了弹出菜单2");
                        break;
                    case R.id.toolbar_r_3:
                        Log.e("Test---->","点击了弹出菜单3");
                        break;
                }
                return true;    //返回为true
            }
        });
    }
    //设置menu（右边图标）
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_popup, menu); //解析menu布局文件到menu
        return true;
    }
}

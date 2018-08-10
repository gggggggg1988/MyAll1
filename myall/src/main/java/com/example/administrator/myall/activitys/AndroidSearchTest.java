package com.example.administrator.myall.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myall.R;

public class AndroidSearchTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_search_test);
    }

    @Override
    public boolean onSearchRequested() {
        String text="123";
        Bundle bundle=new Bundle();
        bundle.putString("data", text);

        //打开浮动搜索框（第一个参数默认添加到搜索框的值）
        //bundle为传递的数据
        startSearch("mm", false, bundle, false);
        //这个地方一定要返回真 如果只是super.onSearchRequested方法不但
 //onSearchRequested（搜索框默认值）无法添加到搜索框中,bundle也无法传递出去
        return true;
    }
}

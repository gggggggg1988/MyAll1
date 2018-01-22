package com.example.administrator.myall;

import com.example.administrator.myall.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

import entity.Item;
import http.DataService;
import http.HttpCallBack;
import http.HttpProxy;
import http.RetrofitHttpIml;

/**
 * Created by cantian on 2018/1/18.
 */

public class Test {
    public  void test(){
        HttpProxy proxy = HttpProxy.getInstance();
        proxy.init(new RetrofitHttpIml<DataService>());
        Map<String, String> map = new HashMap<>();
        map.put("data", "json");
        proxy.get("com/MyServlet", map, new HttpCallBack<Item>() {
            @Override
            public void onSuccess(Item item) {
                LogUtils.i("item---- "+item.toString());
            }

            @Override
            public void onFail(String e) {

            }
        });
    }
}

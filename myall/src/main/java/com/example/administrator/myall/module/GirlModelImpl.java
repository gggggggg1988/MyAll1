package com.example.administrator.myall.module;

import java.util.ArrayList;
import java.util.List;

import entity.Data;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class GirlModelImpl implements IGirlModel {


    @Override
    public void loadGirl(GirlOnloadListener listener) {
        List<Data> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Data data = new Data("title"+i);
            datas.add(data);

        }

        listener.onComplete(datas);
    }
}

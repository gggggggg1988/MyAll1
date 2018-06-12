package com.example.administrator.myall.module;

import java.util.List;

import entity.Data;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public interface IGirlModel {
    void loadGirl( GirlOnloadListener listener);
    interface GirlOnloadListener{
        void onComplete(List<Data> girls);
    }
}

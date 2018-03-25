package com.example.administrator.myall.view;

import java.util.List;

import entity.Data;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public interface IGirlView {

    void showLoading();

    void showGirls(List<Data> girls);
}

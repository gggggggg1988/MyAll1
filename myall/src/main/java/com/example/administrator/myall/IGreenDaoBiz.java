package com.example.administrator.myall;

import com.example.administrator.myall.module.GreenDaoData;

import java.util.List;

/**
 * Created by cantian on 2017/11/28.
 */

public interface IGreenDaoBiz {
    void save(GreenDaoData data);

    List<GreenDaoData> get();

    List<GreenDaoData> get(String key);
}

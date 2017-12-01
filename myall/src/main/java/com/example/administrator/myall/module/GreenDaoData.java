package com.example.administrator.myall.module;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by cantian on 2017/11/28.
 */
@Entity
public class GreenDaoData {
    private String name;
    private String pwd;

    @Override
    public String toString() {
        return "GreenDaoData{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    @Generated(hash = 894252131)
    public GreenDaoData(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Generated(hash = 817674021)
    public GreenDaoData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

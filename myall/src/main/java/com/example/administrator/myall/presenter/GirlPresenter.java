package com.example.administrator.myall.presenter;

import com.example.administrator.myall.module.GirlModelImpl;
import com.example.administrator.myall.module.IGirlModel;
import com.example.administrator.myall.view.IGirlView;

import java.util.List;

import entity.Data;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class GirlPresenter<T extends IGirlView> extends BasePresenter<T>{
//    IGirlView girlView;

   private IGirlModel girlModel=new GirlModelImpl();

    public GirlPresenter() {

    }

    public void fetch(){
        if (view != null) {
            view.get().showLoading();
            if (girlModel
                    != null) {
                girlModel.loadGirl(new IGirlModel.GirlOnloadListener() {
                    @Override
                    public void onComplete(List<Data> girls) {
                        if (view != null) {
                            view.get().showGirls(girls);
                        }
                    }
                });
            }
        }
    }


}

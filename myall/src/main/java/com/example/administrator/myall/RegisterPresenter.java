package com.example.administrator.myall;

import android.content.Context;

import com.example.administrator.myall.module.GreenDaoData;
import com.example.administrator.myall.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cantian on 2017/11/28.
 */

public class RegisterPresenter extends BasePresenter<IRegisterView> {
    private Context m_context;
   private IGreenDaoBiz gdBiz;
    private RegisterActivity m_activity;
    public void testView(){
        LogUtils.i("getview------"+getView());

    }

    public RegisterPresenter(Context context) {
        m_context = context;
        gdBiz=new GreenDaoBizImp(m_context);
    }

    public void saveData(GreenDaoData data){
        m_activity = (RegisterActivity) getView();
        m_activity.showProgress();
        gdBiz.save(data);
        m_activity.hideProgress();
    }

    public List<GreenDaoData> getDataList(){
        List<GreenDaoData> datas = new ArrayList<>();

        datas = gdBiz.get();
        return datas;
    }



}

package com.example.administrator.myall;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.myall.module.GreenDaoData;
import com.example.administrator.myall.utils.LogUtils;
import com.minto.greendao.gen.DaoMaster;
import com.minto.greendao.gen.DaoSession;
import com.minto.greendao.gen.GreenDaoDataDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cantian on 2017/11/28.
 */

public class GreenDaoBizImp implements IGreenDaoBiz {
    Context m_context;
    private GreenDaoDataDao userDao;

    public GreenDaoBizImp(Context context) {
        m_context = context;
        initDbHelp();
    }

    @Override
    public void save(GreenDaoData data) {
        LogUtils.i("dataGreenDaoData------- "+data.toString());
        userDao.insert(data);
    }

    @Override
    public List<GreenDaoData> get() {
        List<GreenDaoData> datas = new ArrayList<>();
        QueryBuilder<GreenDaoData> builder = userDao.queryBuilder();
        datas=builder.list();

        return datas;
    }
    @Override
    public List<GreenDaoData> get(String key) {
        List<GreenDaoData> datas = new ArrayList<>();
        QueryBuilder<GreenDaoData> builder = userDao.queryBuilder();
        datas = builder.where(GreenDaoDataDao.Properties.Name.eq(key)).list();

        return datas;
    }

    /*初始化数据库相关*/
    private void initDbHelp() {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(m_context, "liwei-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getGreenDaoDataDao();
    }
}

package com.minto.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.administrator.myall.module.GreenDaoData;

import com.minto.greendao.gen.GreenDaoDataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig greenDaoDataDaoConfig;

    private final GreenDaoDataDao greenDaoDataDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        greenDaoDataDaoConfig = daoConfigMap.get(GreenDaoDataDao.class).clone();
        greenDaoDataDaoConfig.initIdentityScope(type);

        greenDaoDataDao = new GreenDaoDataDao(greenDaoDataDaoConfig, this);

        registerDao(GreenDaoData.class, greenDaoDataDao);
    }
    
    public void clear() {
        greenDaoDataDaoConfig.clearIdentityScope();
    }

    public GreenDaoDataDao getGreenDaoDataDao() {
        return greenDaoDataDao;
    }

}
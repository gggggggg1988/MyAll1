package com.example.administrator.myall;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import dalvik.system.DexClassLoader;
import fragment.PluginManager;
import ndkjnidemo.wobiancao.com.pluginstandard.PluginInterface;

public class ProxyActivity extends Activity {
    private String activityName;
    private PluginInterface m_pluginInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_proxy);
        activityName = getIntent().getStringExtra("className");
        launchActivity();
    }

    private void launchActivity() {
        int a = 2;
        int b = 4;
        try {
            DexClassLoader loader = PluginManager.getInstance().getDexClassLoader();
            Class<?> activityClass = loader.loadClass(activityName);
            Constructor constructor = activityClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance();
            m_pluginInterface = (PluginInterface) instance;
            m_pluginInterface.attach(this);

            Bundle bundle = new Bundle();
            m_pluginInterface.onCreate(bundle);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        int a = 2;
        int b = 4;
        m_pluginInterface.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        m_pluginInterface.onDestroy();
    }

    /**
     *这里非常重要
     * @return
     */
    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }
}

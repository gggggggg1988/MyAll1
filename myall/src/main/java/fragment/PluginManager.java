package fragment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class PluginManager {
    private Context m_context;
    private DexClassLoader m_dexClassLoader;
    private Resources m_resources;
    private PackageInfo m_packageInfo;
    private static final PluginManager ourInstance = new PluginManager();

    public static PluginManager getInstance() {
        return ourInstance;
    }

    private PluginManager() {
    }
    public void init(Context context){
        m_context = context;
    }
    public void loadApk(String path){
        File dexOutFile = m_context.getDir("dex", Context.MODE_PRIVATE);
        String dexPath = dexOutFile.getAbsolutePath();
        /**
         * 非常重要，将apk里面的dex里面的class load出来
         */
        m_dexClassLoader = new DexClassLoader(path, dexPath, null, m_context.getClassLoader());

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, path);
            m_resources = new Resources(assetManager, m_context.getResources().getDisplayMetrics(), m_context.getResources().getConfiguration());
            PackageManager packageManager = m_context.getPackageManager();
            m_packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public DexClassLoader getDexClassLoader() {
        return m_dexClassLoader;
    }

    public Resources getResources() {
        return m_resources;
    }

    public PackageInfo getPackageInfo() {
        return m_packageInfo;
    }
}

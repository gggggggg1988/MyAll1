package ndkjnidemo.wobiancao.com.biaobai;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import ndkjnidemo.wobiancao.com.pluginstandard.PluginInterface;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class BaseActivity extends Activity implements PluginInterface{
    /**this没用，因为系统没有注入
     * 宿主传进来的activity
     */
    protected Activity that;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        that.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        that.setContentView(view);
    }

    @Override
    public View findViewById(@IdRes int id) {
        return that.findViewById(id);
    }

    @Override
    public Resources getResources() {
        return that.getResources();
    }

    @Override
    public Object getSystemService( String name) {
        return that.getSystemService(name);
    }

    @Override
    public ClassLoader getClassLoader() {
        return that.getClassLoader();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }

    @Override
    public void attach(Activity activity) {
        this.that = activity;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}

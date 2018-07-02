package ndkjnidemo.wobiancao.com.pluginstandard;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public interface PluginInterface {
    /**
     *
     * @param activity
     */
    void attach(Activity activity);
    void onCreate(Bundle saveInstanceState);
    public void onStart();
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestroy();
    public void onSaveInstanceState(Bundle outState);
    public boolean onTouchEvent(MotionEvent event);
    public void onBackPressed();
}

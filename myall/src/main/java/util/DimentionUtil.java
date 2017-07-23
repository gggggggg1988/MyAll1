package util;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2016/5/3 0003.
 */
public class DimentionUtil {
    public static int dpi2Px(Activity activity,int dpi){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float density = metrics.density;
        return (int)(dpi*density+0.5f);
    }
    public static int getDisplayWdith(Activity activity){
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        return point.x;
    }
}

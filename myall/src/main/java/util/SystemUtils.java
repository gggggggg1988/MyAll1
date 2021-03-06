/*
 Copyright © 2015, 2016 Jenly Yu <a href="mailto:jenly1314@gmail.com">Jenly</a>

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 */
package util;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class SystemUtils {


    private static KeyguardManager.KeyguardLock mKeyguardLock;
    private static PowerManager.WakeLock mWakeLock;
    private static Vibrator myVibrator;

    private SystemUtils() {
        throw new AssertionError();
    }



    //获取屏幕原始尺寸高度，包括虚拟功能键高度
    public static int getDpi(Context context){
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics",DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi=displayMetrics.heightPixels;
        }catch(Exception e){
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取 虚拟按键的高度
     * @param context
     * @return
     */
    public static  int getBottomStatusHeight(Context context){
        int totalHeight = getDpi(context);

        int contentHeight = getScreenHeight(context);

        return totalHeight  - contentHeight;
    }

    /**
     * 标题栏高度
     * @return
     */
    public static int getTitleHeight(Activity activity){
        return  activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context)
    {

        int statusHeight = -1;
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return statusHeight;
    }


    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 判断是否存在虚拟按键
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }



    /**
     * 异步线程
     *
     * @param runnable
     */
    public static void asyncThread(Runnable runnable) {
        new Thread(runnable).start();
    }


    public static boolean isNetWorkActive(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null && activeNetInfo.isConnected();
    }

    /**
     * 发送按键按下事件
     *
     * @param keyCode
     */
    public static void sendKeyCode(final int keyCode) {
        asyncThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    LogUtil.e("Exception when sendPointerSync", e);
                }
            }
        });
    }

    /**
     * 退格删除键
     *
     * @param et
     */
    public static void deleteClick(EditText et) {
        final KeyEvent keyEventDown = new KeyEvent(KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_DEL);
        et.onKeyDown(KeyEvent.KEYCODE_DEL, keyEventDown);
    }


    /**
     * 调用打电话界面
     *
     * @param context
     * @param phoneNumber
     */
    public static void call(Context context, String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(String.format("tel:%s", phoneNumber)));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }


    /**
     * 调用发短信界面
     *
     * @param context
     * @param phoneNumber
     */
    public static void sendSMS(Context context, String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(String.format("smsto:%s", phoneNumber)));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }

    /**
     * 发短信
     *
     * @param phoneNumber
     * @param msg
     */
    public static void sendSMS(String phoneNumber, String msg) {

        SmsManager sm = SmsManager.getDefault();

        List<String> msgs = sm.divideMessage(msg);

        for (String text : msgs) {
            sm.sendTextMessage(phoneNumber, null, text, null, null);
        }

    }

    /**
     * 拍照
     *
     * @param activity
     * @param requestCode
     */
    public static void imageCapture(Activity activity, int requestCode) {
        imageCapture(activity, null, requestCode);
    }

    /**
     * 拍照
     *
     * @param activity
     * @param path
     * @param requestCode
     */
    public static void imageCapture(Activity activity, String path,
                                    int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (!TextUtils.isEmpty(path)) {
            Uri uri = Uri.fromFile(new File(path));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 拍照
     *
     * @param fragment
     * @param path
     * @param requestCode
     */
    public static void imageCapture(Fragment fragment, String path,
                                    int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (!TextUtils.isEmpty(path)) {
            Uri uri = Uri.fromFile(new File(path));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * 获取包信息
     *
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(e);
        } catch (Exception e) {
            LogUtil.e(e);
        }
        return packageInfo;
    }

    /**
     * 获取当前应用包的版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo != null ? packageInfo.versionName : null;
    }

    /**
     * 获取当前应用包的版本码
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo != null ? packageInfo.versionCode : 0;
    }

    /**
     * 跳转到app详情设置界面
     *
     * @param context
     */
    public static void startAppDetailSetings(Context context) {
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri);
        context.startActivity(intent);
    }

    /**
     * 安装apk
     *
     * @param context
     * @param path
     */
    public static void installApk(Context context, String path) {
        installApk(context, new File(path));
    }

    /**
     * 安装apk
     *
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uriData = Uri.fromFile(file);
        String type = "application/vnd.android.package-archive";

        intent.setDataAndType(uriData, type);
        context.startActivity(intent);
    }


    /**
     * 卸载apk
     *
     * @param context
     * @param packageName
     */
    public static void uninstallApk(Context context, String packageName) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uriData = Uri.parse("package:" + packageName);
        intent.setData(uriData);
        context.startActivity(intent);
    }

    /**
     * 卸载
     *
     * @param context
     */
    public static void uninstallApk(Context context) {
        uninstallApk(context, context.getPackageName());
    }


    /**
     * 检测权限
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkSelfPermission(Context context, @NonNull String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * 申请权限
     *
     * @param activity
     * @param permission
     * @param requestCode
     */
    public static void requestPermission(Activity activity, @NonNull String permission, int requestCode) {
        if (!checkSelfPermission(activity,permission) ) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
        }
    }

    /**
     * 显示申请授权
     *
     * @param activity
     * @param permission
     */
    public static void shouldShowRequestPermissionRationale(Activity activity, @NonNull String permission) {
        ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }


    /**
     * 隐藏软键盘
     *
     * @param context
     * @param v
     */
    public static void hideInputMethod(Context context, EditText v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

    }

    /**
     * 显示软键盘
     *
     * @param context
     * @param v
     */
    public static void showInputMethod(Context context, EditText v) {

        v.requestFocus();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 点亮屏幕 并解锁
     *
     * @param context
     */
    public static void wakeupScreen(Context context) {
//        // 键盘管理器
//        KeyguardManager mKeyguardManager=(KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);;
//        // 键盘锁
//          mKeyguardLock= mKeyguardManager.newKeyguardLock("");
//        // 电源管理器
//         PowerManager mPowerManager  = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//        // 唤醒锁
//          mWakeLock = mPowerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "Tag");
//        // 点亮亮屏
//        mWakeLock = mPowerManager.newWakeLock
//                (PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "Tag");
//        mWakeLock.acquire();
////        // 初始化键盘锁
////        mKeyguardLock = mKeyguardManager.newKeyguardLock("");
//        // 键盘解锁
//        mKeyguardLock.disableKeyguard();

        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //解锁
        kl.disableKeyguard();
        //获取电源管理器对象
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");
        //点亮屏幕
        wl.acquire();
        //释放
        wl.release();
    }

    /**
     * 释放锁屏资源
     */
    public static void rleaseLock() {
        if (mWakeLock != null) {
            mWakeLock = null;
        }
        if (mKeyguardLock != null) {
            mKeyguardLock.reenableKeyguard();
        }
    }

    public static final int LONG_VIBRATE = 1;
    public static final int SHORT_VIBRATE = 0;
    /**
     * 节奏震动
     */
    public static final int RYTHM_VIBRATE = 2;

    /**
     * 震动
     * @param context
     * @param length  震动类型 1:长震动，0：短震动，2：节奏震动
     */
    public static void beginVibrate(Context context, int length) {
        //获得系统的Vibrator实例:
        myVibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        if (!myVibrator.hasVibrator()) {
            Toast.makeText(context, "当前设备没有振动器", Toast.LENGTH_SHORT);
            return;
        }
        switch (length) {
            case LONG_VIBRATE:
                myVibrator.cancel();
                myVibrator.vibrate(new long[]{400,1000}, 0);
                break;
            case SHORT_VIBRATE:
                myVibrator.cancel();
                myVibrator.vibrate(new long[]{100, 200, 100, 200}, 0);
                break;
            case RYTHM_VIBRATE:
                myVibrator.cancel();
                myVibrator.vibrate(new long[]{500, 100, 500, 100, 500, 100}, 0);
                break;

            default:
                break;
        }


    }

    public static void stopVibrate(){
        if (myVibrator == null) {
            return;
        }
        myVibrator.cancel();
        myVibrator=null;
    }
}

/**
 * The MIT License (MIT)
 * Copyright (c) 2012-2014 唐虞科技(TangyuSoft) Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Filename: Description: 系统工具类 Company: 灿天
 * 
 * @author: chjr
 * @version: 1.0 Create at: 2014-8-25 下午6:04:56
 * 
 *           Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014-8-25 chjr 1.0 1.0 Version
 */
public class Util {

	public final static String Log_Tag = "Util";

	private static BroadcastReceiver sdCardReceiver;
	private static boolean isSDCardReceiverRegister = false;
	private static boolean isSDCardAvailable = false;
	private static boolean isSDCardWriteable = false;
	private static Context saveContext;
	private static Toast mToast;
	private static Dialog mProgressDialog;

	public static void v(String msg) {
		Log.v(Log_Tag, msg);
	}

	public static void e(String msg) {
		Log.e(Log_Tag, msg);
	}

	public static void toast(Context ctx, String msg, boolean isShort) {
		Toast.makeText(ctx, msg,
				isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG).show();
	}

	public static void toast(Context ctx, int msgid, boolean isShort) {
		Toast.makeText(ctx, msgid,
				isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG).show();
	}

	public static boolean isNull(Object o) {
		return o == null ? true : false;
	}

	public static boolean isNull(List<?> list) {
		return list == null || list.size() == 0 ? true : false;
	}

	public static boolean isNotNull(List<?> list) {
		return !isNull(list);
	}

	@SuppressLint("DefaultLocale")
	public static boolean isNull(String str) {

		if (str != null && str.toLowerCase().equals("null")) {
			str = "";
		}
		return TextUtils.isEmpty(str) ? true : false;
	}

	public static boolean isNotNull(Object o) {
		return !isNull(o);
	}

	@SuppressLint("DefaultLocale")
	public static boolean isNotNull(String str) {

		return !isNull(str);
	}

	public static String getCurrentTime(String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		String currentTime = sdf.format(date);
		return currentTime;
	}

	public static String getCurrentTime() {
		return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
	}

	/**
	 * @param src
	 * @param key
	 * @return >= 0 is in array. negative value means not.
	 */
	public static int isInArray(int[] src, int key) {
		if (isNull(src))
			return -1;
		for (int i = 0; i < src.length; i++) {
			if (src[i] == key) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * find param2 in param1
	 * 
	 * @param src
	 * @param key
	 * @return if return positive value. that is index in array. negative value
	 *         means not.
	 */
	public static int isInList(List<? extends Object> src, Object key) {
		if (isNull(src))
			return -1;
		if (isNull(key))
			return -1;
		for (int i = 0; i < src.size(); i++) {
			if (isNull(src.get(i)))
				continue;
			if (src.get(i).equals(key)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * parameter 2 is contain in parameter 1.
	 * 
	 * @param sourceFlag
	 * @param compareFlag
	 * @return
	 */
	public static boolean isFlagContain(int sourceFlag, int compareFlag) {
		return (sourceFlag & compareFlag) == compareFlag;
	}

	/**
	 * Whether show StatueBar or not.
	 * 
	 * @param active
	 *            in which Activity
	 * @param visible
	 *            View.VISIBLE is show, otherwise is dismiss
	 */
	public static void statueBarVisible(Activity active, final int visible) {
		if (visible == View.VISIBLE) {
			active.getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		} else {
			active.getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		}
	}

	/**
	 * check SD card is available. then u may be call toastShort to communicate
	 * with user.
	 * 
	 * @return <b>true</b> the SD card is available. <b>false</b> not.
	 */
	public static boolean sdcardIsOnline() {
		final String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state) ? true : false;
	}

	/**
	 * update the isSDCardAvailable and isSDCardWriteable state
	 */
	private static void sdCardUpdateState(Context context) {
		final String sdCardState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(sdCardState)) {
			isSDCardAvailable = isSDCardWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(sdCardState)) {
			isSDCardAvailable = true;
			isSDCardWriteable = false;
		} else {
			isSDCardAvailable = isSDCardWriteable = false;
		}
	}

	/**
	 * open the SD card state listener. Generally call it in "onStart" method.
	 */
	public static synchronized void sdCardStartListener(Context context,
			sdcardListener lis) {
		if (saveContext != null && saveContext != context) {
			sdCardStopListener(saveContext);
		}
		mSdcardListener = lis;
		saveContext = context;
		sdCardReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				sdCardUpdateState(context);
				if (mSdcardListener != null)
					mSdcardListener.onReceiver(isSDCardAvailable,
							isSDCardWriteable);
			}
		};
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
		filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
		filter.addAction(Intent.ACTION_MEDIA_REMOVED);
		filter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
		filter.addAction(Intent.ACTION_MEDIA_EJECT);
		if (!isSDCardReceiverRegister) {
			context.registerReceiver(sdCardReceiver, filter);
			isSDCardReceiverRegister = true;
		}
		sdCardUpdateState(context);
	}

	/**
	 * close the SD card state listener. Generally call it in "onStart" method.
	 */
	public static synchronized void sdCardStopListener(Context context) {
		if (isSDCardReceiverRegister && saveContext == context) {
			context.unregisterReceiver(sdCardReceiver);
			isSDCardReceiverRegister = false;
			mSdcardListener = null;
		}
	}

	public static sdcardListener mSdcardListener;

	public interface sdcardListener {
		void onReceiver(boolean isAvailable, boolean isCanWrite);
	}

	public static void sysSetActionBness(Activity action, float bness) {
		WindowManager.LayoutParams lp = action.getWindow().getAttributes();
		lp.screenBrightness = bness;
		action.getWindow().setAttributes(lp);
	}

	public static float sysGetActionBness(Activity action) {
		return action.getWindow().getAttributes().screenBrightness;
	}

	public static void sysIsLockScreen(Activity act, boolean isLock) {
		if (isLock) {
			switch (act.getResources().getConfiguration().orientation) {
			case Configuration.ORIENTATION_PORTRAIT:
				act.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				break;
			case Configuration.ORIENTATION_LANDSCAPE:
				act.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				break;
			}
		} else {
			act.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
		}
	}

	/**
	 * 判断网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null && cm.getActiveNetworkInfo() != null) {
			return cm.getActiveNetworkInfo().isAvailable();
		}
		return false;
	}

	/**
	 * 判断GPS是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager lm = ((LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE));
		List<String> accessibleProviders = lm.getProviders(true);
		return accessibleProviders != null && accessibleProviders.size() > 0;
	}

	public static boolean isWifiEnabled(Context context) {
		ConnectivityManager mgrConn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		TelephonyManager mgrTel = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
				.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
				.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
	}

	public static final void openGPS(Context context) {
		Intent GPSIntent = new Intent();
		GPSIntent.setClassName("com.android.settings",
				"com.android.settings.widget.SettingsAppWidgetProvider");
		GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
		GPSIntent.setData(Uri.parse("custom:3"));
		try {
			PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
		} catch (PendingIntent.CanceledException e) {
			e.printStackTrace();
		}
	}

	public static boolean is3rd(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkINfo = cm.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}

	public static boolean isWifi(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkINfo = cm.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	public static class DensityUtil {

		/**
		 * dp to px
		 */
		public static int dip2px(Context context, float dpValue) {
			final float scale = context.getResources().getDisplayMetrics().density;
			return (int) (dpValue * scale + 0.5f);
		}

		/**
		 * px to dp
		 */
		public static int px2dip(Context context, float pxValue) {
			final float scale = context.getResources().getDisplayMetrics().density;
			return (int) (pxValue / scale + 0.5f);
		}
	}

	/**
	 * 验证邮箱地址是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			Log.e("验证邮箱地址错误", e.getMessage());
			flag = false;
		}
		return flag;
	}
	/***
	 * 
	 * 
	 * 验证密码
	 */
	public boolean checkPwd(String pwd) {

		if (!pwd.matches("^[0-9a-zA-Z]{6,18}$")) {

			return true;
		}
		return false;

	}
	/***
	 * 验证手机号的方法
	 */
	public boolean checkPhone(String str) {

		if (str.matches("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$")) {
			return true;
		}
		return false;

	}
	
	/**
	 * 以，的方式拼接ID joinStr(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选)
	 * 
	 * @param ids
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public static String joinStr(String[] ids) {
		if (null != ids && ids.length > 0) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < ids.length; i++) {
				builder.append(ids[i]).append(",");
			}
			return builder.deleteCharAt(builder.length() - 1).toString();
		}
		return null;
	}

	/**
	 * 用@方式拆分名字
	 * 
	 * @param ids
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public static String joinName(String[] ids) {

		if (null != ids && ids.length > 0) {

			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < ids.length; i++) {
				builder.append(ids[i]).append("@");
			}
			return builder.deleteCharAt(builder.length() - 1).toString();
		}
		return null;
	}

	/**
	 * 保存图片到手机中
	 * 
	 * @param bmp
	 * @return
	 * @throws Exception
	 */
	public static String saveImage(Bitmap bmp) throws Exception {
		File file = new File(buildFileName());
		file.createNewFile();
		FileOutputStream oStream = new FileOutputStream(file);
		bmp.compress(Bitmap.CompressFormat.JPEG, 100, oStream);
		oStream.flush();
		oStream.close();
		return file.getPath();
	}

	/**
	 * 构建图片路劲
	 * 
	 * @return
	 */
	public static String buildFileName() {
		String SDCARD = Environment.getExternalStorageDirectory() + "/";
		String basePath = SDCARD + "workdb/chache/";
		File dir = new File(basePath);
		dir.mkdirs();
		return basePath + UUIDUtil.UUIDLong() + ".jpg";
	}

	/**
	 * 压缩图片的方法
	 * 
	 * @param srcPath
	 * @return
	 * @throws Exception
	 */
	public static String Compressionimage(String srcPath) throws Exception {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return saveImage(bitmap);
	}

//	/**
//	 * 加载更多的分页 getPageNo(这里用一句话描述这个方法的作用)
//	 *
//	 * @since 1.0.0
//	 */
//	@SuppressWarnings("unchecked")
//	public static Integer getPageNo(ResponseBean responseBean, int pageNo,
//			int pageSize) {
//
//		int pageIndex = pageNo;
//
//		try {
//
//			if (!Util.isNull(responseBean)) {
//
//				if ("202".equals(responseBean.getResult())) {
//
//					PageObject pObject = DataUtil.jsonToObject(responseBean
//							.getParam().get("result"), PageObject.class);
//
//					String jsonString = DataUtil
//							.objectToJSON(pObject.getList());
//					List<SimpleCommunityBean> sBeanList = DataUtil
//							.jsonToObject(jsonString, List.class);
//					if (!Util.isNull(sBeanList)) {
//						if (pageNo == (1 + pObject.getCount() / pageSize)) {
//							return pageNo;
//						} else {
//							return ++pageNo;
//						}
//
//					}
//				} else {
//					if (!Util.isNull(responseBean.getResult())) {
//						PageObject pObject = DataUtil.jsonToObject(responseBean
//								.getParam().get("result"), PageObject.class);
//						if (pObject != null && pObject.getList() != null
//								&& pObject.getList().size() > 0) {
//							String jsonString = DataUtil.objectToJSON(pObject
//									.getList());
//							List<SimpleCommunityBean> sBeanList = DataUtil
//									.jsonToObject(jsonString, List.class);
//							if (!Util.isNull(sBeanList)) {
//								if (pageNo == (1 + pObject.getCount()
//										/ pageSize)) {
//									return pageNo;
//								} else {
//									return ++pageNo;
//								}
//
//							}
//						}
//					}
//				}
//			}
//
//		} catch (Exception e) {
//
//			// 捕捉异常
//			DBHelper.sendlogRecorde("json 内容：" + responseBean + "错误原因："
//					+ e.getMessage());
//		}
//
//		return pageIndex;
//	}

	/***
	 * 
	 * 
	 * 判断服务状态
	 * 
	 * @param context
	 * @return boolean
	 * @exception
	 * @since 1.0.0
	 */
	public static boolean isServiceRun(Context context) {

		ActivityManager am = (ActivityManager) context

		.getSystemService(context.ACTIVITY_SERVICE);

		List<ActivityManager.RunningServiceInfo> list = am
				.getRunningServices(30);

		for (ActivityManager.RunningServiceInfo info : list) {

			if (info.service.getClassName().equals(
					"com.workhi.service.GetMsgService")) {

				return true;
			}
		}
		return false;
	}

	/**
	 * 创建apk文件 createFile(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选)
	 *
	 * @param name
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	public static void createFile(String name) {

		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			File updateDir = new File(Environment.getExternalStorageDirectory()
					+ "/" + "workdb/");
			File updateFile = new File(updateDir + "/" + name);
			if (!updateDir.exists()) {
				updateDir.mkdirs();
			}
			if (!updateFile.exists()) {
				try {
					updateFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 打印消息并且用Toast显示消息
	 * 
	 * @param activity
	 * @param message
	 * @param logLevel
	 *            填d, w, e分别代表debug, warn, error; 默认是debug
	 */
	public static final void toastMessage(final Activity activity,
			final String message) {
		toastMessage(activity, message, null);
	}

	/**
	 * 打印消息并且用Toast显示消息
	 * 
	 * @param activity
	 * @param message
	 * @param logLevel
	 *            填d, w, e分别代表debug, warn, error; 默认是debug
	 */
	public static final void toastMessage(final Activity activity,
			final String message, String logLevel) {
		if ("w".equals(logLevel)) {
			Log.w("sdkDemo", message);
		} else if ("e".equals(logLevel)) {
			Log.e("sdkDemo", message);
		} else {
			Log.d("sdkDemo", message);
		}
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (mToast != null) {
					mToast.cancel();
					mToast = null;
				}
				mToast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
				mToast.show();
			}
		});
	}

	public static final void dismissDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}

	/**
	 * 将图片设置为圆角(放在列表上会有稍稍的卡)
	 */
	@SuppressWarnings("deprecation")
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	public static Bitmap toRoundBitmap(Bitmap bitmap) {

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			left = 0;
			bottom = width;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(4);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);

		// 画白色圆圈
		paint.reset();
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(4);
		paint.setAntiAlias(true);
		canvas.drawCircle(width / 2, width / 2, width / 2 - 4 / 2, paint);
		return output;
	}
}

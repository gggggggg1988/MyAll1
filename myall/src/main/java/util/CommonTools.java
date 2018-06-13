package util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.ParseException;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.AllAplication;

/**
 * 
 * Filename: Description: 公用工具类 Company: 灿天
 * 
 * @author: chjr
 * @version: 1.0 Create at: 2014-8-25 下午6:05:52
 * 
 *           Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014-8-25 chjr 1.0 1.0 Version
 */
public class CommonTools {

	private MediaPlayer mPlayer;
	private SharePreferenceUtil spUtil;
	public CommonTools() {
		mPlayer = new MediaPlayer();
		spUtil=new SharePreferenceUtil(AllAplication.getContext(),"LiWei");
	}

	private int playState = 1;
	private MediaPlayer mediaPlayer = null;

	/**
	 * Ensures that an object reference passed as a parameter to the calling method is not null.
	 *
	 * @param reference an object reference
	 * @param errorMessage the exception message to use if the check fails
	 * @return the non-null reference that was validated
	 * @throws NullPointerException if {@code reference} is null
	 */
	public static <T> T checkNotNull(T reference, String errorMessage) {
		if (reference == null) {
			throw new NullPointerException(errorMessage);
		}
		return reference;
	}

	/***
	 * 
	 * 创建一个处理时间显示的方法
	 */
	public static void handTime(String lastmsgTime, String msgTime,
			TextView msTextView) {

		try {
			if (Util.isNull(lastmsgTime) && !Util.isNull(msgTime)) {

				// 格式化时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

				Date time = sdf.parse(msgTime);
				Date lasttime;
				if (!Util.isNull(lastmsgTime)) {
					lasttime = sdf.parse(lastmsgTime);

					if (time.getTime() - lasttime.getTime() < 60 * 10000) {
						msTextView.setVisibility(View.GONE);
						Log.d("messagetime",
								(time.getMinutes() - lasttime.getMinutes())
										+ "------" + time + "---------"
										+ lastmsgTime);
					}
				}
				Date td = new Date();
				// 今天的时间
				if (td.getYear() == time.getYear()

				&& td.getMonth() == time.getMonth()
						&& td.getDay() == time.getDay()) {

					// 判断是上午还是下午
					if (time.getHours() <= 12 && time.getHours() >= 0) {

						msTextView.setText(DateFormat.format("上午 hh:mm", time));

					} else if (time.getHours() < 24 && time.getHours() > 12) {

						msTextView.setText(DateFormat.format("下午 hh:mm", time));

					}
				} else {

					// 只显示日期
					msTextView.setText((time.getMonth() + 1) + "月"
							+ time.getDate() + "日");
				}
			}
		} catch (ParseException e) {

			e.printStackTrace();

		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取图片的本地存储路径。
	 * 
	 * @param
	 *
	 * @return 图片的本地存储路径。
	 */
	public String getImagePath(Context context, String imgIdName) {

		// 手机SD 卡路径
		// String imageDir = Environment.getExternalStorageDirectory().getPath()
		// + "/ImgTemp/";

		// 获取手机内存路径
		String imageDir = context.getCacheDir() + "/ImgTemp/";
		Log.i("log", "=======================图片路径：" + imageDir);
		File file = new File(imageDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		String imagePath = imageDir + imgIdName + ".jpg";
		return imagePath;
	}

	/**
	 * 将图片存入文件缓存
	 * 
	 * @param
	 *
	 * @param bm
	 *            bitmap对象
	 */
	public Boolean saveBitmap(Context context, Bitmap bm, String imgIdName) {

		if (bm == null) {
			return false;
		}
		// 得到文件名字
		String filename = getImagePath(context, imgIdName);

		File file = new File(filename);
		try {

			file.createNewFile();
			OutputStream outStream = new FileOutputStream(file);

			bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
			outStream.flush();
			outStream.close();

			return true;

		} catch (FileNotFoundException e) {
			Log.w("ImageFileCache", "FileNotFoundException");
		} catch (IOException e) {
			Log.w("ImageFileCache", "IOException");

		}
		return false;
	}

	/***
	 * 
	 * 
	 * 从内存中检测该图片是否存在
	 * 
	 * @param piconId
	 * @return
	 */
	public Boolean checkFileExist(Context context, String piconId) {

		return useTheImage(context, piconId) != null ? true : false;
	}

	/***
	 * 
	 * 根据路径获取 图片
	 * 
	 * @param
	 * @param imgIdName
	 * @return
	 */
	public Drawable useTheImage(Context context, String imgIdName) {
		Drawable drawable = null;
		// 获取图片
		Bitmap bmpDefaultPic = BitmapFactory.decodeFile(
				getImagePath(context, imgIdName), null);

		if (bmpDefaultPic != null) {

			drawable = new BitmapDrawable(bmpDefaultPic);
		}
		return drawable;
	}

	/***
	 * 
	 * 使用手机内卡上的图片
	 * 
	 * @param
	 * @param imgIdName
	 * @return
	 */
	public Bitmap useTheImage1(Context context, String imgIdName) {

		// Bitmap bmpDefaultPic = BitmapFactory.decodeFile(
		// getImagePath(context, imgIdName), null);

		return getSmallBitmap(getImagePath(context, imgIdName));
	}

	/**
	 * 根据路径获得图片并压缩，返回bitmap用于显示
	 * 
	 * @param filePath
	 * @return
	 */
	public static Bitmap getSmallBitmap(String filePath) {// 图片所在SD卡的路径

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		options.inSampleSize = calculateInSampleSize(options, 120, 120);// 自定义一个宽和高
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}
	/***
	 * 
	 * 
	 * 计算图片的缩放值
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;// 获取图片的高
		final int width = options.outWidth;// 获取图片的框
		int inSampleSize = 4;
		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;// 求出缩放值
	}
	/***
	 * 
	 * 
	 * 定义一个下载文件的公共类
	 */
//	public void showFile(Context context, List<SimpleFileBean> res,
//			TextView file_num, GridView grid_files, final Handler handler,
//			final int FILE_REQUEST) {
//
//		file_num.setText("附件(" + res.size() + ")");
//
//		grid_files.setAdapter(new TrendFileGridAdapter(context, res, false));
//
//		grid_files.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//
//				TextView tv_id = (TextView) view.findViewById(R.id.file_id);
//
//				TextView tv_title = (TextView) view
//						.findViewById(R.id.file_content);
//				TextView tv_type = (TextView) view.findViewById(R.id.file_type);
//
//				final String fileid = tv_id.getText().toString();
//				String fileTile = tv_title.getText().toString();
//
//				int type = Integer.parseInt(tv_type.getText().toString());
//
//				final HashMap<String, Object> map = new HashMap<String, Object>();
//
//				map.put("title", subStringMethod(fileTile));
//				map.put("action", "open");
//				map.put("type", type);
//				downFile(fileid, handler, FILE_REQUEST, map, "");
//			}
//		});
//	}
	/**
	 * 
	 * setRecordFile 显示语音文件
	 * 
	 * @param context
	 * @param res
	 * @param handler
	 * @param FILE_REQUEST
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
//	public void setRecordFile(Context context, final SimpleFileBean bean,
//			final Handler handler, final int FILE_REQUEST,
//			final ImageView recordImage, File fileDir,
//			final ArrayList<MediaPlayer> mediaPlayerList) {
//
//		final HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("title", bean.getName());
//		map.put("action", "record");
//		downFile(bean.getId(), handler, FILE_REQUEST, map, "");
//		final File recodeFile = new File(fileDir + "/" + bean.getName());
//		recordImage.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if (recodeFile.exists()) {
//					play(recordImage, recodeFile, mediaPlayerList);
//				}
//			}
//		});
//	}

	/**
	 * 
	 播放录音 void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	@SuppressWarnings("null")
//	protected void play(ImageView rePlayer, File recodeFile,
//			ArrayList<MediaPlayer> mediaPlayerList) {
//
//		if (playState == 1) {
//			mediaPlayer = playRecord(rePlayer, recodeFile);
//			if (mediaPlayerList != null)
//				mediaPlayerList.add(mediaPlayer);
//			rePlayer.setBackgroundResource(R.drawable.play_stop_small);
//			playState = 2;
//		} else if (playState == 2) {
//			try {
//				mediaPlayer.pause();
//			} catch (Exception e) {
//			}
//			rePlayer.setBackgroundResource(R.drawable.play_in_small);
//			playState = 1;
//		}
//	}

 
	public void stopMediaPlayer() {
		if (mediaPlayer != null) {
			try {
				mediaPlayer.stop();
			} catch (Exception e) {
				Log.e("CommonTools", "播放器执行stop时发生异常");
			}
		}
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

 
	public void stopRecord() {

		if (null != mediaPlayer) {

			if (mediaPlayer.isPlaying()) {
				try {
					mediaPlayer.stop();
					playState = 1;
				} catch (Exception e) {
				}
			}
		}
	}
 
	/**
	 * 
	 * 播放录音
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	@SuppressWarnings("resource")
//	protected MediaPlayer playRecord(final ImageView rePlayer, File recodeFile) {
//
//		FileInputStream fis;
//
//		try {
//			fis = new FileInputStream(recodeFile);
//			if (mPlayer.isPlaying()) {
//				mPlayer.pause();
//			}
//			mPlayer.reset();
//			mPlayer.setDataSource(fis.getFD());
//			// 此方法返回与流相关联的文件说明符。
//			mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//			mPlayer.prepare();
//			mPlayer.start();
//			mPlayer.setOnCompletionListener(new OnCompletionListener() {
//
//				@Override
//				public void onCompletion(MediaPlayer arg0) {
//					rePlayer.setBackgroundResource(R.drawable.play_in_small);
//					playState = 1;
//				}
//			});
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return mPlayer;
//	}

	/**
	 * 
	 下载文件方法
	 * 
	 * @exception
	 * @since 1.0.0
	 */
//	public void downFile(final String fileid, final Handler handler,
//			final int FILE_REQUEST, final HashMap<String, Object> map,
//			final String arcId) {
//
//		if (!Util.isNull(fileid)) {
//			/**
//			 * 开启一个异步任务
//			 */
//			new AsyncTask<Void, Void, Void>() {
//				@Override
//				protected Void doInBackground(Void... params) {
//
//					InputStream file_stream = new RequestUrl().getFile(fileid,
//							arcId);
//					Log.i("msg", "fileid:"+fileid+"\narcId"+arcId);
//					Log.i("msg", "下载的文件的url"+(new RequestUrl().getFile(fileid,
//							arcId)));
//					map.put("stream", file_stream);
//					Message msg = handler.obtainMessage();
//					msg.obj = map;
//					msg.what = FILE_REQUEST;
//					handler.sendMessage(msg);
//					return null;
//				}
//			}.execute();
//		}
//	}

	/***
	 * 下载文件
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public long downloadUpdateFile(InputStream stream, String file_url)
			throws Exception {

		int downloadCount = 0;// 已经下载好的大小
		OutputStream outputStream;

		outputStream = new FileOutputStream(file_url, false);// 文件存在则覆盖掉
		byte buffer[] = new byte[1024];
		int readsize = 0;
		while ((readsize = stream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, readsize);
			downloadCount += readsize;// 时时获取下载到的大小
		}
		stream.close();
		outputStream.close();
		return downloadCount;
	}

	/**
	 * 
	 * subStringMethod 处理字符串 void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	protected static String subStringMethod(String str) {

		String result = "";
		if (!Util.isNull(str)) {
			int index = str.lastIndexOf("(");

			result = str.substring(0, index);

		}
		return result;
	}
	/**
	 * 添加列表表头
	 * @exception
	 * @since 1.0.0
	 */
//	public View addHeader(Activity con,OnClickListener listener,String state) {
//		// 实例化各个界面的布局对象
//		LayoutInflater mLi = LayoutInflater.from(con);
//		View view = mLi.inflate(R.layout.custom_top_layout, null);
//		TextView tvState=(TextView) view.findViewById(R.id.tv_state);
//		view.findViewById(R.id.rlin_person).setOnClickListener(listener);
//		view.findViewById(R.id.iv_person).setOnClickListener(listener);
//		tvState.setText(state);
//		return view;
//	}
}

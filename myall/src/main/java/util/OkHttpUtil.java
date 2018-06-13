package util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 达拉然的风 on 2017/5/8.
 */

public class OkHttpUtil {
    public static OkHttpUtil okHttpUtil;
    public final String LOGTAG = "OkHttpMsg";
    private OkHttpClient mOkHttpClient;

    private OkHttpUtil() {
        init();
    }

    public synchronized static OkHttpUtil getInstance() {
        if (okHttpUtil == null) {
            okHttpUtil = new OkHttpUtil();
        }
        return okHttpUtil;
    }

    private void init() {
        mOkHttpClient = new OkHttpClient();
    }

    /**
     * 这是一个利用OKHttp做get请求的方法
     * 由于okhttp的网络请求并不在UI线程上。利用回调来实现
     *
     * @param url      网络请求的url
     * @param activity
     * @param callback 请求结果回调
     */
    public void asynGet(String url, final Activity activity,
                        final ResultCallBack callback) {
        showLog("正在进行Get请求，url：" + url);
        final Request request = new Request.Builder()
                .url(url)
                .build();
        deliveryResult(callback, request, activity);
    }

    /**
     * 这是一个利用OKHttp做POST请求的方法
     * 由于okhttp的网络请求并不在UI线程上。利用回调来实现
     *
     * @param url      网络请求的url
     * @param maps     Post上传的参数集合
     * @param activity
     * @param callback 请求结果回调
     */
    public void asynPost(String url, final Activity activity, Map<String, String> maps,
                         final ResultCallBack callback) {
        showLog("正在进行Post请求，url：" + url + "\n上传的值是：" + maps.toString());
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> map : maps.entrySet()) {
            formBody.add(map.getKey(), map.getValue());
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBody.build())
                .build();
        deliveryResult(callback, request, activity);
    }

    /**
     * 多文件上传
     *
     * @param url      文件上传的url
     * @param files    上传的文件
     * @param callback 结果的回调函数
     * @param activity
     */
    private void upLoadFile(String url, List<File> files, Map<String, String> params, ResultCallBack callback, Activity activity) {
        showLog("正在进行文件上传，url：" + url);
        /* form的分割线,自己定义 */
        String boundary = "xx--------------------------------------------------------------xx";
        MultipartBody.Builder mBody = new MultipartBody.Builder(boundary).setType(MultipartBody.FORM);
        for (File file : files) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            mBody.addFormDataPart("file", file.getName(), fileBody);
        }
        for (Map.Entry<String, String> param : params.entrySet()) {
            mBody.addFormDataPart(param.getKey(), param.getValue());
        }
        Request request = new Request.Builder()
                .url(url)
                .post(mBody.build())
                .build();
        deliveryResult(callback, request, activity);
    }

    public void download(final String url, final String saveDir, final String fileName, final String fileId, final OnDownloadListener listener) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept-Encoding","identity")
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed(e.getLocalizedMessage(), fileId);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                File file = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                String savePath = isExistDir(saveDir);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
//                   File file = new File(savePath, getNameFromUrl(url));
                    Log.i(LOGTAG, "savePath---" + saveDir + "\tfileName---" + fileName);
                    file = new File(savePath, fileName);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        listener.onDownloading(progress, file, fileId);
                    }
                    fos.flush();
                    // 下载完成
                    listener.onDownloadSuccess(file, file, fileId);
                } catch (Exception e) {
                    listener.onDownloadFailed(e.getLocalizedMessage(), fileId);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    /**
     * 判断文件是否存在
     *
     * @param file
     */
    public void judeFileExists(File file) {

        if (file.exists()) {
            Log.i(LOGTAG, "file exists");
        } else {
            Log.i(LOGTAG, "file not exists, create it ...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 判断文件夹是否存在
     *
     * @param file
     */
    public void judeDirExists(File file) {

        if (file.exists()) {
            if (file.isDirectory()) {
                Log.i(LOGTAG, "dir exists");
            } else {
                Log.i(LOGTAG, "the same name file exists, can not create dir");
            }
        } else {
            Log.i(LOGTAG, "dir not exists, create it ...");
            file.mkdir();
        }

    }

    /**
     * 判断下载目录是否存在
     *
     * @param saveDir 保存路径
     * @return
     * @throws IOException
     */
    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    /**
     * @param url
     * @return 从下载连接中解析出文件名
     */
    @NonNull
    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    /**
     * 后台打印Log信息
     *
     * @param msg 需要打印的内容
     */
    private void showLog(String msg) {
        Log.i(LOGTAG, msg);
    }

    /**
     * 创建Call开始异步请求
     *
     * @param callback 请求结果回调
     * @param request  请求体
     * @param activity
     */
    private void deliveryResult(final ResultCallBack callback, final Request request, final Activity activity) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(LOGTAG, "请求失败，错误信息：" + e.getLocalizedMessage());
                        callback.failListener(request, e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(LOGTAG, "请求成功");
                        callback.successListener(str);
                    }
                });
            }
        });
    }

    /**
     * 判断文件是否存在
     *
     * @param f
     * @return
     */
    public boolean isExists(File f) {
        try {
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

//    /**
//     * 打开附件的方法
//     *
//     * @param f
//     * @param context
//     */
//    public void openFile(File f, Context context) {
//        Log.i(LOGTAG, "正在打开附件打--------" + f.getName());
//        try {
//            String end = f.getName().substring(f.getName().lastIndexOf(".")
//                    + 1, f.getName().length()).toLowerCase();
//            if(end.equals("amr")){
//                AudioRecoderUtils.getInstance().playerStart(f.getAbsolutePath());
//            }else {
//                Intent intent = new Intent();
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.addCategory("android.intent.category.DEFAULT");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//      /* 调用getMIMEType()来取得MimeType */
//                String type = getMIMEType(f);
//      /* 设置intent的file与MimeType */
//                intent.setDataAndType(Uri.fromFile(f), type);
//                context.startActivity(intent);
//            }
//        } catch (Exception e) {
//            Log.i(LOGTAG, "打开附件" + f.getName() + "报错了，错误是-----" + e.getLocalizedMessage());
//        }
//    }

    /**
     * 判断文件MimeType的method
     */
    private String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
      /* 取得扩展名 */
        String end = fName.substring(fName.lastIndexOf(".")
                + 1, fName.length()).toLowerCase();
      /* 依扩展名的类型决定MimeType */
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||
                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            type = "audio/*";
        } else if (end.equals("3gp") || end.equals("mp4")) {
            type = "video/*";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
                end.equals("jpeg") || end.equals("bmp")) {
            type = "image/*";
        } else if (end.equals("apk")) {
            type = "application/vnd.android.package-archive";
        } else if (end.equals("pdf")) {
            type = "application/pdf";
        } else if (end.equals("xls") || end.equals("xlsx")) {
            type = "application/ms-excel";
        } else if (end.equals("doc") || end.equals("docx")) {
            type = "application/mswork";
        } else if (end.equals("ppt") || end.equals("pptx")) {
            type = "application/vnd.ms-powerpoint";
        } else {
            type = "*/*";
        }
        return type;
    }

    /**
     * get、post请求回调
     */
    public interface ResultCallBack {
        void successListener(String response);

        void failListener(Request response, IOException e);
    }

    /**
     * 文件下载监听
     */
    public interface OnDownloadListener {
        /**
         * 下载成功
         *
         * @param downfile 下载的文件
         */
        void onDownloadSuccess(File downfile, File file, String fileId);

        /**
         * @param progress 下载进度
         */
        void onDownloading(int progress, File file, String fileId);

        /**
         * 下载失败
         */
        void onDownloadFailed(String error, String fileId);
    }
}

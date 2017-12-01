package com.example.administrator.myall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lidroid.xutils.util.LogUtils;
import com.vise.log.ViseLog;

public class ContentActivity extends AppCompatActivity {
    private WebView m_webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
        setContentView(R.layout.activity_content);
        Intent intent = getIntent();
//        Parcelable parcelable = intent.getParcelableExtra("data");
//        Data data = (Data) parcelable;
        final String url = intent.getStringExtra("data");

        initViews();
        WebSettings settings = m_webView.getSettings();
        settings.setJavaScriptEnabled(true);
//        m_webView.loadData(url,"text/html", "utf-8");
        m_webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }
        });
        m_webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                ViseLog.i("url------"+url);
                view.loadUrl(url);
                return true;
            }
        });
        // 设置WevView要显示的网页
//        m_webView.loadDataWithBaseURL(null, data.getContent(), "text/html", "utf-8",
//                null);
        m_webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        m_webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                emulateShiftHeld(v);
                WebView.HitTestResult hitTestResult = m_webView.getHitTestResult();
                final int type = hitTestResult.getType();
                switch (type) {
                         case WebView.HitTestResult.IMAGE_TYPE:
                             LogUtils.i("type:1");
                             break;case WebView.HitTestResult.EDIT_TEXT_TYPE:
                             LogUtils.i("type:2");

                             break;case WebView.HitTestResult.PHONE_TYPE:
                             LogUtils.i("type:3");

                             break;case WebView.HitTestResult.EMAIL_TYPE:
                             LogUtils.i("type:4");

                             break;case WebView.HitTestResult.SRC_ANCHOR_TYPE:
                             LogUtils.i("type:5");

                             break;case WebView.HitTestResult.UNKNOWN_TYPE:
                             LogUtils.i("type:6");

                             break;

                         default:
                             break;
                       }

                return false;
            }
        });
        
        m_webView.loadUrl(url);

    }
    /**
     * 网页里 复制粘贴
     * @param view webView
     * @author ethan
     */
    private void emulateShiftHeld(KeyEvent.Callback view)
    {
        try
        {
            KeyEvent shiftPressEvent = new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_SHIFT_LEFT, 0, 0);
            shiftPressEvent.dispatch(view);
        } catch (Exception e)
        {
        }
    }

    private void initViews() {
        m_webView = (WebView) findViewById(R.id.web_content);
    }
}

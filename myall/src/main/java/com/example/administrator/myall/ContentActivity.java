package com.example.administrator.myall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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

import java.lang.reflect.Method;

import util.StatusBarUtil;
import widget.ScrollListenWebview;

public class ContentActivity extends AppCompatActivity {
    private ScrollListenWebview m_webView;
    private Toolbar viewById;
    private float mParallaxImageHeight=200f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        getWindow().setReenterTransition(explode);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        new MenuInflater(this).inflate(R.menu.actionbar_overflow_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    /**
     * menu item 上面图标显示出来的关键方法
     * @param featureId
     * @param view
     * @param menu
     * @return
     */
    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try{
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }

        return super.onPreparePanel(featureId, view, menu);
    }

    private void initViews() {
        m_webView = (ScrollListenWebview) findViewById(R.id.web_content);
        m_webView.setScrollListener(new ScrollListenWebview.ScrollListener() {
            @Override
            public void onScrollChange(int l, int t, int lo, int to) {
                int baseColor = getResources().getColor(R.color.colorPrimary);
                float alpha = Math.min(1, (float) t / mParallaxImageHeight);
                viewById.setBackgroundColor(StatusBarUtil.mixtureColor(baseColor,alpha));
            }
        });
        viewById = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(viewById);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        viewById.setTitle("Liwei Test");
        //supportActionBar.setHomeAsUpIndicator();
        supportActionBar.setTitle("");
        viewById.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentActivity.this.finish();
            }
        });
    }
}

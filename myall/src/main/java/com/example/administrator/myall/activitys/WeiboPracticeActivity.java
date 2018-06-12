package com.example.administrator.myall.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.myall.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import util.StatusBarUtil;


/**
 * 微博主页
 */
public class WeiboPracticeActivity extends AppCompatActivity {

    private int mOffset = 0;
    private int mScrollY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_weibo);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //状态栏透明和间距处理
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, toolbar);

        final View parallax = findViewById(R.id.parallax);
        final View buttonBar = findViewById(R.id.buttonBarLayout);
        final NestedScrollView scrollView = (NestedScrollView)findViewById(R.id.scrollView);
        final RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);

        findViewById(R.id.attention).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"点击了关注",Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.leaveword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"点击了留言",Toast.LENGTH_SHORT).show();
            }
        });

        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(3000);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                //onHeaderMoving  就是下拉刷新的时候的下拉动作引起头部向下运动的过程
                mOffset = offset / 2;//实现向下拉动的时候，上面背景图片运动速度是拉动距离的一般
                parallax.setTranslationY(mOffset - mScrollY); //经测试，这里的mscrollY一直为零
                Log.i("msg", "offset---" + mOffset + "mScrollY---" + mScrollY + "percent----" + percent);
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
//            @Override
//            public void onHeaderPulling(@NonNull RefreshHeader header, float percent, int offset, int bottomHeight, int maxDragHeight) {
//                mOffset = offset / 2;
//                parallax.setTranslationY(mOffset - mScrollY);
//                toolbar.setAlpha(1 - Math.min(percent, 1));
//            }
//            @Override
//            public void onHeaderReleasing(@NonNull RefreshHeader header, float percent, int offset, int bottomHeight, int maxDragHeight) {
//                mOffset = offset / 2;
//                parallax.setTranslationY(mOffset - mScrollY);
//                toolbar.setAlpha(1 - Math.min(percent, 1));
//            }
        });
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            private int lastScrollY = 0;
            private int h = DensityUtil.dp2px(170);
            private int color = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)&0x00ffffff;
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (lastScrollY < h) {//实现向下滑动距离小于h之内才进行头部透明度的改变操作
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    buttonBar.setAlpha(1f * mScrollY / h);
                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    parallax.setTranslationY(mOffset - mScrollY);//经测试mOffset一直为零  向上向下滑动的时候，小猫图和scrollview同时运动

                    Log.i("onScrollChange---", "offset---" + mOffset + "mScrollY---" + mScrollY );
                }
                lastScrollY = scrollY;
            }
        });
        buttonBar.setAlpha(0);

        toolbar.setBackgroundColor(0);
    }

}

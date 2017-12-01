package com.example.administrator.myall.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myall.R;
import com.example.administrator.myall.utils.ShakeListener;
import com.example.administrator.myall.utils.UIUtils;
import com.example.administrator.myall.widget.StellarMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class RandomRecommand extends AppCompatActivity {
    private StellarMap rootView ;
    private List<String> mDatas = new ArrayList<>();
    private RecommendAdapter mAdatper;
    private ShakeListener mShake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_recommand);
        initDatas();
        rootView = (StellarMap) findViewById(R.id.root_view);
        // 设置样式
        rootView.setInnerPadding(UIUtils.dip2px(10), UIUtils.dip2px(10), UIUtils.dip2px(10), UIUtils.dip2px(10));

        // 设置数据的方法
        mAdatper = new RecommendAdapter();
        rootView.setAdapter(mAdatper);

        // 设置随机摆放区域
        rootView.setRegularity(15, 20);

        // 设置默认选中页
        rootView.setGroup(0, true);

        mShake = new ShakeListener(UIUtils.getContext());
        mShake.setOnShakeListener(new ShakeListener.OnShakeListener() {

            @Override
            public void onShake()
            {
                // 当摇一摇的时候的回调
                int currentGroup = rootView.getCurrentGroup();
                if (currentGroup == mAdatper.getGroupCount() - 1)
                {
                    currentGroup = 0;
                }
                else
                {
                    currentGroup++;
                }
                rootView.setGroup(currentGroup, true);
            }
        });
    }

    private void initDatas() {
        for (int i = 0; i < 90; i++) {
            mDatas.add("radomData" + i);
        }
    }

    private class RecommendAdapter implements StellarMap.Adapter {
        private final static int	PER_PAGE_SIZE	= 15;

        // 有几页面
        @Override
        public int getGroupCount()
        {
            if (mDatas != null)
            {
                int size = mDatas.size();

                int count = size / PER_PAGE_SIZE;

                if (size % PER_PAGE_SIZE > 0)
                {
                    count++;
                }
                return count;
            }
            return 0;
        }

        // 第group页面有几条数据
        @Override
        public int getCount(int group)
        {
            if (mDatas != null)
            {

                int size = mDatas.size();

                // 如果是最后一页
                if (group == (getGroupCount() - 1))
                {
                    if (size % PER_PAGE_SIZE > 0)
                    {
                        // 最后一页有多余,不够一页的数量
                        return size % PER_PAGE_SIZE;
                    }
                    else
                    {
                        return PER_PAGE_SIZE;
                    }
                }
                return PER_PAGE_SIZE;
            }
            return 0;
        }

        // 提供View显示
        @Override
        public View getView(int group, int position, View convertView)
        {
            int index = PER_PAGE_SIZE * group + position;
            String data = mDatas.get(index);

            // 返回随机大小，随机颜色的textView
            TextView tv = new TextView(UIUtils.getContext());

            // 设置数据
            tv.setText(data);

            Random rdm = new Random();
            // 设置颜色
            int alpha = 0xff;
            int red = rdm.nextInt(170) + 30;// 30 --200
            int green = rdm.nextInt(170) + 30;
            int blue = rdm.nextInt(170) + 30;
            int argb = Color.argb(alpha, red, green, blue);
            tv.setTextColor(argb);

            // 设置大小
            int size = UIUtils.dip2px(rdm.nextInt(11) + 14);// 14sp -- > 24sp
            tv.setTextSize(size);

            return tv;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree)
        {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn)
        {
            // TODO Auto-generated method stub
            return 0;
        }
    }
}

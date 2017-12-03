package com.example.administrator.myall.activitys;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myall.R;
import com.example.administrator.myall.fragments.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class CoordinateAndViewpager extends AppCompatActivity {
    ViewPager mViewPager;
    List<Fragment> mFragments;

    String[] mTitles = new String[]{
            "主页", "微博", "相册"
    };
    private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate_and_viewpager);
        // 第一步，初始化ViewPager和TabLayout
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        setupViewPager();


    }

    private void setupViewPager() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
            mFragments.add(listFragment);
        }
        // 第二步：为ViewPager设置适配器
        BaseFragmentAdapter adapter =
                new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);

        mViewPager.setAdapter(adapter);
        //  第三步：将ViewPager与TableLayout 绑定在一起
        mTabLayout.setupWithViewPager(mViewPager);


    }

    class BaseFragmentAdapter extends FragmentPagerAdapter{
private FragmentManager fragmentManager ;
        private List<Fragment> m_fragments;
        private String[] m_titles;
        public BaseFragmentAdapter(FragmentManager supportFragmentManager, List<Fragment> mFragments, String[] mTitles) {
            super(supportFragmentManager);
            fragmentManager = supportFragmentManager;
            m_fragments = mFragments;
            m_titles = mTitles;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }


        @Override
        public Fragment getItem(int position) {
            return m_fragments.get(position);
        }
    }
}

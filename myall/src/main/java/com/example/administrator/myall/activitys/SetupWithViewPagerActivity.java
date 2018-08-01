package com.example.administrator.myall.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.myall.R;
import com.example.administrator.myall.widget.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class SetupWithViewPagerActivity extends AppCompatActivity {
    private static final String TAG = SetupWithViewPagerActivity.class.getSimpleName();
//    private ActivityWithViewPagerBinding bind;
    private VpAdapter adapter;
    private BottomNavigationViewEx m_bottomNavigationViewEx,bnveCenterIconOnly;
    private ViewPager m_viewPager;

    // collections
    private List<Fragment> fragments;// used for ViewPager adapter


    private void initCenterIconOnly() {
        disableAllAnimation(bnveCenterIconOnly);
        int centerPosition = 2;
        // attention: you must ensure the center menu item title is empty
        // make icon bigger at centerPosition
        bnveCenterIconOnly.setIconSizeAt(centerPosition, 48, 48);
        bnveCenterIconOnly.setItemBackground(centerPosition, R.color.colorGreen);
        bnveCenterIconOnly.setIconTintList(centerPosition,
           getResources().getColorStateList(R.color.selector_item_gray_color));
        bnveCenterIconOnly.setIconMarginTop(centerPosition, BottomNavigationViewEx.dp2px(this, 4));
        // you could set a listener for bnve. and return false when click the center item so that it won't be checked.
        bnveCenterIconOnly.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_add) {
                    Toast.makeText(SetupWithViewPagerActivity.this, "add", Toast.LENGTH_SHORT).show();
                    return false;//表示点击后不会修改按钮样式
                }
                return true;
            }
        });
    }

    private void disableAllAnimation(BottomNavigationViewEx bnveCenterIconOnly) {
        bnveCenterIconOnly.enableAnimation(false);
        bnveCenterIconOnly.enableShiftingMode(false);
        bnveCenterIconOnly.enableItemShiftingMode(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_view_pager);
//        bind = DataBindingUtil.setContentView(this, R.layout.activity_with_view_pager);

        initView();
        initData();
        initCenterIconOnly();
        initEvent();
    }

    /**
     * change BottomNavigationViewEx style
     */
    private void initView() {
        m_bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bnve);
        bnveCenterIconOnly = (BottomNavigationViewEx) findViewById(R.id.bn_center);
        m_bottomNavigationViewEx.enableShiftingMode(true);
        m_bottomNavigationViewEx.enableAnimation(true);
        m_viewPager = (ViewPager) findViewById(R.id.vp);
    }


    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(12, 2, true)
                .bindTarget(m_bottomNavigationViewEx.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
                            Toast.makeText(SetupWithViewPagerActivity.this, "removed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * create fragments
     */
    private void initData() {
        fragments = new ArrayList<>();

        // create music fragment and add it
        BlankFragment musicFragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.music));
        musicFragment.setArguments(bundle);
        // create backup fragment and add it
        BlankFragment backupFragment = new BlankFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.backup));
        backupFragment.setArguments(bundle);
        // create friends fragment and add it
        BlankFragment friendsFragment = new BlankFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.friends));
        friendsFragment.setArguments(bundle);
        // add to fragments for adapter
        fragments.add(musicFragment);
        fragments.add(backupFragment);
        fragments.add(friendsFragment);

        // set adapter
        adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        m_viewPager.setAdapter(adapter);
        m_bottomNavigationViewEx.setupWithViewPager(m_viewPager);

        addBadgeAt(0, 100);
    }

    /**
     * set listeners
     */
    private void initEvent() {
        // set listener to do something then item selected
        m_bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d(TAG, item.getItemId() + " item was selected-------------------");
                // you can return false to cancel select
                return true;
            }
        });

    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }

}

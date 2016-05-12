package com.example.administrator.myall;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import fragment.ContentFragment;
import fragment.MenuFragment;
import util.DimentionUtil;

public class MainActivity extends SlidingFragmentActivity {

    private static final String TAG_CONTENT ="content";
    private static final String TAG_MENU = "menu";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_frame_layout);
        setBehindContentView(R.layout.main_menu_layout);
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        menu.setBehindWidth(DimentionUtil.dpi2Px(this, 250));
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBackgroundColor(getResources().getColor(R.color.colorMenu));

        initViews();
    }

    private void initViews() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_frame, new ContentFragment(), TAG_CONTENT);
        transaction.replace(R.id.main_menu, new MenuFragment(), TAG_MENU);
        transaction.commit();
    }

    public ContentFragment getContentFragment(){
        FragmentManager manager = getSupportFragmentManager();
        return (ContentFragment) manager.findFragmentByTag(TAG_CONTENT);
    }

    public MenuFragment getMenuFragment(){
        FragmentManager manager = getSupportFragmentManager();
        return (MenuFragment) manager.findFragmentByTag(TAG_MENU);
    }
}

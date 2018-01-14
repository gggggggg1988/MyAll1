package com.example.administrator.myall.activitys;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.example.administrator.myall.R;
import com.example.administrator.myall.widget.BouncingView;

/**
 * Created by Administrator on 2018/1/13 0013.
 */

class MenuBar {
    private final View rootView;
    private ViewGroup mParentVG;
    private BouncingView bounceView;
    public MenuBar(View view,int resId) {
        mParentVG = findRootParent(view);
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        rootView = inflater.inflate(resId, null, false);
        bounceView = (BouncingView) rootView.findViewById(R.id.rv);
    }
    public void show(){
        if (rootView.getParent()!=null ) {
            mParentVG.removeView(rootView);
        }

        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        param.gravity = Gravity.BOTTOM;
        mParentVG.addView(rootView,param);
        bounceView.show();
    }
    private static ViewGroup findRootParent(View view) {
        //不断找父容器，直到找到r.id.content这个viewgroup
        do {
            if (view instanceof FrameLayout) {
                if (view.getId()==android.R.id.content ) {
                    return (ViewGroup) view;
                }
            }
            if (view!=null ) {
                ViewParent parent = view.getParent();
                view = parent instanceof View ? (View)parent : null;

            }

        } while (view != null);
        return null;
    }

    public static MenuBar make(View view,int resId) {
        return new MenuBar(view,resId);
    }
}

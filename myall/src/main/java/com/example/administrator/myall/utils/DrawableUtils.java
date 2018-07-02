package com.example.administrator.myall.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * @项目名: GooglePlay56
 * @包名: org.itheima56.googleplay.utils
 * @类名: DrawableUtils
 * @创建者: 肖琦
 * @创建时间: 2015-5-7 下午2:42:13
 * @描述: TODO
 * 
 * @svn版本: $Rev: 35 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-05-07 14:53:20 +0800 (Thu, 07 May 2015) $
 * @更新描述: TODO
 */
public class DrawableUtils
{

	public static GradientDrawable getShape(int shape, int radius, int argb)
	{
		GradientDrawable gd = new GradientDrawable();
		gd.setShape(shape);// 设置形状
		gd.setCornerRadius(radius);// 设置圆角
		gd.setColor(argb);
		return gd;
	}

	public static StateListDrawable getSelector(Drawable normalBg, Drawable pressedBg)
	{
		StateListDrawable selector = new StateListDrawable();
		selector.addState(new int[] { android.R.attr.state_pressed }, pressedBg);
		selector.addState(new int[] {}, normalBg);
		return selector;
	}
}

package com.example.administrator.myall.utils;

import android.view.View;

import com.lidroid.xutils.BitmapUtils;

/**
 * @项目名: GooglePlay56
 * @包名: org.itheima56.googleplay.utils
 * @类名: BitmapHelper
 * @创建者: 肖琦
 * @创建时间: 2015-5-6 下午2:27:06
 * @描述: 图片加载的工具类
 * 
 * @svn版本: $Rev: 21 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-05-06 14:36:43 +0800 (Wed, 06 May 2015) $
 * @更新描述: TODO
 */
public class BitmapHelper
{
	private static BitmapUtils utils;

	static
	{
		utils = new BitmapUtils(UIUtils.getContext());
	}

	public static <T extends View> void display(T container, String uri)
	{
		utils.display(container, uri);
	}
}

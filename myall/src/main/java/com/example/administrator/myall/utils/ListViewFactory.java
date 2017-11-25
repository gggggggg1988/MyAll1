package com.example.administrator.myall.utils;

import android.graphics.Color;
import android.widget.ListView;

/**
 * @项目名: GooglePlay56
 * @包名: org.itheima56.googleplay.utils
 * @类名: ListViewFactory
 * @创建者: 肖琦
 * @创建时间: 2015-5-7 上午9:05:35
 * @描述: TODO
 * 
 * @svn版本: $Rev: 25 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-05-07 09:09:48 +0800 (Thu, 07 May 2015) $
 * @更新描述: TODO
 */
public class ListViewFactory
{

	public static ListView getListView()
	{
		ListView listView = new ListView(UIUtils.getContext());

		// 属性设置
		listView.setCacheColorHint(Color.TRANSPARENT);
		listView.setSelector(android.R.color.transparent);
		listView.setDividerHeight(0);
		listView.setScrollingCacheEnabled(false);
//		listView.setBackgroundColor(UIUtils.getColor(color.bg));

		return listView;
	}
}

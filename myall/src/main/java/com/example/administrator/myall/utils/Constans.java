package com.example.administrator.myall.utils;

/**
 * @项目名: GooglePlay56
 * @包名: org.itheima56.googleplay.utils
 * @类名: Constans
 * @创建者: 肖琦
 * @创建时间: 2015-5-6 下午2:24:50
 * @描述: TODO
 * 
 * @svn版本: $Rev: 40 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-05-08 10:02:11 +0800 (Fri, 08 May 2015) $
 * @更新描述: TODO
 */
public interface Constans
{

//	String	SERVER_URL		= "http://10.0.2.2:8080/GooglePlayServer/";
	String	SERVER_URL		= "http://192.168.191.1:8080/GooglePlayServer/";
//	String	SERVER_URL		= "http://localhost:8090/";

	String	IMAGE_BASE_URL	= SERVER_URL + "image?name=";

	int		PAGER_SIZE		= 20;										// 每页显示的数据量

	long	REFRESH_DELAY	= 5 * 60 * 1000;
}

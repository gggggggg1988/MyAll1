package util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@TargetApi(Build.VERSION_CODES.HONEYCOMB) public class SharePreferenceUtil {
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;
	
	public SharePreferenceUtil(Context context, String file) {
		sp = context.getSharedPreferences(file, context.MODE_PRIVATE);
		editor = sp.edit();
	}
	//用户的validateCode，用于判断是否在其他设备上登陆
	public void setValidateCode(String validateCode){
		editor.putString("validateCode", validateCode);
		editor.commit();
	}
	public String getValidateCode() {
		return sp.getString("validateCode", "");
	}
	//判断是否为领导
		public void setIsSuper(String validateCode){
			editor.putString("isSuper", validateCode);
			editor.commit();
		}
		public String getIsSuper() {
			return sp.getString("isSuper", "");
		}

	// 用户的Pid
	public void setPersonId(String personId) {
		editor.putString("personId", personId);
		editor.commit();
	}

	public String getPersonId() {
		return sp.getString("personId", "");
	}
	// 用户的密码
	public void setPasswd(String passwd) {
		editor.putString("pwd", passwd);
		editor.commit();
	}

	public String getPasswd() {
		return sp.getString("pwd", "");
	}

	// 用户的登陆账号
	public void setUName(String name) {
		editor.putString("name", name);
		editor.commit();
	}

	public boolean getNoticeShow() {
		return sp.getBoolean("noticeshow", false);
	}
			public void setNoticeShow(boolean isChecked) {
			editor.putBoolean("noticeshow", isChecked);
			editor.commit();
		}

		// 用户的账号
		public String getUName() {
			return sp.getString("name", "");
		}
	// 用户显示昵称
	public void setPName(String pName) {
		editor.putString("pname", pName);
		editor.commit();
	}
	
	/** 用户名称 */
	public String getPName() {
		return sp.getString("pname", "");
	}
	/** 设置sessionId */
	public void setSessionId(String sessionId) {
		editor.putString("sessionId", sessionId);
		editor.commit();
	}
	public void setRoles(String roles){
		editor.putString("roles", roles);
		editor.commit();
	}
	public String getRoles() {
		return sp.getString("roles", "");
	}
	public void setMessagePageConfig(String messagePageConfig){
		editor.putString("messagePageConfig", messagePageConfig);
		editor.commit();
	}
	public String getMessagePageConfig() {
		return sp.getString("messagePageConfig", "");
	}
	/** 得到sessionId */
	public String getSessionId() {
		return sp.getString("sessionId", "");
	}

	public void setOrgPid(String pid) {
		editor.putString("OrgPid", pid);
		editor.commit();
	}


	/** 得到人员ID */
	public String getOrgPid() {
		return sp.getString("OrgPid", "");
	}

	public void setCid(String cid) {
		editor.putString("cid", cid);
		editor.commit();
	}

	/** 得到单位ID */
	public String getCid() {
		return sp.getString("cid", "");
	}

	/** 得到默认单位ID */
	public String getOrgCorpId() {
		return sp.getString("orgCorpId", "");
	}

	/** 设置默认单位id */
	public void setOrgCorpId(String orgCorpId) {
		editor.putString("orgCorpId", orgCorpId);
		editor.commit();
	}

	public String getOpenid() {
		return sp.getString("openid", "");
	}

	public void setOpenid(String openId) {
		editor.putString("openid", openId);
		editor.commit();
	}

	public String getAccessToken() {
		return sp.getString("accessToken", "");
	}

	public void setAccessToken(String accessToken) {
		editor.putString("accessToken", accessToken);
		editor.commit();
	}

	public String getExpiresIn() {
		return sp.getString("expiresIn", "");
	}

	public void setExpiresIn(String expiresIn) {
		editor.putString("expiresIn", expiresIn);
		editor.commit();
	}

	/** 得到所有的单位名称 */
	public String[] getOrgNameList() {
		Set<String> unitSet = new HashSet<String>();
		unitSet = sp.getStringSet("unitName", unitSet);
		String[] data = null;
		if (unitSet.size() > 0) {
			data = (String[]) unitSet.toArray(new String[unitSet.size()]);
		}
		return data;
	}

	public void setOrgNameList(ArrayList<String> unitName) {
		Set<String> unitSet = new HashSet<String>();
		unitSet.addAll(unitName);
		editor.putStringSet("unitName", unitSet);
		editor.commit();
	}

	/** 得到所有的单位ID */
	@SuppressLint("NewApi") public String[] getOrgIdList() {
		Set<String> unitSet = new HashSet<String>();
		unitSet = sp.getStringSet("unitId", unitSet);
		String[] data = null;
		if (unitSet.size() > 0) {
			data = (String[]) unitSet.toArray(new String[unitSet.size()]);
		}
		return data;
	}

	public void setOrgIdList(ArrayList<String> unitId) {
		Set<String> unitSet = new HashSet<String>();
		unitSet.addAll(unitId);
		editor.putStringSet("unitId", unitSet);
		editor.commit();
	}

	// 是否在后台运行标记
	public void setIsStart(boolean isStart) {
		editor.putBoolean("isStart", isStart);
		editor.commit();
	}

	public boolean getIsStart() {
		return sp.getBoolean("isStart", false);
	}

	// 是否第一次运行本应用
	public void setIsFirst(boolean isFirst) {
		editor.putBoolean("isFirst", isFirst);
		editor.commit();
	}

	public boolean getisFirst() {
		return sp.getBoolean("isFirst", true);
	}

	// 是否有新的任务消息
	public void setIsNews(boolean isFirst) {
		editor.putBoolean("isNews", isFirst);
		editor.commit();
	}

	public boolean getisNews() {
		return sp.getBoolean("isNews", true);
	}

	/**
	 * 设置手机序列号
	 * 
	 * @param
	 */
	public void setImei(String imei) {
		editor.putString("imei", imei);
		editor.commit();
	}

	public String getImei() {
		return sp.getString("imei", "");
	}

	/**
	 * 设置手机序Imsi
	 * 
	 * @param
	 */
	public void setImsi(String imsi) {
		editor.putString("imsi", imsi);
		editor.commit();
	}

	public String getImsi() {
		return sp.getString("imsi", "");
	}

	/**
	 * 设置手机型号
	 */
	public void setPhoneType(String pType) {
		editor.putString("ptype", pType);
		editor.commit();
	}

	public String getPhoneType() {
		return sp.getString("ptype", "");
	}

	/* /** 登陆人的部门Name */
	public void setLoginDepartName(String dname) {
		editor.putString("dname", dname);
		editor.commit();
	}

	public String getLoginDepartName() {
		return sp.getString("dname", "");
	}

	/* /** 登陆人的部门 Id */
	public void setLoginDepartId(String loginDepartId) {
		editor.putString("loginDepartId", loginDepartId);
		editor.commit();
	}

	public String getLoginDepartId() {
		return sp.getString("loginDepartId", "");
	}

	/*
	 * 
	 * 保存登陆人头像
	 */
	public void setLoginHead(String loginHead) {
		editor.putString("loginHead", loginHead);
		editor.commit();
	}

	public String getLoginHead() {
		return sp.getString("loginHead", "");
	}

	/**
	 * 是否是领导
	 * 
	 * @since 1.0.0
	 */
	public void setIsSuperviseManager(String isSuperviseManager) {
		editor.putString("isSuperviseManager", isSuperviseManager);
		editor.commit();
	}

	public String getIsSuperviseManager() {
		return sp.getString("isSuperviseManager", "false");
	}

	/**
	 * 是否是督察员
	 * 
	 * @since 1.0.0
	 */
	public void setIsSupervise(String isSupervise) {
		editor.putString("isSupervise", isSupervise);
		editor.commit();
	}

	public String getisSupervise() {
		return sp.getString("isSupervise", "false");
	}

	/*
	 * 
	 * 手势密码存储
	 */
	public void setGesturePsd(String gesturePsd) {
		editor.putString("gesturePsd", gesturePsd);
		editor.commit();
	}

	public String getGesturePsd() {
		return sp.getString("gesturePsd", "");
	}

	/**
	 * 保存密码状态
	 */
	public void setGestureState(String state) {
		editor.putString("state", state);
		editor.commit();
	}

	public String getGestureState() {
		return sp.getString("state", "");
	}
	public String getHasEasemob() {
		return sp.getString("HasEasemob", "");
	}

	/**
	 * 是否开通了环信
	 */
	public void setHasEasemob(String HasEasemob) {
		editor.putString("HasEasemob", HasEasemob);
		editor.commit();
	}
	public String getServerIp() {
		return sp.getString("serverIp", "LiWei");
	}
	/**
	 * 保存服务 port
	 */
	public void setServerIp(String serverIp) {
		editor.putString("serverIp", serverIp);
		editor.commit();
	}
	public int getServerPort() {
		return sp.getInt("serverPort",0);
	}
	/**
	 * 保存服务 ip port
	 */
	public void setServerPort(int serverPort) {
		editor.putInt("serverPort", serverPort);
		editor.commit();
	}
}

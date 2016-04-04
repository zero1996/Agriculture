package com.rjxy.agriculture;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;

import com.rjxy.agriculture.bean.ContorllerStatusValue;
import com.rjxy.agriculture.bean.SensorBean;
import com.rjxy.agriculture.bean.SensorConfig;
import com.rjxy.agriculture.bean.SensorValue;
import com.rjxy.agriculture.request.BaseRequest.REQUEST_RESULT;
import com.rjxy.agriculture.request.RequestThread;

public class ClientApp extends Application {
	private SharedPreferences mPref;
	// 传感器当前值
	private SensorValue mSensorValue;
	// 传感器阈值
	private SensorConfig mSensorConfig;
	private SensorBean mSensorBean;
	private Handler mHandler;
	public static int what = 0x12;

	private boolean isShowSplash;//是否显示欢迎界面，false不显示，true显示
	private boolean isInternational;//是否国际化，false代表中文，true代表英文
	private boolean isAutoControl;//是否自动控制，false手动，true自动
	
	ContorllerStatusValue mContorllerStatusValue;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mPref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		mSensorValue = new SensorValue();
		mSensorConfig = new SensorConfig();
		mSensorBean = new SensorBean();
		mContorllerStatusValue = new ContorllerStatusValue();
		mHandler = new Handler(new Handler.Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				if(msg.obj != null && msg.what == ClientApp.what) {
					REQUEST_RESULT result = REQUEST_RESULT.values()[msg.arg1];
					RequestThread thread = (RequestThread) msg.obj;
					if(thread != null && result == REQUEST_RESULT.SUCCESS){
						thread.hanlderResult(result);
					}	
					if (result.equals(REQUEST_RESULT.NOT_NET)) {
						
					}
				}
				return false;
			}

		});
	}

	public ContorllerStatusValue getContorllerStatusValue(){
		return this.mContorllerStatusValue;
	}
	
	public SensorBean getSensorBean() {
		return mSensorBean;
	}

	public SensorValue getSensorValue() {
		return this.mSensorValue;
	}

	public SensorConfig getSensorConfig() {
		return mSensorConfig;
	}

	/**
	 * 返回消息处理对象
	 * */
	public Handler getHandler() {
		return this.mHandler;
	}

	/**
	 * 设置不是第一次进入应用程序
	 */
	public void setFirstEnter() {
		Editor editor = mPref.edit();
		editor.putBoolean("isFirstEnter", false);
		editor.commit();
	}

	/**
	 * 获取用户是否第一次进入应用程序的状态
	 */
	public boolean getFirstEnter() {
		boolean isFirstEnter = mPref.getBoolean("isFirstEnter", true);
		return isFirstEnter;
	}

	/**
	 * 设置记住密码复选框的状态
	 */
	public void setCheckBoxCheck(boolean isChecked) {
		Editor editor = mPref.edit();
		editor.putBoolean("isChecked", isChecked);
		editor.commit();
	}

	/**
	 * 获取记住密码复选框的状态
	 */
	public boolean getCheckBoxCheck() {
		return mPref.getBoolean("isChecked", false);
	}

	/**
	 * 本地储存ip地址
	 * 
	 * @param ip
	 */
	public void setIp(String ip) {
		Editor editor = mPref.edit();
		editor.putString("ip", ip);
		editor.commit();
	}

	/**
	 * 读取本地用户名
	 * 
	 * @param username
	 */
	public String getIp() {
		String ip = mPref.getString("ip", "");
		return ip;
	}

	/**
	 * 本地存储用户名
	 * 
	 * @param username
	 */
	public void setUserName(String username) {
		Editor editor = mPref.edit();
		editor.putString("username", username);
		editor.commit();
	}

	/**
	 * 读取本地用户名
	 * 
	 * @param username
	 */
	public String getUserName() {
		String username = mPref.getString("username", "");
		return username;
	}

	/**
	 * 本地存储密码
	 * 
	 * @param username
	 */
	public void setPassword(String password) {
		Editor editor = mPref.edit();
		editor.putString("password", password);
		editor.commit();
	}

	/**
	 * 读取本地用户名
	 * 
	 * @param username
	 */
	public String getPassword() {
		String password = mPref.getString("password", "");
		return password;
	}
	
	
	public void setShowSplash(boolean isShowSplash) {
		this.isShowSplash = isShowSplash;
		Editor editor = mPref.edit();
		editor.putBoolean("isShowSplash", isShowSplash);
		editor.commit();
	}

	/**
	 * 是否国际化
	 * @return
	 */
	public boolean isInternational() {
		return isInternational;
	}

	/**
	 * 设置是否国际化
	 * @param isInternational
	 */
	public void setInternational(boolean isInternational) {
		this.isInternational = isInternational;
		Editor editor = mPref.edit();
		editor.putBoolean("isInternational", isInternational);
		editor.commit();
	}
	
	
	public boolean isShowSplash() {
		this.isShowSplash = mPref.getBoolean("isShowSplash", true); 
		return isShowSplash;
	}
	

	/**
	 * 是否自动控制
	 * @return
	 */
	public boolean isAutoControl() {
		this.isAutoControl = mPref.getBoolean("isAutoControl", false); 
		return isAutoControl;
	}

	/**
	 * 设置是否自动控制
	 * @param isAutoContol
	 */
	public void setAutoContol(boolean isAutoContol) {
		this.isAutoControl = isAutoContol;
		Editor editor = mPref.edit();
		editor.putBoolean("isAutoControl", isAutoControl);
		editor.commit();
	}

}

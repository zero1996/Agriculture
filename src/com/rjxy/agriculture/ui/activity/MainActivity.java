package com.rjxy.agriculture.ui.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.rjxy.agriculture.ClientApp;
import com.rjxy.agriculture.R;
import com.rjxy.agriculture.bean.SensorConfig;
import com.rjxy.agriculture.bean.SensorValue;
import com.rjxy.agriculture.db.SensorValueDB;
import com.rjxy.agriculture.request.BaseRequest;
import com.rjxy.agriculture.request.BaseRequest.REQUEST_RESULT;
import com.rjxy.agriculture.request.RequestThread;
import com.rjxy.agriculture.request.BaseRequest.OnResponseEventListener;
import com.rjxy.agriculture.request.SensorConfigRequest;
import com.rjxy.agriculture.request.SensorValueRequest;
import com.rjxy.agriculture.ui.fragment.ControlFragment;
import com.rjxy.agriculture.ui.fragment.CreativeFragment;
import com.rjxy.agriculture.ui.fragment.EnvirFragment;
import com.rjxy.agriculture.ui.fragment.HistoryFragment;
import com.rjxy.agriculture.ui.fragment.SettingFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private FragmentTabHost mTabHost;
	private Class<?>[] fragments;
	private List<String> mTabTags;
	private TextView titleTV;
	private List<String> mTabTitles;

	//数据存储类
	SensorValueDB mDB;
	ArrayList<Object> mSensorList;
	
	ClientApp mApp;
	//当前值请求类
	private SensorValueRequest mSensorValueRequest;
	//阈值请求类
	private SensorConfigRequest mSensorConfigRequest;
	//请求线程类
	private RequestThread SensorThread,ConfigThread;

	public interface OnUpDateListener {
		void upDate();
	}

	private OnUpDateListener mUpDate;

	public void setUpDate(OnUpDateListener upDate) {
		this.mUpDate = upDate;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		mApp = (ClientApp) MainActivity.this.getApplication();
		mDB = new SensorValueDB(MainActivity.this);
		mSensorList = new ArrayList<Object>();
		
		initView();
		initData();
		initTabHost();
		setRequest();
	}

	/**
	 * 初始化TabHost
	 */
	private void initTabHost() {
		mTabHost.setup(this, getSupportFragmentManager(), R.id.content);
		for (int i = 0; i < mTabTags.size(); i++) {
			TabSpec mTabSpec = mTabHost.newTabSpec(mTabTags.get(i))
					.setIndicator(setTabTitle(i));
			mTabHost.addTab(mTabSpec, fragments[i], null);
		}

		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals(mTabTags.get(0))) {
					titleTV.setText(mTabTitles.get(0));
				}
				if (tabId.equals(mTabTags.get(1))) {
					titleTV.setText(mTabTitles.get(1));
				}
				if (tabId.equals(mTabTags.get(2))) {
					titleTV.setText(mTabTitles.get(2));
				}
				if (tabId.equals(mTabTags.get(3))) {
					titleTV.setText(mTabTitles.get(3));
				}
			}
		});
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		mTabTags = Arrays.asList("环境指标", "历史数据查询", "手动控制", "系统设置", "创意");
		mTabTitles = Arrays.asList("实时数据", "历史数据查询", "手动控制界面", "系统设置", "创意");
		titleTV.setText(mTabTitles.get(0));
		fragments = new Class<?>[] { EnvirFragment.class,
				HistoryFragment.class, ControlFragment.class,
				SettingFragment.class, CreativeFragment.class };
	}

	/**
	 * 初始化界面
	 */
	private void initView() {
		titleTV = (TextView) findViewById(R.id.title);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
	}

	/**
	 * 设置Tab的标题
	 * @param i
	 * @return
	 */
	private View setTabTitle(int i) {
		TextView tv = new TextView(this);
		tv.setText(mTabTags.get(i));
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		return tv;
	}

	public void setRequest(){
		
		/**
		 * 传感器阈值获取
		 * */
		ConfigThread = new RequestThread(MainActivity.this, mApp.getHandler());
		mSensorConfigRequest = new SensorConfigRequest();
		mSensorConfigRequest.setSensorConfig(mApp.getSensorConfig());
		mSensorConfigRequest.setOnResponseEventListener(new OnResponseEventListener() {
			
			@Override
			public void onResponse(BaseRequest request, REQUEST_RESULT result) {
				// TODO Auto-generated method stub
				System.out.println("----阈值获取成功----------");
				SensorConfig config = mApp.getSensorConfig();
				System.out.println("+++++++++++++++++++++++"+config.getMaxAirHumidity());
				System.out.println("+++++++++++++++++++++++"+config.getMinAirHumidity());
//				ConfigThread.interrupt();
			}
		});
		
		ConfigThread.setBaseRequest(mSensorConfigRequest);
		ConfigThread.start();
		
		
		/**
		 * 传感器当前值获取线程
		 * */
		mSensorValueRequest = new SensorValueRequest();
		mSensorValueRequest.setSensorValue(mApp.getSensorValue());
		mSensorValueRequest.setOnResponseEventListener(new OnResponseEventListener() {

			@Override
			public void onResponse(BaseRequest request,REQUEST_RESULT result) {
				// TODO Auto-generated method stub
				if(mSensorValueRequest.isSuccess){
					SensorValue value = mApp.getSensorValue();
					mSensorList.add(value);
					if(mSensorList.size()>10){
						saveDate();
						mSensorList.clear();
					}
					mUpDate.upDate();
				}
			}
		});
		SensorThread = new RequestThread(MainActivity.this, mApp.getHandler());
		SensorThread.setLoop(true, 3000);
		SensorThread.setBaseRequest(mSensorValueRequest);
		SensorThread.start();
	}

	public void saveDate(){
		ArrayList<Object> mlist = new ArrayList<Object>();
		mlist.addAll(mSensorList);
		mDB.saveDataToDB(mlist, null);
	}

}

package com.rjxy.agriculture.ui.activity;

import java.util.ArrayList;


import com.rjxy.agriculture.R;
import com.rjxy.agriculture.adapter.SensorRealAdapter;
import com.rjxy.agriculture.bean.ChartPagerBean;
import com.rjxy.agriculture.bean.SensorValue;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RealTimeData extends AppBaseActivity implements OnPageChangeListener{
	
	TextView curTitle;
	LinearLayout linearlayout;
	ViewPager mViewPager;
	SensorRealAdapter adapter;

	ArrayList<ChartPagerBean> mList;
	ChartPagerBean co2;
	ChartPagerBean light;
	ChartPagerBean airH;
	ChartPagerBean airT;
	ChartPagerBean soilH;
	ChartPagerBean soilT;

	SensorValue mSensorValue;
	
	public static final int CO2_CHART = 0x00;
	public static final int LIGHT_CHART = 0x01;
	public static final int AIR_T_CHART = 0x02;
	public static final int AIR_H_CHART = 0x03;
	public static final int SOIL_T_CHART = 0x04;
	public static final int SOIL_H_CHART = 0x05;

	int CurType=CO2_CHART;
	int cur;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.real_viewpager);
		initData();
		super.startGetSensorData();
		cur = getIntent().getIntExtra("type", CO2_CHART);
		indicator();
		initView();
		switch(cur){
		case CO2_CHART:
			curTitle.setText("Co2实时数据");
			break;
		case LIGHT_CHART:
			curTitle.setText("光照实时数据");
			break;
		case AIR_T_CHART:
			curTitle.setText("空气温度实时数据");
			break;
		case AIR_H_CHART:
			curTitle.setText("空气湿度实时数据");
			break;
		case SOIL_T_CHART:
			curTitle.setText("土壤温度实时数据");
			break;
		case SOIL_H_CHART:
			curTitle.setText("土壤湿度实时数据");
			break;
		}
		mViewPager.setCurrentItem(cur,true);//设置当前页面
		updataView();
	}


	
	/**
	 * 页面刷新数据
	 * 
	 * */
	private void updataView() {
		
		SensorValue value = mApp.getSensorValue();
		if(value!=null){
			// C02浓度
			if (co2.majorValue.size() >8) {
				co2.majorValue.removeFirst();
			}
			co2.majorValue.add(value.getCo2());
			co2.majorValueMin = 0;// co2的最小值
			co2.majorValueMax = 1000;// co2的最大值

			// 光照强度
			if (light.majorValue.size() >8) {
				light.majorValue.removeFirst();
			}
			System.out.println("----------------size---------"+light.majorValue.size());
			light.majorValue.add(value.getLight());
			light.majorValueMin = 0;// 光照强度最小值
			light.majorValueMax = 15000;// 光照强度最大值

			// 空气温度
			if (airT.majorValue.size() >8) {
				airT.majorValue.removeFirst();
			}
			airT.majorValue.add(value.getAirTemperature());
			airT.majorValueMin = 0;// 空气温度最小值
			airT.majorValueMax = 100;// 空气温度最大值

			// 空气湿度
			if (airH.majorValue.size() > 8) {
				airH.majorValue.removeFirst();
			}
			airH.majorValue.add(value.getAirHumidity());
			airH.majorValueMin = 0;// 空气湿度最小值
			airH.majorValueMax = 100;// 空气湿度最大值

			// 土壤湿度
			if (soilH.majorValue.size() >8) {
				soilH.majorValue.removeFirst();
			}
			soilH.majorValue.add(value.getSoilTemperature());
			soilH.majorValueMin = 0;// 土壤温度最小值
			soilH.majorValueMax = 100;// 土壤温度最大值

			// 土壤温度
			if (soilT.majorValue.size() >7) {
				soilT.majorValue.removeFirst();
			}
			soilT.majorValue.add(value.getSoilHumidity());
			soilT.majorValueMin = 0;// 土壤湿度最小值
			soilT.majorValueMax = 100;// 土壤湿度最大值

			adapter.notifyDataSetChanged();
		
		}
	}

	public void indicator(){
		linearlayout = (LinearLayout) findViewById(R.id.lin);
		for(int i=0;i<mList.size();i++){
			if(i == cur){
				ImageView image = new ImageView(RealTimeData.this);
				image.setImageResource(R.drawable.up);
				linearlayout.addView(image, i);
			}else{
				ImageView image = new ImageView(RealTimeData.this);
				image.setImageResource(R.drawable.down);
				linearlayout.addView(image, i);
			}
		}
	}

	/**
	 * 初始化控件
	 * */
	public void initView(){
		curTitle = (TextView) findViewById(R.id.cur_type);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(this);
	}

	/**
	 * 
	 * 初始化图表数据
	 * */
	public void initData(){
		mList = new ArrayList<ChartPagerBean>();
		co2 = new ChartPagerBean("co2");
		light = new ChartPagerBean("光照强度");
		airT = new ChartPagerBean("空气温度");
		airH = new ChartPagerBean("空气湿度");
		soilT = new ChartPagerBean("土壤温度");
		soilH = new ChartPagerBean("土壤湿度");

		mList.add(co2);
		mList.add(light);
		mList.add(airT);
		mList.add(airH);
		mList.add(soilT);
		mList.add(soilH);
		adapter = new SensorRealAdapter(RealTimeData.this, mList);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int position) {
		for(int i=0;i<mList.size();i++){
			ImageView view = (ImageView) linearlayout.getChildAt(i); 
			if(i == position){
				view.setImageResource(R.drawable.up);
			}else{
				view.setImageResource(R.drawable.down);
			}
		}
		switch(position){
		case CO2_CHART:
			curTitle.setText("Co2实时数据");
			break;
		case LIGHT_CHART:
			curTitle.setText("光照实时数据");
			break;
		case AIR_T_CHART:
			curTitle.setText("空气温度实时数据");
			break;
		case AIR_H_CHART:
			curTitle.setText("空气湿度实时数据");
			break;
		case SOIL_T_CHART:
			curTitle.setText("土壤温度实时数据");
			break;
		case SOIL_H_CHART:
			curTitle.setText("土壤湿度实时数据");
			break;
		}
	}
	
	@Override
	protected void onGetSensor() {
		// TODO Auto-generated method stub
		super.onGetSensor();
		System.out.println("------------界面刷新--------------");
		updataView();
	}
	
}

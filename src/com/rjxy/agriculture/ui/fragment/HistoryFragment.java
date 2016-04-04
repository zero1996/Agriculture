package com.rjxy.agriculture.ui.fragment;

import java.util.ArrayList;

import com.rjxy.agriculture.R;
import com.rjxy.agriculture.bean.ChartPagerBean;
import com.rjxy.agriculture.bean.SensorValue;
import com.rjxy.agriculture.db.BaseDB.OnDataBaseListener;
import com.rjxy.agriculture.db.SensorValueDB;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class HistoryFragment extends Fragment {

	SensorValueDB mDB ;
	Button query;
	Spinner sensorType;
	String type = "CO2浓度";
	LinearLayout charShow;
	String[] array;
	ChartPagerBean mChartPagerBean;
	ChartView chartView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		array = getResources().getStringArray(R.array.spinner);
		mDB = new SensorValueDB(getActivity());
		chartView = new ChartView();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_hostory, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		setListener();
	}
	
	/**
	 * 设置监听
	 * */
	public void setListener(){
		sensorType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				type = array[position];
				System.out.println(type);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDB.loadDataByTime("", new OnDataBaseListener() {
					
					@Override
					public void OnDatabase(ArrayList<Object> list, boolean isSuccess) {
						if(isSuccess){
							System.out.println("---------成功-------");
							if(type.equals("CO2浓度")){
								mChartPagerBean = new ChartPagerBean("CO2浓度");
								mChartPagerBean.majorValueMax=100;
								mChartPagerBean.majorValueMin=0;
								mChartPagerBean.majorValue.clear();
							}
							else if(type.equals("光照强度")){
								mChartPagerBean = new ChartPagerBean("光照强度");
								mChartPagerBean.majorValueMax=1000;
								mChartPagerBean.majorValueMin=0;
								mChartPagerBean.majorValue.clear();
							}
							else if(type.equals("空气温度")){
								mChartPagerBean = new ChartPagerBean("空气温度");
								mChartPagerBean.majorValueMax=100;
								mChartPagerBean.majorValueMin=0;
								mChartPagerBean.majorValue.clear();
							}
							else if(type.equals("空气湿度")){
								mChartPagerBean = new ChartPagerBean("空气湿度");
								mChartPagerBean.majorValueMax=100;
								mChartPagerBean.majorValueMin=0;
								mChartPagerBean.majorValue.clear();
							}
							else if(type.equals("土壤温度")){
								mChartPagerBean = new ChartPagerBean("土壤温度");
								mChartPagerBean.majorValueMax=100;
								mChartPagerBean.majorValueMin=0;
								mChartPagerBean.majorValue.clear();
							}
							else if(type.equals("土壤湿度")){
								mChartPagerBean = new ChartPagerBean("土壤湿度");
								mChartPagerBean.majorValueMax=100;
								mChartPagerBean.majorValueMin=0;
								mChartPagerBean.majorValue.clear();
							}
							for(int i=0;i<9;i++){
								SensorValue value = (SensorValue) list.get(i);
								if(type.equals("CO2浓度")){
									mChartPagerBean.majorValue.add(value.getCo2());
								}
								else if(type.equals("光照强度")){
									mChartPagerBean.majorValue.add(value.getCo2());
								}
								else if(type.equals("空气温度")){
									mChartPagerBean.majorValue.add(value.getAirTemperature());
								}
								else if(type.equals("空气湿度")){
									mChartPagerBean.majorValue.add(value.getAirHumidity());
								}
								else if(type.equals("土壤温度")){
									mChartPagerBean.majorValue.add(value.getSoilTemperature());
								}
								else if(type.equals("土壤湿度")){
									mChartPagerBean.majorValue.add(value.getSoilHumidity());
								}
							}
							chartView.chartView(getActivity(), mChartPagerBean, charShow);
						}
					}
				});
			}
		});
	}
	
	/**
	 * 初始界面
	 * */
	public void initView(){
		query = (Button) getView().findViewById(R.id.history_query);
		sensorType = (Spinner) getView().findViewById(R.id.history_spinner);
		charShow = (LinearLayout) getView().findViewById(R.id.history_content);
	}
}

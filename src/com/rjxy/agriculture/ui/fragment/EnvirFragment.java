package com.rjxy.agriculture.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import com.rjxy.agriculture.ClientApp;
import com.rjxy.agriculture.R;
import com.rjxy.agriculture.adapter.EnvirGridAdapter;
import com.rjxy.agriculture.bean.SensorBean;
import com.rjxy.agriculture.bean.SensorConfig;
import com.rjxy.agriculture.bean.SensorValue;
import com.rjxy.agriculture.ui.activity.MainActivity;
import com.rjxy.agriculture.ui.activity.MainActivity.OnUpDateListener;
import com.rjxy.agriculture.ui.activity.RealTimeData;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class EnvirFragment extends Fragment {
	private GridView mGridView;
	private EnvirGridAdapter mAdapter;
	private ClientApp mApp;
	
	private List<SensorBean> mDataList;
	SensorBean Co2,Light,at,ah,st,sh;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApp = (ClientApp) getActivity().getApplication();
		mDataList = new ArrayList<SensorBean>();
		initData();
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_envir, container,false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mGridView = (GridView) getView().findViewById(R.id.envir_gird_view);
		mAdapter = new EnvirGridAdapter(getActivity(), mDataList);
		mGridView.setAdapter(mAdapter);
		updataView();
		MainActivity activity = (MainActivity) getActivity();
		activity.setUpDate(new OnUpDateListener() {
			
			@Override
			public void upDate() {
				updataView();
			}
		});
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int CurType = RealTimeData.CO2_CHART;
				if(mDataList.get(position) == Co2){
					CurType = RealTimeData.CO2_CHART;
					System.out.println("点击Co2");
				}
				if(mDataList.get(position) == Light){
					CurType = RealTimeData.LIGHT_CHART;
					System.out.println("点击光照");
				}
				if(mDataList.get(position) == at){
					CurType = RealTimeData.AIR_T_CHART;
					System.out.println("点击空气温度");
				}
				if(mDataList.get(position) == ah){
					CurType = RealTimeData.AIR_H_CHART;
					System.out.println("点击空气湿度");
				}
				if(mDataList.get(position) == st){
					CurType = RealTimeData.SOIL_T_CHART;
					System.out.println("点击土壤温度");
				}
				if(mDataList.get(position) == sh){
					CurType = RealTimeData.SOIL_H_CHART;
					System.out.println("点击土壤湿度");
				}
				System.out.println("------------------------------"+CurType);
				Log.e("name", CurType+"");
				Intent intent = new Intent(getActivity(),RealTimeData.class);
				intent.putExtra("type", CurType);
				startActivity(intent);
			}
	
		});
	}
	
	public void initData(){
		
		Co2 = new SensorBean();
		Co2.setMaxValue(50);
		Co2.setMinValue(0);
		Co2.setName("co2");
		Co2.setValue(30);
		
		Light = new SensorBean();
		Light.setMaxValue(10000);
		Light.setMinValue(0);
		Light.setName("light");
		Light.setValue(5000);
		
		at = new SensorBean();
		at.setMaxValue(50);
		at.setMinValue(0);
		at.setName("at");
		at.setValue(30);
		
		//空气湿度
		ah = new SensorBean();
		ah.setMaxValue(100);
		ah.setMinValue(0);
		ah.setName("ah");
		ah.setValue(70);
		//土壤是湿度
		sh = new SensorBean();
		sh.setMaxValue(100);
		sh.setMinValue(0);
		sh.setName("sh");
		sh.setValue(50);
		
		st = new SensorBean();
		st.setMaxValue(100);
		st.setMinValue(0);
		st.setName("sh");
		st.setValue(50);
		
		mDataList.add(at);
		mDataList.add(ah);
		mDataList.add(Light);
		mDataList.add(st);
		mDataList.add(sh);
		mDataList.add(Co2);
	}

	public void updataView(){
		mDataList.clear();
		SensorValue value = mApp.getSensorValue();
		SensorConfig config = mApp.getSensorConfig();
		
		Co2 = new SensorBean();
		Co2.setMaxValue(config.getMaxCo2());
		Co2.setMinValue(config.getMinCo2());
		Co2.setName("co2");
		Co2.setValue(value.getCo2());
		
		Light = new SensorBean();
		Light.setMaxValue(config.getMaxLight());
		Light.setMinValue(config.getMinLight());
		Light.setName("光照");
		Light.setValue(value.getLight());
		
		at = new SensorBean();
		at.setMaxValue(config.getMaxAirTemperature());
		at.setMinValue(config.getMinAirTemperature());
		at.setName("空气温度");
		at.setValue(value.getAirTemperature());
		
////		//空气湿度
		ah = new SensorBean();
		ah.setMaxValue(config.getMaxAirHumidity());
		ah.setMinValue(config.getMinAirHumidity());
		ah.setName("空气湿度");
		ah.setValue(value.getAirHumidity());
////		//土壤是湿度
		sh = new SensorBean();
		sh.setMaxValue(config.getMaxSoilHumidity());
		sh.setMinValue(config.getMinSoilHumidity());
		sh.setName("土壤湿度");
		sh.setValue(value.getSoilHumidity());
////		
		st = new SensorBean();
		st.setMaxValue(config.getMaxSoilTemperature());
		st.setMinValue(config.getMinSoilTemperature());
		st.setName("土壤温度");
		st.setValue(value.getSoilTemperature());
////		
		mDataList.add(at);
		mDataList.add(ah);
		mDataList.add(Light);
		mDataList.add(st);
		mDataList.add(sh);
		mDataList.add(Co2);
//		
		mAdapter.notifyDataSetChanged();
		
//		System.out.println("++++++++++"+value.getCo2());
//		System.out.println("++++++++++"+value.getLight());
//		System.out.println("++++++++++"+value.getAirHumidity());
//		System.out.println("++++++++++"+value.getAirTemperature());
//		System.out.println("++++++++++"+value.getSoilHumidity());
//		System.out.println("++++++++++"+value.getSoilTemperature());
	}
}

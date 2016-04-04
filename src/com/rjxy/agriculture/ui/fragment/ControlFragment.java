package com.rjxy.agriculture.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.rjxy.agriculture.ClientApp;
import com.rjxy.agriculture.R;
import com.rjxy.agriculture.adapter.ControlGridAdapter;
import com.rjxy.agriculture.bean.ContorllerStatusValue;
import com.rjxy.agriculture.bean.SetSensorControl;
import com.rjxy.agriculture.request.BaseRequest;
import com.rjxy.agriculture.request.BaseRequest.OnResponseEventListener;
import com.rjxy.agriculture.request.BaseRequest.REQUEST_RESULT;
import com.rjxy.agriculture.request.ContorllerStatusValueRequest;
import com.rjxy.agriculture.request.RequestThread;
import com.rjxy.agriculture.request.SetControlRequest;

public class ControlFragment extends Fragment {
	private GridView mGridView;
	private ControlGridAdapter mAdapter;
	private List<Integer> mStatusList;
	private ClientApp mApp;
	private ContorllerStatusValue corntorllerValue ;
	private SetSensorControl setSensorControl;
	private  SetControlRequest setControlRequest;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mStatusList = new ArrayList<Integer>();
		mApp = (ClientApp) getActivity().getApplication();
		corntorllerValue = mApp.getContorllerStatusValue();	 
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_control, null);
		mGridView = (GridView) view.findViewById(R.id.control_grid_view);
		mAdapter = new ControlGridAdapter(getActivity(), mStatusList);
		mGridView.setAdapter(mAdapter);
		getContollerStatusValues();
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAdapter.notifyDataSetChanged();
		mGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//发起网络请求获取当前控制器状态
				getContollerStatusValues();
		        //设置各个点击事件
				switch (position) {
				case 0:
					corntorllerValue = mApp.getContorllerStatusValue();
					int value = corntorllerValue.getBlower();
					//根据当前值取反
					if(value == 0) {
						value = 1;
					}else{
						value = 0;
					}
			        setSensorControl = new SetSensorControl();
			        setSensorControl.setBlower(value);
			        setControlRequest = new SetControlRequest();
			        RequestThread setControlThread = new RequestThread(getActivity(),  mApp.getHandler());
			        setControlRequest.setControl(setSensorControl);
			        setControlThread.setBaseRequest(setControlRequest);
			        setControlRequest.setOnResponseEventListener(new OnResponseEventListener() {
						
						@Override
						public void onResponse(BaseRequest request, REQUEST_RESULT result) {
							// TODO Auto-generated method stub
							if(setControlRequest.isSuccess == true){
								getActivity().runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_LONG).show();
										//更新界面
										try {
											Thread.sleep(1000);
											getContollerStatusValues();
											mAdapter.notifyDataSetChanged();
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}									
									}
								});
							}else {
								getActivity().runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(getActivity(), "设置失败", Toast.LENGTH_LONG).show();
									}
								});
							}	
						}
					});
			        setControlThread.start();
					break;
				case 1:
					corntorllerValue = mApp.getContorllerStatusValue();
					value = corntorllerValue.getRoadlamp();
					//根据当前值取反
					if(value == 0) {
						value = 1;
					}else{
						value = 0;
					}
			        setSensorControl = new SetSensorControl();
			        setSensorControl.setRoadlamp(value);
			        setControlRequest = new SetControlRequest();
			        setControlThread = new RequestThread(getActivity(),  mApp.getHandler());
			        setControlRequest.setControl(setSensorControl);
			        setControlThread.setBaseRequest(setControlRequest);
			        setControlRequest.setOnResponseEventListener(new OnResponseEventListener() {
						
						@Override
						public void onResponse(BaseRequest request, REQUEST_RESULT result) {
							// TODO Auto-generated method stub
							if(setControlRequest.isSuccess == true){
								getActivity().runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_LONG).show();
										//更新界面
										try {
											Thread.sleep(1000);
											getContollerStatusValues();
											mAdapter.notifyDataSetChanged();
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}									
									}
								});
							}else {
								getActivity().runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(getActivity(), "设置失败", Toast.LENGTH_LONG).show();
									}
								});
							}	
						}
					});
			        setControlThread.start();
					break;
				case 2:
					corntorllerValue = mApp.getContorllerStatusValue();
					value = corntorllerValue.getWaterPump();
					//根据当前值取反
					if(value == 0) {
						value = 1;
					}else{
						value = 0;
					}
			        setSensorControl = new SetSensorControl();
			        setSensorControl.setWaterPump(value);
			        setControlRequest = new SetControlRequest();
			        setControlThread = new RequestThread(getActivity(),  mApp.getHandler());
			        setControlRequest.setControl(setSensorControl);
			        setControlThread.setBaseRequest(setControlRequest);
			        setControlRequest.setOnResponseEventListener(new OnResponseEventListener() {
						
						@Override
						public void onResponse(BaseRequest request, REQUEST_RESULT result) {
							// TODO Auto-generated method stub
							if(setControlRequest.isSuccess == true){
								getActivity().runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_LONG).show();
										//更新界面
										try {
											Thread.sleep(1000);
											getContollerStatusValues();
											mAdapter.notifyDataSetChanged();
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}									
									}
								});
							}else {
								getActivity().runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(getActivity(), "设置失败", Toast.LENGTH_LONG).show();
									}
								});
							}	
						}
					});
			        setControlThread.start();
					break;
				case 3:
					corntorllerValue = mApp.getContorllerStatusValue();
					value = corntorllerValue.getBuzzer();
					//根据当前值取反
					if(value == 0) {
						value = 1;
					}else{
						value = 0;
					}
			        setSensorControl = new SetSensorControl();
			        setSensorControl.setBuzzer(value);
			        setControlRequest = new SetControlRequest();
			        setControlThread = new RequestThread(getActivity(),  mApp.getHandler());
			        setControlRequest.setControl(setSensorControl);
			        setControlThread.setBaseRequest(setControlRequest);
			        setControlRequest.setOnResponseEventListener(new OnResponseEventListener() {
						
						@Override
						public void onResponse(BaseRequest request, REQUEST_RESULT result) {
							// TODO Auto-generated method stub
							if(setControlRequest.isSuccess == true){
								getActivity().runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_LONG).show();
										//更新界面
										try {
											Thread.sleep(1000);
											getContollerStatusValues();
											mAdapter.notifyDataSetChanged();
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}									
									}
								});
							}else {
								getActivity().runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(getActivity(), "设置失败", Toast.LENGTH_LONG).show();
									}
								});
							}	
						}
					});
			        setControlThread.start();
					break;

				default:
					break;
				}
				
			}
		});
	}
	
	/**
	 * 获取当前控制器状态并更新界面
	 * 
	 * 
	 * **/
	
	public void getContollerStatusValues() {
		ContorllerStatusValueRequest corntorllerRequest = new ContorllerStatusValueRequest();
        corntorllerRequest.setClientApp(mApp);
        RequestThread corntorllerthread = new RequestThread(getActivity(),  mApp.getHandler());//线程类
        corntorllerthread.setBaseRequest(corntorllerRequest);
        corntorllerRequest.setOnResponseEventListener(new OnResponseEventListener() {
			
			@Override
			public void onResponse(BaseRequest request, REQUEST_RESULT result) {
				// TODO Auto-generated method stub
				//设置数据集合
				mStatusList.clear();
				mStatusList.add(corntorllerValue.getBlower());
				mStatusList.add(corntorllerValue.getRoadlamp());
				mStatusList.add(corntorllerValue.getWaterPump());
				mStatusList.add(corntorllerValue.getBuzzer());
				for(int i=0; i<mStatusList.size(); i++) {
					 int z = mStatusList.get(i);
					 Log.e("name", z+"");
					 
				}
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						mAdapter.notifyDataSetChanged();
					}
				});
			}
		});
        corntorllerthread.start();
	}
	
}

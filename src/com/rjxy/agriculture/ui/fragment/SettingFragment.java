package com.rjxy.agriculture.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.rjxy.agriculture.ClientApp;
import com.rjxy.agriculture.R;
import com.rjxy.agriculture.bean.SetSensorConfig;
import com.rjxy.agriculture.dialog.SetAirDialog;
import com.rjxy.agriculture.dialog.SetCo2Dialog;
import com.rjxy.agriculture.dialog.SetLightDialog;
import com.rjxy.agriculture.dialog.SetSoilDialog;
import com.rjxy.agriculture.request.BaseRequest;
import com.rjxy.agriculture.request.BaseRequest.OnResponseEventListener;
import com.rjxy.agriculture.request.BaseRequest.REQUEST_RESULT;
import com.rjxy.agriculture.request.RequestThread;
import com.rjxy.agriculture.request.SetSensorConfigRequest;

public class SettingFragment extends Fragment {
	private ListView mListView;
	private SimpleAdapter mAdapter;
	private List<Map<String, String>> mDataList;
	private ClientApp mApp;
	private String[] mListItemArray;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
		updateView();

	}

	private void initData() {
		mApp = (ClientApp) getActivity().getApplicationContext();
		mDataList = new ArrayList<Map<String, String>>();
		mListItemArray = new String[] { "是否显示导航界面", "语言", "控制方式", "空气温湿度阈值设置",
				"土壤温湿度阈值设置", "光照强度阈值设置", "CO2浓度阈值设置" };
		mAdapter = new SimpleAdapter(
				getActivity(),
				mDataList,
				R.layout.item_setting,
				new String[] { "name", "indicator" },
				new int[] { R.id.setting_item_name, R.id.setting_item_indicator });
	}

	/**
	 * 更新视图
	 */
	private void updateView() {
		mDataList.clear();
		for (int i = 0; i < mListItemArray.length; i++) {
			if (i == 0) {
				if (mApp.isShowSplash()) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", mListItemArray[i]);
					map.put("indicator", "是");
					mDataList.add(map);
				} else {
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", mListItemArray[i]);
					map.put("indicator", "否");
					mDataList.add(map);
				}
			} else if (i == 1) {
				if (mApp.isInternational()) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", mListItemArray[i]);
					map.put("indicator", "英文");
					mDataList.add(map);
				} else {
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", mListItemArray[i]);
					map.put("indicator", "中文");
					mDataList.add(map);
				}
			} else if (i == 2) {
				if (mApp.isAutoControl()) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", mListItemArray[i]);
					map.put("indicator", "自动");
					mDataList.add(map);
				} else {
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", mListItemArray[i]);
					map.put("indicator", "手动");
					mDataList.add(map);
				}
			} 
			else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", mListItemArray[i]);
				map.put("indicator", "→");
				mDataList.add(map);
			}
		}
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setting, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mListView = (ListView) getView().findViewById(R.id.setting_list_view);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					mApp.setShowSplash(!mApp.isShowSplash());
					updateView();
					break;
				case 1:
					
					break;
				case 2:
					boolean isAuto = mApp.isAutoControl();//当前的状态
					if(isAuto){
						setAutoControl(0);
					}else{
						setAutoControl(1);
					}
					break;
				case 3:
					new SetAirDialog(getActivity()).show();
					break;
				case 4:
					new SetSoilDialog(getActivity()).show();
					break;
				case 5:
					new SetLightDialog(getActivity()).show();
					break;
				case 6:
					new SetCo2Dialog(getActivity()).show();
					break;

				default:
					break;
				}
			}
		});
	}
	/**
	 * 
	 * 设置是否自动控制
	 * **/
	private void setAutoControl(final int auto) {
      	SetSensorConfig config = new SetSensorConfig();
    	config.setControlAuto(auto);
    	final SetSensorConfigRequest request1 = new SetSensorConfigRequest();
    	request1.setSensorConfig(config);
    	RequestThread thread = new RequestThread(getActivity(),  mApp.getHandler());
    	thread.setBaseRequest(request1);
    	request1.setOnResponseEventListener(new  OnResponseEventListener() {
			
			@Override
			public void onResponse(BaseRequest request, REQUEST_RESULT result) {
				// TODO Auto-generated method stub
				if(request1.isSuccess){
					getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							if(auto == 1){
								mApp.setAutoContol(true);
							}else {
								mApp.setAutoContol(false);
							}
							Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT).show();
							updateView();
						}
					});
				}
			}
		});
    	thread.start();
		
	}
}

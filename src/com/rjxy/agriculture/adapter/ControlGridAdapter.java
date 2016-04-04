package com.rjxy.agriculture.adapter;

import java.util.Arrays;
import java.util.List;

import com.rjxy.agriculture.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

public class ControlGridAdapter extends ArrayAdapter<Integer> {
	private Context mContext;
	private List<Integer> mDataList;
	private List<String> mSensorNames;

	public ControlGridAdapter(Context context, List<Integer> objects) {
		super(context, 0,objects);
		this.mContext = context;
		this.mDataList = objects;
		mSensorNames = Arrays.asList("风扇","荧光灯","水泵","蜂鸣报警器");
	}
	
	@Override
	public Integer getItem(int position) {
		return mDataList.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int  isOpen = getItem(position);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_control, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.control_name);
			viewHolder.state = (TextView) convertView
					.findViewById(R.id.control_state);
			viewHolder.switchBtn = (Switch) convertView
					.findViewById(R.id.control_switch);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.name.setText(mSensorNames.get(position));
		if( isOpen == 0){
			viewHolder.state.setText("关闭");
			viewHolder.switchBtn.setChecked(false);
		}else{
			viewHolder.state.setText("打开");
			viewHolder.switchBtn.setChecked(true);
		}
		return convertView;
	}

	class ViewHolder {
		TextView name;
		TextView state;
		Switch switchBtn;
	}


}

package com.rjxy.agriculture.adapter;

import java.util.List;

import com.rjxy.agriculture.R;
import com.rjxy.agriculture.bean.SensorBean;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EnvirGridAdapter extends ArrayAdapter<SensorBean> {
		private List<SensorBean> mDataList;
		private Context mContext;
	
		public EnvirGridAdapter(Context context, List<SensorBean> objects) {
			super(context, 0, objects);
			this.mDataList = objects;
			this.mContext = context;
		}
		
		@Override
		public int getCount() {
			return mDataList.size();
		}
		
		@Override
		public SensorBean getItem(int position) {
			return mDataList.get(position);
		}
	
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			SensorBean mData = (SensorBean)mDataList.get(position);
			ViewHolder viewHolder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_envir, null);
				viewHolder = new ViewHolder();
				viewHolder.bg = (RelativeLayout) convertView
						.findViewById(R.id.envir_bg);
				viewHolder.name = (TextView) convertView
						.findViewById(R.id.envir_name);
				viewHolder.state = (TextView) convertView
						.findViewById(R.id.envir_state);
				viewHolder.value = (TextView) convertView
						.findViewById(R.id.envir_value);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			String name = mData.getName();
			int value = mData.getValue();
			int max = mData.getMaxValue();
			int min = mData.getMinValue();
			viewHolder.name.setText(name);
			viewHolder.value.setText(value+"");
			if (value < min
					|| value > max) {
				viewHolder.bg.setBackgroundColor(Color.RED);
				viewHolder.state.setText("告警");
			}else{
				viewHolder.bg.setBackgroundColor(Color.GREEN);
				viewHolder.state.setText("正常");
			}
			return convertView;
		}
	
		class ViewHolder {
			RelativeLayout bg;
			TextView name;
			TextView state;
			TextView value;
		}

}

package com.rjxy.agriculture.adapter;

import java.util.ArrayList;
import java.util.List;

import com.rjxy.agriculture.R;
import com.rjxy.agriculture.bean.ChartPagerBean;
import com.rjxy.agriculture.ui.fragment.ChartView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SensorRealAdapter extends PagerAdapter{

	Context mContext ;
	ArrayList<ChartPagerBean> mList;
	List<View> viewList ;
	LayoutInflater mInflater;
	
	public SensorRealAdapter(Context context,ArrayList<ChartPagerBean> list){
		this.mContext = context;
		this.mList = list;
		this.mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		viewList = new ArrayList<View>();
		for(int i=0;i<mList.size();i++){
			View view  = mInflater.inflate(R.layout.real, null);
			viewList.add(view);
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}
	
	

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (View) arg1;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(viewList.get(position));
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		new ChartView().chartView(mContext, mList.get(position), (LinearLayout) viewList.get(position).findViewById(R.id.layout));
		((ViewPager) container).addView(viewList.get(position));
		return viewList.get(position);
	}
	
	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}
}

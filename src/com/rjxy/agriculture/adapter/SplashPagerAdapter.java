package com.rjxy.agriculture.adapter;

import java.util.List;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SplashPagerAdapter extends PagerAdapter {
	private Context mContext;
	private List<LinearLayout> mDataList;

	public SplashPagerAdapter(Context context, List<LinearLayout> dataList) {
		this.mContext = context;
		this.mDataList = dataList;
	}

	@Override
	public int getCount() {
		return mDataList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LinearLayout view = (LinearLayout) mDataList.get(position);
		container.addView(view);
		return view;
	}

}

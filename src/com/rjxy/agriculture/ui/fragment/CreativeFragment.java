package com.rjxy.agriculture.ui.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rjxy.agriculture.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class CreativeFragment extends Fragment {
	private LinearLayout mTabLayout;
	private ViewPager mViewPager;
	private MyViewPagerAdapter mAdapter;
	private List<View> mDataList;
	private ImageView mTabline;
	private int mCurrentView;
	private int mTablineWidth;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_creative, null);
		mViewPager = (ViewPager) view.findViewById(R.id.creative_view_pager);
		mTabLayout = (LinearLayout) view.findViewById(R.id.creative_tab);
		mTabline = (ImageView) view.findViewById(R.id.creative_tabline);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
		mViewPager.setAdapter(mAdapter);
		setListener();
	}


	/**
	 * 初始化数据
	 */
	private void initData() {
		mDataList = new ArrayList<View>();
		for (int i = 0; i < mTabLayout.getChildCount(); i++) {
			LinearLayout view = (LinearLayout) LayoutInflater.from(
					getActivity()).inflate(R.layout.item_creative, null);
			TextView tv1 = new TextView(getActivity());
			tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
			tv1.setText("最大值:" + new Random().nextInt(1024));
			TextView tv2 = new TextView(getActivity());
			tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
			tv2.setText("最小值:" + new Random().nextInt(356));
			view.addView(tv1);
			view.addView(tv2);
			mDataList.add(view);
		}
		mAdapter = new MyViewPagerAdapter(mDataList);
		//设置指示线宽度
		mTablineWidth = getScreenWidth() / mTabLayout.getChildCount();
		mTabline.getLayoutParams().width = mTablineWidth;
	}
	
	/**
	 * 设置监听
	 */
	private void setListener() {
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				mCurrentView = position;
				for (int i = 0; i < mTabLayout.getChildCount(); i++) {
					LinearLayout layout = (LinearLayout) mTabLayout
							.getChildAt(i);
					TextView tv = (TextView) layout.getChildAt(0);
					tv.setTextColor(Color.BLACK);
				}
				LinearLayout layout = (LinearLayout) mTabLayout
						.getChildAt(position);
				TextView tv = (TextView) layout.getChildAt(0);
				tv.setTextColor(Color.parseColor("#ff8800"));
			}

			@Override
			public void onPageScrolled(int position, float offset, int arg2) {
				LinearLayout.LayoutParams params = (LayoutParams) mTabline
						.getLayoutParams();
				if (position == 0 && mCurrentView == 0) {//0-1
					params.leftMargin = (int) (mCurrentView * mTablineWidth + offset
							* mTablineWidth);
				} else if (position == 1 && mCurrentView == 1) {//1-2
					params.leftMargin = (int) (mCurrentView * mTablineWidth +offset
							* mTablineWidth);
				} else if (position == 2 && mCurrentView == 2) {//2-3
					params.leftMargin = (int) (mCurrentView * mTablineWidth +offset
							* mTablineWidth);
				} else if (position == 3 && mCurrentView == 3) {//3-4
					params.leftMargin = (int) (mCurrentView * mTablineWidth +offset
							* mTablineWidth);
				} else if (position == 4 && mCurrentView == 4) {//4-5
					params.leftMargin = (int) (mCurrentView * mTablineWidth +offset
							* mTablineWidth);
				} 
				
				else if (position == 0 && mCurrentView == 1) {//1-0
					params.leftMargin = (int) (mCurrentView * mTablineWidth + (offset - 1)
							* mTablineWidth);
				} else if (position == 1 && mCurrentView == 2) {//2-1
					params.leftMargin = (int) (mCurrentView * mTablineWidth + (offset - 1)
							* mTablineWidth);
				}
				else if (position == 2 && mCurrentView == 3) {//3-2
					params.leftMargin = (int) (mCurrentView * mTablineWidth + (offset - 1)
							* mTablineWidth);
				} else if (position == 3 && mCurrentView == 4) {//4-3
					params.leftMargin = (int) (mCurrentView * mTablineWidth + (offset - 1)
							* mTablineWidth);
				}else if (position == 4 && mCurrentView == 5) {//5-4
					params.leftMargin = (int) (mCurrentView * mTablineWidth + (offset - 1)
							* mTablineWidth);
				}
				mTabline.setLayoutParams(params);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @return
	 */
	private int getScreenWidth() {
		WindowManager wm = (WindowManager) getActivity().getSystemService(
				getActivity().WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}
}

/**
 * ViewPager适配器
 * @author Zero
 *
 */
class MyViewPagerAdapter extends PagerAdapter {
	private List<View> mDataList;

	public MyViewPagerAdapter(List<View> dataList) {
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
		View view = mDataList.get(position);
		container.addView(view);
		return view;
	}
	
	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

}

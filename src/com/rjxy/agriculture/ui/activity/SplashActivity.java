package com.rjxy.agriculture.ui.activity;

import java.util.ArrayList;
import java.util.List;

import com.rjxy.agriculture.R;
import com.rjxy.agriculture.adapter.SplashPagerAdapter;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashActivity extends BaseActivity {
	private ViewPager mViewPager;
	private SplashPagerAdapter mAdapter;
	private List<LinearLayout> mDataList;
	private LinearLayout mPointLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Intent intent;
		if (!mApp.isShowSplash()) {
			intent = new Intent(SplashActivity.this, LoginActivity.class);
			startActivity(intent);
		}
		initView();
		mAdapter = new SplashPagerAdapter(this, mDataList);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setCurrentItem(0);
		mPointLayout.getChildAt(0).setBackgroundResource(
				R.drawable.jog_tab_target_red);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				mViewPager.setCurrentItem(arg0);
				clearAllPoints();
				mPointLayout.getChildAt(arg0).setBackgroundResource(
						R.drawable.jog_tab_target_red);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	private void initView() {
		mViewPager = (ViewPager) this.findViewById(R.id.splash_view_pager);
		mPointLayout = (LinearLayout) this
				.findViewById(R.id.splash_point_layout);
		mDataList = new ArrayList<LinearLayout>();
		// 添加指示原点
		for (int i = 0; i < 3; i++) {
			TextView tv = new TextView(this);
			tv.setBackgroundResource(R.drawable.jog_tab_target_gray);
			tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
			tv.setTextColor(Color.GRAY);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.leftMargin = 15;
			tv.setLayoutParams(params);
			mPointLayout.addView(tv);
		}
		// 添加TextView
		for (int i = 0; i < 3; i++) {
			LinearLayout layout = new LinearLayout(this);
			layout.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			layout.setOrientation(LinearLayout.VERTICAL);
			layout.setLayoutParams(params1);

			LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params2.bottomMargin = 100;
			TextView tv = new TextView(this);
			tv.setText("功能" + (i + 1));
			tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
			tv.setLayoutParams(params2);
			layout.addView(tv);

			LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			Button button = new Button(this);

			if (i == 2) {
				button.setText("进入程序");
				button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
				button.setLayoutParams(params3);
				layout.addView(button);
			}
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mApp.setFirstEnter();
					Intent intent = new Intent(SplashActivity.this,
							LoginActivity.class);
					startActivity(intent);
					finish();
				}
			});
			mDataList.add(layout);
		}
	}

	/**
	 * 清除所有指示点的样式
	 */
	private void clearAllPoints() {
		for (int i = 0; i < mDataList.size(); i++) {
			mPointLayout.getChildAt(i).setBackgroundResource(
					R.drawable.jog_tab_target_gray);
		}
	}
}

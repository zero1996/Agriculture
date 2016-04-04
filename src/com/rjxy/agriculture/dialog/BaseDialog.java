package com.rjxy.agriculture.dialog;

import com.rjxy.agriculture.ClientApp;
import com.rjxy.agriculture.request.BaseRequest;
import com.rjxy.agriculture.request.RequestThread;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseDialog extends Dialog {
	
	private ClientApp mApp;
	private RequestThread  mRequestThread;
	
	
	private Context mContext;
	private Button confirmBtn;// 表示确定功能的按钮
	private TextView closeTV;// 表示关闭功能的文本
	
	public BaseDialog(Context context) {
		super(context);
		this.mContext = context;
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mApp = (ClientApp) mContext.getApplicationContext();
		
		//初始化线程
		mRequestThread = new RequestThread(mContext, mApp.getHandler());
	}

	/**
	 * 设置确定功能按钮
	 * 
	 * @param btn
	 */
	public void setCommonBtn(Button confirmBtn, TextView closeTV) {
		this.confirmBtn = confirmBtn;
		this.closeTV = closeTV;
		if (confirmBtn != null) {
			confirmBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					setConfirmBtnOnClick();
				}
			});
		}
		if(closeTV != null){
			closeTV.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					BaseDialog.this.dismiss();
				}
			});
		}
	}

	protected abstract void setConfirmBtnOnClick();

	
	/**
	 * 开始线程
	 * */
	public void startThread(BaseRequest request){
		mRequestThread.setBaseRequest(request);
		mRequestThread.start();
	}
	
	public void stopThread(){
		mRequestThread.stopThread();
	}
	
	
	
	public boolean isConfigValueAvailable(boolean isSetAirOrSoil,
			String value1, String value2, String value3, String value4) {
		boolean isConfigValueAvailable = false;
		if (isSetAirOrSoil) {// 判断空气或土壤阈值
			if ("".equals(value1) || "".equals(value2) || "".equals(value3)
					|| "".equals(value4)) {
				Toast.makeText(mContext, "阈值不能为空", Toast.LENGTH_SHORT).show();
			} else if (Integer.valueOf(value1) < Integer.MIN_VALUE
					|| Integer.valueOf(value2) > Integer.MAX_VALUE
					|| Integer.valueOf(value3) < Integer.MIN_VALUE
					|| Integer.valueOf(value4) > Integer.MAX_VALUE) {
				Toast.makeText(mContext, "阈值超过范围", Toast.LENGTH_SHORT).show();
			} else if (Integer.valueOf(value1) >= Integer.valueOf(value2)
					|| Integer.valueOf(value3) >= Integer.valueOf(value4)) {
				Toast.makeText(mContext, "最大值不能小于或等于最小值", Toast.LENGTH_SHORT)
						.show();
			} else {
				isConfigValueAvailable = true;
			}

		} else {// 判断CO2或光照阈值
			if ("".equals(value1) || "".equals(value2)) {
				Toast.makeText(mContext, "阈值不能为空", Toast.LENGTH_SHORT).show();
			} else if (Integer.valueOf(value1) < Integer.MIN_VALUE
					|| Integer.valueOf(value2) > Integer.MAX_VALUE) {
				Toast.makeText(mContext, "阈值超过范围", Toast.LENGTH_SHORT).show();
			} else if (Integer.valueOf(value1) >= Integer.valueOf(value2)) {
				Toast.makeText(mContext, "最大值不能小于或等于最小值", Toast.LENGTH_SHORT)
						.show();
			} else {
				isConfigValueAvailable = true;
			}
		}
		return isConfigValueAvailable;
	}

	
	
}

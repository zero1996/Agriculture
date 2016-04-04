package com.rjxy.agriculture.dialog;

import java.util.ArrayList;
import java.util.List;

import com.rjxy.agriculture.ClientApp;
import com.rjxy.agriculture.R;
import com.rjxy.agriculture.util.Util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class IpSetDialog extends BaseDialog {
	private Context mContext;
	private LinearLayout mIpPartLayout;// Ip地址布局部分
	private TextView closeTV;
	private Button okBtn;
	private ClientApp mApp;
	private String mIpStr;

	public IpSetDialog(Context context) {
		super(context);
		this.mContext = context;
		mApp = (ClientApp) context.getApplicationContext();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_ip_set);
		IpSetDialog.this.setCancelable(false);// 设置不能关闭

		initView();
		mIpStr = mApp.getIp();
		initIpLayout();
		setCommonBtn(okBtn,closeTV);
	}

	/**
	 * 初始化界面
	 */
	private void initView() {
		mIpPartLayout = (LinearLayout) this
				.findViewById(R.id.dialog_ip_set_part);
		closeTV = (TextView) this.findViewById(R.id.dialog_ip_set_close);
		okBtn = (Button) this.findViewById(R.id.dialog_ip_set_ok);
	}

	/**
	 * 初始化Ip部分的布局
	 */
	private void initIpLayout() {
		for (int i = 0; i < 7; i++) {
			int index = i % 2;
			if (index == 0) {
				EditText et = new EditText(mContext);
				// et.setInputType(TypedValue.TYPE_INT_DEC);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						0, LayoutParams.WRAP_CONTENT);
				params.weight = 2;
				et.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
				et.setLayoutParams(params);
				if (!mIpStr.equals("")) {
					List<String> mList = new ArrayList<String>();
					mList = Util.breakIp(mIpStr);
					et.setText(mList.get(i / 2));
				}
				mIpPartLayout.addView(et);
			} else {
				TextView tv = new TextView(mContext);
				// et.setInputType(TypedValue.TYPE_INT_DEC);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						0, LayoutParams.WRAP_CONTENT);
				params.weight = 1;
				tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
				tv.setText(".");
				tv.setTextColor(Color.BLACK);
				tv.setLayoutParams(params);
				mIpPartLayout.addView(tv);
			}
		}
	}

	private String formatIp() {
		mIpStr = "";
		for (int i = 0; i < 7; i++) {
			int index = i % 2;
			if (index == 0) {
				EditText et = (EditText) mIpPartLayout.getChildAt(i);
				String part = et.getText().toString();
				if ("".equals(part)) {
					Toast.makeText(mContext, "IP格式错误", Toast.LENGTH_SHORT)
							.show();
					return "";
				}
				mIpStr += part;
			} else {
				TextView et = (TextView) mIpPartLayout.getChildAt(i);
				String point = et.getText().toString();
				mIpStr += point;
			}
		}
		return mIpStr;
	}
	
	@Override
	protected void setConfirmBtnOnClick() {
		if (Util.provingIP(formatIp())) {
			mApp.setIp(mIpStr);
			Toast.makeText(mContext, "IP设置成功", Toast.LENGTH_SHORT)
					.show();
			IpSetDialog.this.dismiss();
		} else {
			Toast.makeText(mContext, "IP格式错误", Toast.LENGTH_SHORT)
					.show();
		}
	}

}

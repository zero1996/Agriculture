package com.rjxy.agriculture.ui.activity;

import com.rjxy.agriculture.R;
import com.rjxy.agriculture.dialog.IpSetDialog;
import com.rjxy.agriculture.dialog.RegisterDialog;
import com.rjxy.agriculture.request.BaseRequest;
import com.rjxy.agriculture.request.BaseRequest.OnResponseEventListener;
import com.rjxy.agriculture.request.BaseRequest.REQUEST_RESULT;
import com.rjxy.agriculture.request.LoginRequest;
import com.rjxy.agriculture.request.RequestThread;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {
	private TextView ipSetTV;
	private TextView registerTV;
	private TextView forgetPswTV;
	private Button loginBtn;
	private Button cancelBtn;
	private CheckBox rembPswCB;
	private boolean isChecked;
	private EditText usernameET;
	private EditText passwordET;
	
	RequestThread requestThread;
	LoginRequest mLoginRequest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initData();
		setListeners();
	}

	/**
	 * 设置监听
	 */
	private void setListeners() {
		ipSetTV.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IpSetDialog dialog = new IpSetDialog(LoginActivity.this);
				dialog.show();
			}
		});
		//注册
		registerTV.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				RegisterDialog dialog = new RegisterDialog(LoginActivity.this);
				dialog.show();
			}
		});
		
		rembPswCB.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				mApp.setCheckBoxCheck(isChecked);
			}
		});
		//登录按钮
		loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String username = usernameET.getText().toString();
				String passwrod = passwordET.getText().toString();
				if(username.equals("") || username==null || passwrod.equals("")||passwrod == null)
					return;
				mLoginRequest = new LoginRequest();
				mLoginRequest.setUsername(username);
				mLoginRequest.setPassword(passwrod);
				mLoginRequest.setOnResponseEventListener(new OnResponseEventListener() {
					
					@Override
					public void onResponse(BaseRequest request,REQUEST_RESULT result) {
						// TODO Auto-generated method stub
						if(mLoginRequest.isSuccess){
							startActivity(new Intent(LoginActivity.this,MainActivity.class));
							requestThread.interrupted();
							LoginActivity.this.finish();
						}
					}
				});
				requestThread = new RequestThread(LoginActivity.this,mApp.getHandler());
				requestThread.setBaseRequest(mLoginRequest);
				requestThread.start();
				if(mApp.getCheckBoxCheck()){
					mApp.setUserName(usernameET.getText().toString().trim());
					mApp.setPassword(passwordET.getText().toString().trim());
				}
			}
		});
		
		cancelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LoginActivity.this.finish();
			}
		});
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		isChecked = mApp.getCheckBoxCheck();
		//设置用户名、密码文本框，记住密码复选框的状态和数据
		rembPswCB.setChecked(isChecked);
		if (isChecked) {
			usernameET.setText(mApp.getUserName());
			passwordET.setText(mApp.getPassword());
		}
	}

	private void initView() {
		usernameET = (EditText) this.findViewById(R.id.login_username);
		passwordET = (EditText) this.findViewById(R.id.login_password);
		ipSetTV = (TextView) this.findViewById(R.id.login_ip_set);
		registerTV = (TextView) this.findViewById(R.id.login_register);
		forgetPswTV = (TextView) this.findViewById(R.id.login_forget_psw);
		loginBtn = (Button) this.findViewById(R.id.login_btn_login);
		cancelBtn = (Button) this.findViewById(R.id.login_cancel);
		rembPswCB = (CheckBox) this.findViewById(R.id.login_check_box);
	}

}

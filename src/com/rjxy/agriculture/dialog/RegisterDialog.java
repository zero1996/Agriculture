package com.rjxy.agriculture.dialog;

import com.rjxy.agriculture.R;
import com.rjxy.agriculture.request.BaseRequest;
import com.rjxy.agriculture.request.BaseRequest.OnResponseEventListener;
import com.rjxy.agriculture.request.BaseRequest.REQUEST_RESULT;
import com.rjxy.agriculture.request.RegisterRequest;
import com.rjxy.agriculture.util.Util;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterDialog extends BaseDialog {
	
	private RegisterRequest mRegisterRequest;
	
	private Context mContext;
	private EditText usernameET;
	private EditText passwordET;
	private EditText emailET;
	private TextView closeTV;
	private Button registerBtn;

	public RegisterDialog(Context context) {
		super(context);
		this.mContext = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_register);
		usernameET = (EditText) this.findViewById(R.id.register_username);
		passwordET = (EditText) this.findViewById(R.id.register_password);
		emailET = (EditText) this.findViewById(R.id.register_email);
		closeTV = (TextView) this.findViewById(R.id.dialog_register_close);
		registerBtn = (Button) this.findViewById(R.id.dialog_register_ok);
		setCommonBtn(registerBtn, closeTV);
	}

	@Override
	protected void setConfirmBtnOnClick() {
		String username = usernameET.getText().toString();
		String password = passwordET.getText().toString();
		String email = emailET.getText().toString();
		if("".equals(username) ||"".equals(password) ||"".equals(email) ){
			Toast.makeText(mContext, "请输入完整的信息", Toast.LENGTH_SHORT).show();
			return;
		}
		if(!Util.provingUserName(username)){
			Toast.makeText(mContext, "用户名必须为6-12位纯字母", Toast.LENGTH_SHORT).show();
			return;
		}
		if(!Util.provingPassword(password)){
			Toast.makeText(mContext, "密码必须为3-6位字母与数字组合", Toast.LENGTH_SHORT).show();
			return;
		}
		if(!Util.provingEmail(email)){
			Toast.makeText(mContext, "邮箱格式错误", Toast.LENGTH_SHORT).show();
			return;
		}
		
		mRegisterRequest = new RegisterRequest();
		mRegisterRequest.setUserName(username);
		mRegisterRequest.setPassword(password);
		mRegisterRequest.setEmail(email);
		mRegisterRequest.setOnResponseEventListener(new OnResponseEventListener() {

			@Override
			public void onResponse(BaseRequest request,REQUEST_RESULT result) {
				// TODO Auto-generated method stub

				if(mRegisterRequest.isSuccess){
					RegisterDialog.this.dismiss();
					System.out.println("-----------注册成功-------------");
					stopThread();
				}else{
					RegisterDialog.this.dismiss();
					System.out.println("-----------注册失败-------------");
					stopThread();
				}
			
			}
		});
		startThread(mRegisterRequest);
	}
	
	

}

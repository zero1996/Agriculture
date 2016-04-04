package com.rjxy.agriculture.request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 登录请求类
 * */
public class LoginRequest extends AgricultureRequest {

	public static final String ACTION="login";
	
	public String username;
	public String password;
	
	public void setUsername(String username){
		this.username = username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
	@Override
	protected String getActionName() {
		return ACTION;
	}

	@Override
	public String onGetJasonBody() {
		JSONObject object = new JSONObject();
		try {
			object.put("username", username);
			object.put("password", password);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}
	
}

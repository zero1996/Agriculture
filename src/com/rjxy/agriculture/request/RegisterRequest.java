package com.rjxy.agriculture.request;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterRequest extends AgricultureRequest {
	public static final String ACTION = "register";
	private String userName;
	private String password;
	private String email;
	
	
	@Override
	public String onGetJasonBody() {
		// TODO Auto-generated method stub
		JSONObject json  = null;
		try {
			json = new JSONObject();
			json.put("username", userName);
			json.put("password", password);
			json.put("email", email);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
		
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	protected String getActionName() {
		// TODO Auto-generated method stub
		return ACTION;
	}
	
}

package com.rjxy.agriculture.request;

import org.json.JSONException;
import org.json.JSONObject;

import com.rjxy.agriculture.ClientApp;
import com.rjxy.agriculture.bean.ContorllerStatusValue;

public class ContorllerStatusValueRequest extends AgricultureRequest {
	public static final String  ACTIONNAME = "getContorllerStatus";
	private String username = "";
	private ClientApp mApp;
	@Override
	protected String getActionName() {
		// TODO Auto-generated method stub
		return ACTIONNAME;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setClientApp(ClientApp mApp){
		this.mApp = mApp;
	} 
	@Override
	public String onGetJasonBody() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			json.put("username", username);		//请求名
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
	public void onParseJasonBody(String responseStr) {
		super.onParseJasonBody(responseStr);
		if(responseStr != null){
			try {
				JSONObject object = new JSONObject(responseStr);
				ContorllerStatusValue value = mApp.getContorllerStatusValue();
				if(object.has("result") && value != null){
					String result = object.getString("result");
					if("ok".equalsIgnoreCase(result)){
						this.isSuccess = true;
					}
					if(object.has("WaterPump")) {
						value.setWaterPump(object.getInt("WaterPump"));
					}
					if(object.has("Blower")) {
						value.setBlower(object.getInt("Blower"));
					}
					if(object.has("Roadlamp")) {
						value.setRoadlamp(object.getInt("Roadlamp"));
					}
					if(object.has("Buzzer")) {
						value.setBuzzer(object.getInt("Buzzer"));
					}
				}else{
					this.isSuccess = false;
				}
			} catch (JSONException e) {
				e.printStackTrace();
				this.isSuccess = false;
			}
		}
	}
}

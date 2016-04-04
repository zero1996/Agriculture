package com.rjxy.agriculture.request;

import org.json.JSONException;
import org.json.JSONObject;

import com.rjxy.agriculture.ClientApp;
import com.rjxy.agriculture.bean.SensorValue;

public class SensorValueRequest extends AgricultureRequest {
	public static final String  ACTIONNAME = "getSensor";
	private String username = "";
	
	private SensorValue mSensorValue;
	
	@Override
	protected String getActionName() {
		// TODO Auto-generated method stub
		return ACTIONNAME;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setSensorValue(SensorValue sensorValue) {
		this.mSensorValue = sensorValue;
	}
	
	public SensorValue getSensorValue() {
		return mSensorValue;
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
				if(object!=null && mSensorValue != null){
					if (object.has("result")) {
						String result = object.getString("result");
						// 判断请求是否成功
						if (result.equalsIgnoreCase("ok")) {
							isSuccess = true;
						} else {
							isSuccess = false;
						}
					}
					if(object.has("airHumidity")) {
						mSensorValue.setAirHumidity(object.getInt("airHumidity"));
					}
					if(object.has("airTemperature")) {
						mSensorValue.setAirTemperature(object.getInt("airTemperature"));
					}
					if(object.has("soilTemperature")) {
						mSensorValue.setSoilTemperature(object.getInt("soilTemperature"));
					}
					if(object.has("soilHumidity")) {
						mSensorValue.setSoilHumidity(object.getInt("soilHumidity"));
					}
					if(object.has("co2")) {
						mSensorValue.setCo2(object.getInt("co2"));
					}
					if(object.has("light")){
						mSensorValue.setLight(object.getInt("light"));
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

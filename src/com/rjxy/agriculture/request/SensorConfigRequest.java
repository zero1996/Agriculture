package com.rjxy.agriculture.request; 
import org.json.JSONException;
import org.json.JSONObject;

import com.rjxy.agriculture.ClientApp;
import com.rjxy.agriculture.bean.SensorConfig;

public class SensorConfigRequest extends AgricultureRequest {
	
	public static final String ACTIONNAME = "getConfig";
	private String username = ""; 
	private ClientApp mApp;
	SensorConfig mSensorConfig;
	
	
	@Override
	protected String getActionName() {
		// TODO Auto-generated method stub
		return ACTIONNAME;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
	
	public void setClientApp(ClientApp mApp) {
		this.mApp = mApp;
	}
	
	public void setSensorConfig(SensorConfig sensorConfig){
		mSensorConfig = sensorConfig;
	}
	
	@Override
	public void onParseJasonBody(String responseStr) {
		// TODO Auto-generated method stub
		try {
			JSONObject object = new JSONObject(responseStr);
			if(object.has("result") ) {
				String result = object.getString("result");
				if(result.equals("ok")) {
					isSuccess = true;
					
					mSensorConfig.setControlAuto(object.getInt("controlAuto"));
					
					mSensorConfig.setMaxAirHumidity(object.getInt("maxAirHumidity"));
					mSensorConfig.setMinAirHumidity(object.getInt("minAirHumidity"));
					
					mSensorConfig.setMaxAirTemperature(object.getInt("maxAirTemperature"));
					mSensorConfig.setMinAirTemperature(object.getInt("minAirTemperature"));
					
					mSensorConfig.setMaxCo2( object.getInt("maxCo2"));
					mSensorConfig.setMinCo2(object.getInt("minCo2"));
					
					mSensorConfig.setMaxLight(object.getInt("maxLight"));
					mSensorConfig.setMinLight(object.getInt("minLight"));
					
					mSensorConfig.setMaxSoilHumidity(object.getInt("maxSoilHumidity"));
					mSensorConfig.setMinSoilHumidity(object.getInt("minSoilHumidity"));
					
					mSensorConfig.setMaxSoilTemperature(object.getInt("maxSoilTemperature"));
					mSensorConfig.setMaxSoilTemperature(object.getInt("minSoilTemperature"));
				}else{
					isSuccess = false;
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
